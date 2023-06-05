package com.surveywizz.surveyservice.services.impl;

import com.surveywizz.surveyservice.entity.BaseEntity;
import com.surveywizz.surveyservice.repository.BaseRepository;
import com.surveywizz.surveyservice.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseServiceImpl<T extends BaseEntity> {

    private final BaseRepository<T> baseRepository;

    protected BaseServiceImpl(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public T saveEntity(T entity) throws SQLException {
        T saved = this.baseRepository.save(entity);
        if(saved.getId() == null){
            //TODO refactor this.
            throw new SQLException();
        }
        log.info("Saved entity by id {} and table",saved.getId(),saved.getClass().getSimpleName());
        return saved;
    }

    public T updatedEntity(T entity) throws SQLException {
        Date lastModifiedDate = new Date();
        entity.setLastModifiedDate(lastModifiedDate);
        T updated = this.baseRepository.save(entity);
        if(!updated.getLastModifiedDate().equals(lastModifiedDate)){
            // TODO refactor this.
            throw new SQLException();
        }
        return updated;
    }

    public List<T> findAll(Integer page, Integer size){
        return this.baseRepository.findAll(PageRequest.of(page,size)).stream().collect(Collectors.toList());
    }

    public T findById(String id){

        Optional<T> o = this.baseRepository.findById(id);
        // TODO refactor this exception
        return o.orElseThrow(NotFoundException::new);

    }

    public void deleteById(String id) throws SQLException {
        T entity = findById(id);
        entity.setIsDeleted(true);
        this.baseRepository.save(entity);
        log.info("deleted by id {},and table {}",entity.getId(),entity.getClass().getSimpleName());
    }

}

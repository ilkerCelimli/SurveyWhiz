package com.surveywizz.surveyservice.services.impl;

import com.surveywizz.surveyservice.entity.BaseEntity;
import com.surveywizz.surveyservice.repository.BaseRepository;
import com.surveywizz.surveyservice.services.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

    protected T saveEntity(T entity){
        T saved = this.baseRepository.save(entity);
        if(saved.getId() == null){
            //TODO refactor this.
            throw new RuntimeException();
        }
        log.info("Saved entity by id {} and table",saved.getId(),saved.getClass().getSimpleName());
        return saved;
    }

    protected T updatedEntity(T entity){
        Date lastModifiedDate = new Date();
        entity.setLastModifiedDate(lastModifiedDate);
        T updated = this.baseRepository.save(entity);
        if(!updated.getLastModifiedDate().equals(lastModifiedDate)){
            // TODO refactor this.
            throw new RuntimeException();
        }
        return updated;
    }

    protected List<T> findAll(Integer page, Integer size){
        return this.baseRepository.findAll(PageRequest.of(page,size)).stream().collect(Collectors.toList());
    }

    protected T findById(String id){

        Optional<T> o = this.baseRepository.findById(id);
        // TODO refactor this exception
        o.orElseThrow(() -> new RuntimeException());
        return o.get();

    }

    protected void deleteById(String id){
        T entity = findById(id);
        entity.setIsDeleted(true);
        updatedEntity(entity);
        log.info("deleted by id {},and table {}",entity.getId(),entity.getClass().getSimpleName());
    }

}

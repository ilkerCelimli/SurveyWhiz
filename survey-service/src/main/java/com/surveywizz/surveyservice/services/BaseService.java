package com.surveywizz.surveyservice.services;

import com.surveywizz.surveyservice.entity.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T saveEntity(T entity) throws SQLException;
    T updatedEntity(T entity) throws SQLException;
    List<T> findAll(Integer page, Integer size);
    T findById(String id);
    void deleteById(String id) throws SQLException;
}

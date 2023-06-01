package com.surveywizz.surveyservice.services;

import com.surveywizz.surveyservice.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T saveEntity(T entity);
    T updateEntity(T entity);
    T deleteEntity(String id);
    List<T> findAll(Integer page,Integer size);
    T findById(String id);

}

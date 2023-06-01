package com.surveywizz.surveyservice.repository;

import com.surveywizz.surveyservice.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T,String> {
}

package com.surveywizz.surveyservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Survey extends BaseEntity {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;


}

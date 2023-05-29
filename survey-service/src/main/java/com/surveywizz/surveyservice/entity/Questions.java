package com.surveywizz.surveyservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Questions extends BaseEntity {

    @Column(name="question",nullable = false)
    public String text;
}

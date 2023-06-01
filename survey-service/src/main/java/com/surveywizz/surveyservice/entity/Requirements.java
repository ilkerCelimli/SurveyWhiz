package com.surveywizz.surveyservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Requirements extends BaseEntity {

    @Column(nullable = false)
    public String requirement;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    public Survey survey;



}

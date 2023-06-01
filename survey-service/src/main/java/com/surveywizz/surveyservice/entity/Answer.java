package com.surveywizz.surveyservice.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions questions;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(nullable = false)
    private Integer answer;
}

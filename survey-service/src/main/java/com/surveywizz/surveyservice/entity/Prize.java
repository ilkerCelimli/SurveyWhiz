package com.surveywizz.surveyservice.entity;

import com.surveywizz.surveyservice.enums.Company;
import com.surveywizz.surveyservice.util.codeGenerate.Generate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

@Entity
public class Prize extends BaseEntity {

    @Column(nullable = true)
    private Date expirationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Company company;


    @Column(nullable = false)
    private Integer Amount;

    @Column(nullable=false)
    private Generate generate;



}

package com.surveywizz.surveyservice.services.impl;

import com.surveywizz.surveyservice.entity.Prize;
import com.surveywizz.surveyservice.repository.PrizeRepository;
import com.surveywizz.surveyservice.services.PrizeService;
import org.springframework.stereotype.Service;

@Service
public class PrizeServiceImpl extends BaseServiceImpl<Prize> implements PrizeService {

    private final PrizeRepository prizeRepository;

    public PrizeServiceImpl(PrizeRepository prizeRepository){
        super(prizeRepository);
        this.prizeRepository = prizeRepository;
    }




}

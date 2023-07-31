package com.surveywizz.surveyservice.services;

import com.surveywizz.surveyservice.entity.Prize;

public interface PrizeService extends BaseService<Prize> {

    void sendPrize(String userId);

}

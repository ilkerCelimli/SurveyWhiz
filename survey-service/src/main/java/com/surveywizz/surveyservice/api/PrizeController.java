package com.surveywizz.surveyservice.api;

import com.surveywizz.surveyservice.entity.Prize;
import com.surveywizz.surveyservice.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PrizeController {

    @Autowired
    private BaseService service;

    @GetMapping("/prize")
    public List<Prize> listAll(){
        return service.findAll();
    }


}

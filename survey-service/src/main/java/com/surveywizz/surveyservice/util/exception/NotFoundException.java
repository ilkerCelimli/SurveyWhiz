package com.surveywizz.surveyservice.util.exception;

public class NotFoundException extends RuntimeException{

    private NotFoundException(String message){
        super(message);
    }

    public NotFoundException(){
        this("Not Found");
    }
}

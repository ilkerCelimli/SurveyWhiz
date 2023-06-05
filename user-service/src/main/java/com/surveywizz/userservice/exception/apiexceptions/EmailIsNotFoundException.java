package com.surveywizz.userservice.exception.apiexceptions;


import com.surveywizz.userservice.exception.Constants;

public class EmailIsNotFoundException extends RuntimeException{
    public EmailIsNotFoundException() {
        super(Constants.EMAIL_IS_NOT_FOUND);
    }
}

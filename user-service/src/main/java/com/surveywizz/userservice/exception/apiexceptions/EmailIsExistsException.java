package com.surveywizz.userservice.exception.apiexceptions;


import com.surveywizz.userservice.exception.Constants;

public class EmailIsExistsException extends RuntimeException{

    public EmailIsExistsException() {
        super(Constants.EMAIL_IS_EXISTS);
    }
}

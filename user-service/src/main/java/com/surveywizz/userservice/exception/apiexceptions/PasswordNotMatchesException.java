package com.surveywizz.userservice.exception.apiexceptions;

import com.surveywizz.userservice.exception.Constants;

public class PasswordNotMatchesException extends RuntimeException{

    public PasswordNotMatchesException(){
        super(Constants.PASSWORD_NOT_MATCHES);
    }
}

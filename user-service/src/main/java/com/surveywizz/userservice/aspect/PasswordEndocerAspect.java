package com.surveywizz.userservice.aspect;


import com.surveywizz.userservice.entity.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PasswordEndocerAspect {

    private final PasswordEncoder bCryptPasswordEncoder;

    public PasswordEndocerAspect(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @AfterReturning(value = "@annotation(com.surveywizz.userservice.customannonatations.PasswordEncoder)",returning = "obj")
    public void encodePassword(Object obj){
       User u  =  (User) obj;
       String password = u.getPassword();
       ((User) obj).setPassword(bCryptPasswordEncoder.encode(password));
    }

}

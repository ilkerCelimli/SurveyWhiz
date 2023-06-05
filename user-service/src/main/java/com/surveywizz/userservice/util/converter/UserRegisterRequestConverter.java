package com.surveywizz.userservice.util.converter;


import com.surveywizz.userservice.customannonatations.PasswordEncoder;
import com.surveywizz.userservice.entity.User;
import com.surveywizz.userservice.util.RandomStringGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import request.userservice.UserRegisterRequest;

@Component
public class UserRegisterRequestConverter {
    @PasswordEncoder
    public User toEntity(UserRegisterRequest userRegisterRequest) {
        User u = new User(userRegisterRequest.name(), userRegisterRequest.surname(), userRegisterRequest.email(),
                userRegisterRequest.password(), userRegisterRequest.birtday(),false);
        u.setId(new ObjectId().toString());
        u.setActivitionCode(RandomStringGenerator.randomStringGenerator());
        return u;
    }

}

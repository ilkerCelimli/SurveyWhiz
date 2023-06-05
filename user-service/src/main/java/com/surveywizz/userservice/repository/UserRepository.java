package com.surveywizz.userservice.repository;


import com.surveywizz.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import request.userservice.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByActivitionCode(String user);

    Optional<User> findUserById(String id);

    Optional<User> findUserByEmail(String email);

    Boolean existsUserByEmail(String email);

    // S findByEmailExists(String email);
    List<UserInfo> findAllByIsActiveTrue();

    Optional<UserInfo> findUserByEmailAndIsActiveTrue(String email);


}

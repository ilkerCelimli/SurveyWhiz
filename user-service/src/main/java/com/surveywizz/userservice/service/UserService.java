package com.surveywizz.userservice.service;


import com.surveywizz.userservice.entity.User;
import com.surveywizz.userservice.exception.apiexceptions.BannedUserException;
import com.surveywizz.userservice.exception.apiexceptions.EmailIsExistsException;
import com.surveywizz.userservice.exception.apiexceptions.EmailIsNotFoundException;
import com.surveywizz.userservice.exception.apiexceptions.PasswordNotMatchesException;
import com.surveywizz.userservice.repository.UserRepository;
import com.surveywizz.userservice.util.JwtUtil;
import com.surveywizz.userservice.util.converter.UserRegisterRequestConverter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import request.userservice.UserInfo;
import request.userservice.UserLoginRequest;
import request.userservice.UserRegisterRequest;
import response.TokenResponse;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRegisterRequestConverter userRegisterRequestConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Transactional
    public User saveUser(UserRegisterRequest userRegisterRequest) throws MessagingException {
        if (!findEmailIsExists(userRegisterRequest.email())) {
            User u = userRegisterRequestConverter.toEntity(userRegisterRequest);
            this.userRepository.save(u);
            log.info("saved user date {},id {}", new Date(), u.getId());
            return u;
        }
        throw new EmailIsExistsException();
    }

    @Transactional
    public void activiteUser(String activitionCode) {
        Optional<User> u = this.userRepository.findUserByActivitionCode(activitionCode);
        u.ifPresent(i -> {
            i.setIsActive(true);
            this.userRepository.save(i);
        });
    }
    //TODO refactor this.
/*    @Transactional
    public void updateUser(UserRegisterRequest userRegisterRequest) {

        Optional<User> opt = this.userRepository.findUserByEmail(userRegisterRequest.email());
        opt.ifPresentOrElse((i -> {

        }), EmailIsNotFoundException::new);
    }*/

    public List<UserInfo> findAllUser() {
        return this.userRepository.findAllByIsActiveTrue();
    }

    public UserInfo findUserByEmail(String email) {
        Optional<UserInfo> user = this.userRepository.findUserByEmailAndIsActiveTrue(email);
        return user.orElse(null);
    }


    private boolean findEmailIsExists(String email) {
        return this.userRepository.existsUserByEmail(email);
    }

    @Transactional
    public void deleteUser(String email) {
        Optional<User> user = this.userRepository.findUserByEmail(email);
        user.ifPresentOrElse(i -> {
            i.setIsActive(false);
            this.userRepository.save(i);
        }, EmailIsNotFoundException::new);

    }

    public TokenResponse tokenResponse(UserLoginRequest userLoginRequest) {
        User u = this.userRepository.findUserByEmail(userLoginRequest.email()).orElseThrow(EmailIsNotFoundException::new);
        if (!u.getIsActive()) throw new BannedUserException(userLoginRequest.email());
        if (!passwordEncoder.matches(userLoginRequest.password(), u.getPassword()))
            throw new PasswordNotMatchesException();
        String token = jwtUtil.generate(userLoginRequest);
        return new TokenResponse(token);
    }

    public TokenResponse tokenResponse(String token) {
        String email = this.jwtUtil.validate(token).getClaim("email").asString();
        return new TokenResponse(jwtUtil.generate(email));
    }
}
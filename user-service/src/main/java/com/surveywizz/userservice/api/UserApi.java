package com.surveywizz.userservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.surveywizz.userservice.entity.User;
import com.surveywizz.userservice.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.userservice.UserInfo;
import request.userservice.UserLoginRequest;
import request.userservice.UserRegisterRequest;
import response.GenericResponse;
import response.TokenResponse;

import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<GenericResponse<User>> saveUser(@RequestBody UserRegisterRequest userRegisterRequest) throws MessagingException {
        User u = this.userService.saveUser(userRegisterRequest);
        return ResponseEntity.ok(GenericResponse.SUCCESS(u));
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<TokenResponse>> login(@RequestBody UserLoginRequest userLoginRequest){
       TokenResponse tokenResponse =  this.userService.tokenResponse(userLoginRequest);
       return ResponseEntity.ok(GenericResponse.SUCCESS(tokenResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<GenericResponse<TokenResponse>> refresh(@RequestBody TokenResponse token) {
        TokenResponse tokenResponse = this.userService.tokenResponse(token.getToken());
        return ResponseEntity.ok(GenericResponse.SUCCESS(tokenResponse));
    }
    @GetMapping("/activeuser")
    public ResponseEntity<GenericResponse<Void>> activeUser(@RequestParam String code) {
        this.userService.activiteUser(code);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<UserInfo>>> findUsers() {
        return ResponseEntity.ok(GenericResponse.SUCCESS(this.userService.findAllUser()));
    }
    // TODO refactor this.
  /*  @PutMapping("/")
    public ResponseEntity<GenericResponse<Void>> updateUsers(@JsonInclude(JsonInclude.Include.NON_NULL) @RequestBody UserRegisterRequest request) {
        this.userService.updateUser(request);
        return ResponseEntity.ok(new GenericResponse<>(200,"User Updated"));
    }*/

    @DeleteMapping("/")
    public ResponseEntity<GenericResponse<Void>> deleteUser(@RequestParam String email) {
         this.userService.deleteUser(email);
         return ResponseEntity.ok(new GenericResponse<>(200,"user inactived"));
    }
    @GetMapping(value = "/finduser")
    public ResponseEntity<GenericResponse<UserInfo>>findUserByEmail(@RequestParam String email){
        UserInfo u = this.userService.findUserByEmail(email);
       return u != null ? ResponseEntity.ok(new GenericResponse<>(200,null,u)) :
               ResponseEntity.ok().build();
    }

}

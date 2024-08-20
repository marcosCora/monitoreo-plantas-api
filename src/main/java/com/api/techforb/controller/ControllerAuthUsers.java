package com.api.techforb.controller;

import com.api.techforb.dtos.DtoAuthResponse;
import com.api.techforb.dtos.DtoLogin;
import com.api.techforb.dtos.DtoRegistrer;
import com.api.techforb.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techapi/auth")
public class ControllerAuthUsers {

    @Autowired
    private IServiceUser serviceUser;

    //registro

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody DtoRegistrer user){
        ResponseEntity<String> response = null;
        try{
            response = new ResponseEntity<>(serviceUser.registerNewUser(user), HttpStatus.OK);
        }catch (Exception e){
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }
    //login
    @PostMapping("/login")
    public ResponseEntity<DtoAuthResponse> loginUser(@RequestBody DtoLogin user){
        ResponseEntity<DtoAuthResponse> response = null;
        try{
            response = new ResponseEntity<>(serviceUser.loginUser(user), HttpStatus.OK);
        }catch (AuthenticationException e){
            response = new ResponseEntity<>(new DtoAuthResponse(null), HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            response = new ResponseEntity<>(new DtoAuthResponse(null), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}

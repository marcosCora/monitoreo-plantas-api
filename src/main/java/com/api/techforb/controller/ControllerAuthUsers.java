package com.api.techforb.controller;

import com.api.techforb.dtos.*;
import com.api.techforb.exception.error.InvalidDataException;
import com.api.techforb.exception.error.UserNoAuthenticateException;
import com.api.techforb.service.IServiceUser;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/techapi/auth")
public class ControllerAuthUsers {

    @Autowired
    private IServiceUser serviceUser;

    //registro
    @PostMapping("/register")
    public ResponseEntity<DtoResponse> registerUser(@Validated @RequestBody DtoRegistrer user) throws InvalidDataException{
        return new ResponseEntity<>(serviceUser.registerNewUser(user), HttpStatus.CREATED);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> loginUser(@RequestBody DtoRegistrer user) throws AuthenticationException {
        return new ResponseEntity<>(serviceUser.loginUser(user), HttpStatus.ACCEPTED);
    }

    //retorna nombre completo
    @GetMapping("/get-name/{email}")
    public ResponseEntity<?> getNameComplete(@RequestBody HashMap<String, String> email) throws InvalidDataException{
        return new ResponseEntity<>(serviceUser.getNameComplete(email), HttpStatus.ACCEPTED);
    }

    /*@PostMapping("/validate")
    public ResponseEntity<?> loginUser(@RequestBody String token){
        ResponseEntity<?> response = null;
        try{
            if(serviceUser.validationToken(token)){
                response = ResponseEntity.ok().build();
            }
        }catch (Exception e){
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return response;
    }*/




}

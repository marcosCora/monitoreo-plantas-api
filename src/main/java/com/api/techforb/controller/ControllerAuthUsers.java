package com.api.techforb.controller;

import com.api.techforb.dtos.*;
import com.api.techforb.exception.error.InvalidDataException;
import com.api.techforb.service.IServiceUser;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> loginUser(@RequestBody DtoLogin user){
        ResponseEntity<?> response = null;
        try{
            response = new ResponseEntity<>(serviceUser.loginUser(user), HttpStatus.OK);
        }catch (UsernameNotFoundException e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AuthenticationException e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    //retorna nombre completo
    @PostMapping("/get-name")
    public ResponseEntity<?> getNameComplete(@RequestBody DtoLogin email){
        ResponseEntity<Map<String , Object>> response = null;

        try{
            String res =  serviceUser.getNameComplete(email);
            Map<String, Object> responseOk = new HashMap<>();
            responseOk.put("success", true);
            responseOk.put("message", res);
            response = ResponseEntity.ok().body(responseOk);
        }catch (Exception e){
            Map<String, Object> responseError = new HashMap<>();
            responseError.put("success", false);
            responseError.put("message", e.getMessage());
            response = ResponseEntity.badRequest().body(responseError);
        }
        return response;
    }

    @PostMapping("/validate")
    public ResponseEntity<?> loginUser(@RequestBody TokenValidation token){
        ResponseEntity<?> response = null;
        try{
            if(serviceUser.validationToken(token)){
                response = ResponseEntity.ok().build();
            }
        }catch (Exception e){
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return response;
    }




}

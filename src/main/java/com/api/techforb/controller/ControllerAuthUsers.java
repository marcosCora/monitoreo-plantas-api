package com.api.techforb.controller;

import com.api.techforb.dtos.DtoAuthResponse;
import com.api.techforb.dtos.DtoLogin;
import com.api.techforb.dtos.DtoRegistrer;
import com.api.techforb.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<Map<String , Object>> registerUser(@RequestBody DtoRegistrer user){
        ResponseEntity<Map<String , Object>> response = null;

        try{
            String res =  serviceUser.registerNewUser(user);
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
        ResponseEntity<?> response = null;
        try{
            response = new ResponseEntity<>(serviceUser.getNameComplete(email), HttpStatus.OK);
        }
        catch (Exception e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }


}

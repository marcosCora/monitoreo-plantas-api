package com.api.techforb.service;

import com.api.techforb.dtos.*;
import com.api.techforb.exception.error.InvalidDataException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.naming.AuthenticationException;
import java.util.HashMap;

public interface IServiceUser {

    public DtoResponse registerNewUser(DtoRegistrer dtoUser) throws InvalidDataException;
    public HashMap<String, String> loginUser(DtoRegistrer dtoUser) throws  AuthenticationException;
    public HashMap<String, String> getNameComplete(HashMap<String, String> email) throws InvalidDataException;
    //public boolean validationToken(String token);

}

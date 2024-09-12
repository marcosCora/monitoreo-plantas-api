package com.api.techforb.service;

import com.api.techforb.dtos.*;
import com.api.techforb.exception.error.InvalidDataException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.naming.AuthenticationException;

public interface IServiceUser {

    public DtoResponse registerNewUser(DtoRegistrer dtoUser) throws InvalidDataException;
    public DtoAuthResponse loginUser(DtoLogin dtoUser) throws UsernameNotFoundException, AuthenticationException, Exception;
    public String getNameComplete(DtoLogin email);
    public boolean validationToken(TokenValidation token);

}

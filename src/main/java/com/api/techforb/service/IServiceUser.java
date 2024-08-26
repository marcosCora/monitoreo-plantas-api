package com.api.techforb.service;

import com.api.techforb.dtos.DtoAuthResponse;
import com.api.techforb.dtos.DtoLogin;
import com.api.techforb.dtos.DtoRegistrer;
import com.api.techforb.dtos.TokenValidation;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.naming.AuthenticationException;

public interface IServiceUser {

    public String registerNewUser(DtoRegistrer dtoUser) throws Exception;
    public DtoAuthResponse loginUser(DtoLogin dtoUser) throws UsernameNotFoundException, AuthenticationException, Exception;
    public String getNameComplete(DtoLogin email);
    public boolean validationToken(TokenValidation token);

}

package com.api.techforb.service;

import com.api.techforb.dtos.DtoAuthResponse;
import com.api.techforb.dtos.DtoLogin;
import com.api.techforb.dtos.DtoRegistrer;

import javax.naming.AuthenticationException;

public interface IServiceUser {

    public String registerNewUser(DtoRegistrer dtoUser) throws Exception;
    public DtoAuthResponse loginUser(DtoLogin dtoUser) throws AuthenticationException, Exception;

}

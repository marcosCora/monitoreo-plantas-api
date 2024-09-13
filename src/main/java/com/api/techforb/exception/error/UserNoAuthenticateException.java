package com.api.techforb.exception.error;

import javax.security.sasl.AuthenticationException;

public class UserNoAuthenticateException extends AuthenticationException {
    public UserNoAuthenticateException (String message){
        super(message);
    }
}

package com.api.techforb.service.implementation;

import com.api.techforb.dtos.*;
import com.api.techforb.entity.User;
import com.api.techforb.exception.error.InvalidDataException;
import com.api.techforb.mapper.UserMapper;
import com.api.techforb.repository.IRepositoryUser;
import com.api.techforb.security.JwtService;
import com.api.techforb.service.IServiceUser;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ServiceUser implements IServiceUser {

    @Autowired
    private IRepositoryUser userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;

    @Override
    public DtoResponse registerNewUser(@NotNull DtoRegistrer dtoUser) throws InvalidDataException{
        if(dtoUser.objValidator()){
            if(!userRepository.findByEmail(dtoUser.getEmail()).isEmpty()){
                throw new InvalidDataException("Already registered email");
            } else if (!validationNewUser(dtoUser)) {
                throw new InvalidDataException("Password or data invalid");
            }
        }else {
            throw new InvalidDataException("Data invalid");
        }

        User user = userMapper.dtoRegisterToUser(dtoUser);
        user.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
        userRepository.save(user);
        return new DtoResponse(HttpStatus.CREATED, "User registered successfully");
    }

    @Override
    public DtoAuthResponse loginUser(DtoLogin dtoUser) throws UsernameNotFoundException, AuthenticationException, Exception{

        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(dtoUser.getEmail(), dtoUser.getPassword());
        authenticationManager.authenticate(userPass);
        UserDetails user = (UserDetails) userRepository.findByEmail(dtoUser.getEmail()).get();
        String token = jwtService.getToken(user);
        if(token == null){
            throw new UsernameNotFoundException("Credential invalid");
        }

        return new DtoAuthResponse(token, dtoUser.getEmail());

    }

    @Override
    public String getNameComplete(DtoLogin email) {
        System.out.printf(email.getEmail());
        User user = userRepository.findByEmail(email.getEmail()).get();
        return user.getName() + " " + user.getLastName();
    }

    @Override
    public boolean validationToken(TokenValidation token){
        boolean rta = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            rta = true;
        }
        return rta;
    }

    public boolean validationNewUser(DtoRegistrer user){
        boolean rta = false;
        if(user.objValidator()){
            Pattern pattern = Pattern.compile(".{8,}");
            Matcher matcher = pattern.matcher(user.getPassword());
            if(matcher.matches()){
                rta = true;
            }
        }
        System.out.println(rta);
        return rta;

    }


}

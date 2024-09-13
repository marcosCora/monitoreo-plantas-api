package com.api.techforb.service.implementation;

import com.api.techforb.dtos.*;
import com.api.techforb.entity.User;
import com.api.techforb.exception.error.InvalidDataException;
import com.api.techforb.exception.error.UserNoAuthenticateException;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.HashMap;
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
            if(!dtoUser.getEmail().contains("@")){
                throw new InvalidDataException("Invalid email field");
            } else if (!userRepository.findByEmail(dtoUser.getEmail()).isEmpty()) {
                throw new InvalidDataException("Already registered email");
            } else if (dtoUser.getPassword().length() < 8) {
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
    public HashMap<String, String> loginUser(DtoRegistrer dtoUser) throws AuthenticationException {
        HashMap<String, String> response = new HashMap<>();
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(dtoUser.getEmail(), dtoUser.getPassword());
        if(!authenticationManager.authenticate(userPass).isAuthenticated()){
            throw new AuthenticationException();
        }
        UserDetails user = (UserDetails) userRepository.findByEmail(dtoUser.getEmail()).get();
        String token = jwtService.getToken(user);
        response.put("Token", token);
        response.put("Email", dtoUser.getEmail());

        return response;
    }

    @Override
    public HashMap<String, String> getNameComplete(HashMap<String, String> email) throws InvalidDataException{
        //solucionar el exception que nos arroja al no encontrar el user con ese email.
        User user = userRepository.findByEmail(email.get("email")).get();
        if(user == null){
            throw new InvalidDataException("Email Invalid");
        }
        HashMap<String, String> data = new HashMap<>();
        data.put("Email", user.getEmail());
        data.put("NameComplete", user.getName() + " " + user.getLastName());
        data.put("HttpStatus", HttpStatus.ACCEPTED.toString());
        return data;
    }

    /*
    @Override
    public boolean validationToken(String token){
        boolean rta = false;
        System.out.println(token);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            rta = true;
        }
        return rta;
    }*/
}

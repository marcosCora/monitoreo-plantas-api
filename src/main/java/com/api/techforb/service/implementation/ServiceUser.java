package com.api.techforb.service.implementation;

import com.api.techforb.dtos.DtoAuthResponse;
import com.api.techforb.dtos.DtoLogin;
import com.api.techforb.dtos.DtoRegistrer;
import com.api.techforb.entity.User;
import com.api.techforb.mapper.UserMapper;
import com.api.techforb.repository.IRepositoryRole;
import com.api.techforb.repository.IRepositoryUser;
import com.api.techforb.security.JwtGenerator;
import com.api.techforb.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser implements IServiceUser {

    @Autowired
    private IRepositoryUser userRepository;
    @Autowired
    private IRepositoryRole roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String registerNewUser(DtoRegistrer dtoUser) throws Exception{
        if(!userRepository.findByEmail(dtoUser.getEmail()).isEmpty()){
            throw new Exception("Already registered user");
        }
        User user = userMapper.dtoRegisterToUser(dtoUser);
        user.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
        userRepository.save(user);
        System.out.println(user);
        return "User registered successfully";
    }

    @Override
    public DtoAuthResponse loginUser(DtoLogin dtoUser) throws AuthenticationException, Exception{
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoUser.getEmail(), dtoUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new DtoAuthResponse(token);
    }



}

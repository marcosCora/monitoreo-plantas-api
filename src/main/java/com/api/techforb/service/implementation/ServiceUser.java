package com.api.techforb.service.implementation;

import com.api.techforb.dtos.DtoAuthResponse;
import com.api.techforb.dtos.DtoLogin;
import com.api.techforb.dtos.DtoRegistrer;
import com.api.techforb.entity.User;
import com.api.techforb.mapper.UserMapper;
import com.api.techforb.repository.IRepositoryUser;
import com.api.techforb.security.JwtService;
import com.api.techforb.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public DtoAuthResponse loginUser(DtoLogin dtoUser) throws UsernameNotFoundException, AuthenticationException, Exception{

        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(dtoUser.getEmail(), dtoUser.getPassword());
        authenticationManager.authenticate(userPass);
        UserDetails user = (UserDetails) userRepository.findByEmail(dtoUser.getEmail()).get();
        String token = jwtService.getToken(user);
        if(token == null){
            throw new UsernameNotFoundException("Credential invalid");
        }

        return new DtoAuthResponse(token) ;

    }



}

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

    /*@Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private AuthenticationManager authenticationManager;*/
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
        System.out.println("hola1");
        System.out.println(dtoUser);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoUser.getEmail(), dtoUser.getPassword()));
        System.out.println("hola2");
        UserDetails user = (UserDetails) userRepository.findByEmail(dtoUser.getEmail()).get();
        System.out.println(user);
        System.out.println(jwtService.getToken(user));
        return new DtoAuthResponse(jwtService.getToken(user)) ;

    }



}

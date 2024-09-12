package com.api.techforb.mapper;

import com.api.techforb.Enums.TypeRole;
import com.api.techforb.dtos.DtoRegistrer;
import com.api.techforb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public User dtoRegisterToUser(DtoRegistrer dtoRegistrer){
        User user = new User();
        user.setName(dtoRegistrer.getName());
        user.setLastName(dtoRegistrer.getLastName());
        user.setEmail(dtoRegistrer.getEmail());
        user.setRole(TypeRole.USER.toString());
        user.setPassword(dtoRegistrer.getPassword());
        System.out.println(user);
        return user;
    }

}

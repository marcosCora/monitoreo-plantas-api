package com.api.techforb.mapper;

import com.api.techforb.Enums.TypeRole;
import com.api.techforb.dtos.DtoRegistrer;
import com.api.techforb.entity.Role;
import com.api.techforb.entity.User;
import com.api.techforb.service.IServiceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserMapper {

    @Autowired
    private IServiceRole serviceRole;

    public User dtoRegisterToUser(DtoRegistrer dtoRegistrer){
        User user = new User();
        user.setName(dtoRegistrer.getName());
        user.setLastName(dtoRegistrer.getLastName());
        user.setEmail(dtoRegistrer.getEmail());
        Role role = serviceRole.findTypeRole(TypeRole.USER);
        user.setRoles(Collections.singletonList(role));
        return user;
    }

}

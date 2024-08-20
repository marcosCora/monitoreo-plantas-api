package com.api.techforb.service.implementation;

import com.api.techforb.Enums.TypeRole;
import com.api.techforb.entity.Role;
import com.api.techforb.repository.IRepositoryRole;
import com.api.techforb.service.IServiceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRole implements IServiceRole {

    @Autowired
    private IRepositoryRole roleRepository;

    @Override
    public Role findTypeRole(TypeRole typeRole) throws RuntimeException{
        return roleRepository.findByName(typeRole.toString()).orElseThrow(
                () -> new RuntimeException("Role not found"));
    }


}

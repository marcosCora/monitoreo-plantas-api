package com.api.techforb.service;

import com.api.techforb.Enums.TypeRole;
import com.api.techforb.entity.Role;

public interface IServiceRole {
    public Role findTypeRole(TypeRole typeRole) throws RuntimeException;
}

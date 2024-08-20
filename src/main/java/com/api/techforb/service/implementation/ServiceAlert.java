package com.api.techforb.service.implementation;

import com.api.techforb.repository.IRepositoryAlert;
import com.api.techforb.service.IServiceAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAlert implements IServiceAlert {

    @Autowired
    private IRepositoryAlert alertRepository;





}

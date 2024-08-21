package com.api.techforb.service.implementation;

import com.api.techforb.entity.Reading;
import com.api.techforb.repository.IRepositoryReading;
import com.api.techforb.service.IServiceReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceReading implements IServiceReading {

    @Autowired
    private IRepositoryReading readingRepository;


    @Override
    public List<Reading> getAllReading() {
        return readingRepository.findAll();
    }

    @Override
    public String saveReading(Reading reading) {
        readingRepository.save(reading);
        return "Reading Saved";
    }

    @Override
    public String deleteReading(Long id) throws Exception {
        readingRepository.findById(id)
                .orElseThrow(()-> new Exception("Reading not found"));
        readingRepository.deleteById(id);
        return "Plant deleted";
    }
}

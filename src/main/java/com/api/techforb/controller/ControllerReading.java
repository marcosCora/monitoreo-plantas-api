package com.api.techforb.controller;

import com.api.techforb.entity.Reading;
import com.api.techforb.service.IServiceReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("techapi/reading")
public class ControllerReading {

    @Autowired
    private IServiceReading serviceReading;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllReading(){
        ResponseEntity<?> response = null;
        try{
            response = new ResponseEntity<>(serviceReading.getAllReading(), HttpStatus.OK);
        }catch (Exception e){
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }
}

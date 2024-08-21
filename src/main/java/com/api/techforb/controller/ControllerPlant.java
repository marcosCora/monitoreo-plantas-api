package com.api.techforb.controller;

import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.entity.Plant;
import com.api.techforb.service.IServicePlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("techapi/plants")
public class ControllerPlant {

    @Autowired
    private IServicePlant service;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllPlants(){
        ResponseEntity<?> response = null;
        try{
            response = new ResponseEntity<>(service.getAllPlants(), HttpStatus.OK);
        }catch (Exception e){
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

    @GetMapping("/getalldto")
    public ResponseEntity<?> getAllPlantsDto(){
        ResponseEntity<?> response = null;
        try{
            response = new ResponseEntity<>(service.getAllPlantsDto(), HttpStatus.OK);
        }catch (Exception e){
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

    @PostMapping("/creat")
    public ResponseEntity<String> creatPlant(@RequestBody Plant plant){
        ResponseEntity<String> response = null;
        System.out.println(plant);
        try{
            response = new ResponseEntity<>(service.savePlant(plant), HttpStatus.OK);
        }catch (Exception e){
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

    @GetMapping("/countreadings")
    public ResponseEntity<?> getCountReading(){
        ResponseEntity<?> response = null;
        try{
            response = new ResponseEntity<>(service.readingsTotals(), HttpStatus.OK);
        }catch (Exception e){
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlant(@PathVariable Long id){
        ResponseEntity<String> response = null;
        try{
            response = new ResponseEntity<>(service.deletePlant(id), HttpStatus.OK);
        }catch (Exception e){
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }


}

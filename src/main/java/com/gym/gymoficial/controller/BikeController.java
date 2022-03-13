package com.gym.gymoficial.controller;

import com.gym.gymoficial.dto.BikeDto;
import com.gym.gymoficial.model.BikeModel;
import com.gym.gymoficial.model.UserModel;
import com.gym.gymoficial.service.BikeModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BikeController {

    final BikeModelService bikeModelService;

    public BikeController(BikeModelService bikeModelService) {
        this.bikeModelService = bikeModelService;
    }


    @GetMapping("/getBike")
    public ResponseEntity<Page<BikeModel>> getAllBike(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(bikeModelService.findAll(pageable));
    }

    @PostMapping("/saveBike")
    public ResponseEntity<Object> saveBike (@RequestBody @Valid BikeDto bikeDto){
        BikeModel bikeModel = new BikeModel();
        BeanUtils.copyProperties(bikeDto, bikeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(bikeModelService.save(bikeModel));
    }

    @DeleteMapping("/deleteBike/{id}")
    public ResponseEntity<Object> deleteBike (@PathVariable(value = "id") UUID id){
        Optional<BikeModel> bikeModelOptional =  bikeModelService.findById(id);
        if (!bikeModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bike not found");
        }
        bikeModelService.delete(bikeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Bike deleted");
    }

    @GetMapping("getBike/{id}")
    public ResponseEntity<Object> findBikeById (@PathVariable(value = "id") UUID id){
        Optional<BikeModel> bikeModelOptional = bikeModelService.findById(id);
        if (!bikeModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bike don't find by Id");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bikeModelOptional.get());
    }

    }





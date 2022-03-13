package com.gym.gymoficial.service;

import com.gym.gymoficial.model.BikeModel;
import com.gym.gymoficial.repository.BikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BikeModelService {

    final BikeRepository bikeRepository;

    public BikeModelService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }


    public Page<BikeModel> findAll(Pageable pageable) { return this.bikeRepository.findAll(pageable);}

    public Object save(BikeModel bikeModel) { return this.bikeRepository.save(bikeModel);}

    public Optional<BikeModel> findById(UUID id) { return bikeRepository.findById(id);}

    public void delete(BikeModel bikeModel) { bikeRepository.delete(bikeModel);}
}

package com.gym.gymoficial.repository;

import com.gym.gymoficial.model.BikeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BikeRepository extends JpaRepository <BikeModel, UUID> {

}

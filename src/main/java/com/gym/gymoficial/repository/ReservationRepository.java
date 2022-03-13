package com.gym.gymoficial.repository;

import com.gym.gymoficial.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    
   // boolean existsByBikeId(String bikeId);

    boolean existsByUserId(String userId);

    boolean existsByBikeNumber(String bikeNumber);
}

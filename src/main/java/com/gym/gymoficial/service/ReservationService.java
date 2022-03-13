package com.gym.gymoficial.service;

import com.gym.gymoficial.model.Reservation;
import com.gym.gymoficial.repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Page<Reservation> findAll(Pageable pageable) { return reservationRepository.findAll(pageable);
    }


    public boolean existsByUserId(String userId) { return reservationRepository.existsByUserId(userId);
    }

    public Object save(Reservation reservation) { return reservationRepository.save(reservation);
    }


    public Optional<Reservation> findById(UUID id) { return reservationRepository.findById(id);
    }

    public void checkOut(Reservation reservation) { reservationRepository.delete(reservation);
    }

    public boolean existsByBikeNumber(String bikeNumber) { return reservationRepository.existsByBikeNumber(bikeNumber);
    }
}

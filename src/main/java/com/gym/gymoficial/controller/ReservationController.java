package com.gym.gymoficial.controller;

import com.gym.gymoficial.model.Reservation;
import com.gym.gymoficial.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ReservationController {

    final ReservationService reservationService;
    public static int CHECKIN_EXPIRARION = 2400000;



    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping ("/getAllReservation")
    public ResponseEntity<Page<Reservation>> getAllReservation (@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.findAll(pageable));
    }

    @PostMapping("/checkInBike")
    public ResponseEntity<Object> checkInBike (@RequestBody @Valid Reservation reservation){
        if (reservationService.existsByBikeNumber(reservation.getBikeNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This bike is already located, try other");
        }
        if (reservationService.existsByUserId(reservation.getUserId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This User is already in checkIn");
        }
        reservation.setCheckIn(Timestamp.from(Instant.now()));
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.save(reservation));
    }

    @PutMapping("/extendYourCheckIn")
    public ResponseEntity<Object> extendYourCheckIn (@RequestBody @Valid Reservation reservation) {
        if (reservation.getCheckIn().getTime()  > CHECKIN_EXPIRARION) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("You have exceeded your time limit");
        }
        if (!reservationService.existsByBikeNumber(reservation.getBikeNumber())){
            throw new RuntimeException("You have exceeded your time limit");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.save(reservation));
    }

    @DeleteMapping("/checkOut/{id}")
    public ResponseEntity<Object> checkOut (@PathVariable(value = "id") UUID id){
        Optional<Reservation> reservation = reservationService.findById(id);
        if (!reservation.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This reservation doesn't exist");
        }
       reservationService.checkOut(reservation.get());
        return ResponseEntity.status(HttpStatus.OK).body("Your checkOut is complete");

    }


}

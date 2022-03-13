package com.gym.gymoficial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class PersonalDateModel {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 150)
    private String country;
    @Column(nullable = false, unique = true, length = 150)
    private String city;
    @Column(nullable = false, unique = true, length = 150)
    private String adress;
    @Column(nullable = false, unique = true)
    private Long mobile;

    @OneToOne
    private UserModel userModel;


}

package com.gym.gymoficial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class UserModel implements Serializable {
    private static final long serialVersionUID=1L;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 150)
    private String name;
    @Column(nullable = false, unique = true, length = 150)
    private String login;
    @Column(nullable = false, unique = true, length = 150)
    private String password;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @OneToOne
    @JoinColumn (name = "date_id")
    private PersonalDateModel personalDateModel;


}

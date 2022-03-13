package com.gym.gymoficial.dto;

import com.gym.gymoficial.model.PersonalDateModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {

    @NotBlank
    private String name;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    private PersonalDateModel personalDateModel;


}

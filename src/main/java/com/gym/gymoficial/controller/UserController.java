package com.gym.gymoficial.controller;

import com.gym.gymoficial.dto.UserDto;
import com.gym.gymoficial.model.UserModel;
import com.gym.gymoficial.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    final UserService userService;
    final PasswordEncoder encoder;


    public UserController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;

    }

    @GetMapping("/mapUser")
    public ResponseEntity<Page<UserModel>> getAllUser(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @PostMapping("/saveUser")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {
        if (userService.existsByName(userDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: this name already exist");
        }
        if (userService.existsByLoginAndPassword(userDto.getLogin(), userDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: this login or password already exist");
        }
        var userModel = new UserModel();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserDto userDto) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id doesn't exist");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setId(userModelOptional.get().getId());
        userModel.setRegistrationDate(userModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Object> getByUserId (@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUserById (@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id doesn't exist");
        }
        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }
}


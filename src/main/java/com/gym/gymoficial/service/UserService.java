package com.gym.gymoficial.service;

import com.gym.gymoficial.model.UserModel;
import com.gym.gymoficial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

@Service
public class UserService {


    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODA ESSA PARTE CONTROLA O CONTROLLER

    public Page<UserModel> findAll(Pageable pageable) { return userRepository.findAll(pageable);}

    public boolean existsByName(String name) { return  userRepository.existsByName(name);}

    public boolean existsByLoginAndPassword(String login, String password) {
        return userRepository.existsByLoginAndPassword(login, password);
    }

        public Object save(UserModel userModel) {return userRepository.save(userModel);}

         public Optional<UserModel> findById(UUID id) { return userRepository.findById(id);}

    public void delete(UserModel userModel) { userRepository.delete(userModel);
    }


}


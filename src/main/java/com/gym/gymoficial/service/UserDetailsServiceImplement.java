package com.gym.gymoficial.service;

import com.gym.gymoficial.data.DataUserDetails;
import com.gym.gymoficial.model.UserModel;
import com.gym.gymoficial.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImplement implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userModelOptional = userRepository.findByLogin(username);
        if (userModelOptional.isEmpty()) {
            throw new UsernameNotFoundException("Login [" + username + "] not found");
        }
        return new DataUserDetails(userModelOptional);
    }

}

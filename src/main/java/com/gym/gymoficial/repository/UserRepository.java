package com.gym.gymoficial.repository;

import com.gym.gymoficial.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    public Optional<UserModel> findByLogin (String login);

    boolean existsByName(String name);

    boolean existsByLoginAndPassword(String login, String password);


}

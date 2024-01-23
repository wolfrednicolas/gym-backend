package com.wolfred.gym.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.wolfred.gym.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}

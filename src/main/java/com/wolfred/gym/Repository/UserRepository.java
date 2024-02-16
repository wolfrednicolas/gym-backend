package com.wolfred.gym.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.wolfred.gym.Dto.UserResponseDTO;
import com.wolfred.gym.Enums.UserRole;
import com.wolfred.gym.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
    
    @Query("SELECT new com.wolfred.gym.Dto.UserResponseDTO(u.id, u.email) FROM User u WHERE u.role != :excludedRole")
    List<UserResponseDTO> findByRoleNot(UserRole excludedRole);
}

package com.wolfred.gym.Service;

import java.util.List;

import com.wolfred.gym.Dto.UserResponseDTO;
import com.wolfred.gym.Enums.UserRole;


public interface UserService {
    List<UserResponseDTO> findAll();
    List<UserResponseDTO> findByRoleNot(UserRole excludedRole);
    UserResponseDTO getUser(Long id);
}

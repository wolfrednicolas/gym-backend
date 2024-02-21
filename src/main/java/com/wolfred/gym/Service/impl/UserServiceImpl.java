package com.wolfred.gym.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolfred.gym.Dto.UserResponseDTO;
import com.wolfred.gym.Enums.UserRole;
import com.wolfred.gym.Models.User;
import com.wolfred.gym.Repository.UserRepository;
import com.wolfred.gym.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper; 

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<UserResponseDTO> findAll() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map((user) -> this.modelMapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> findByRoleNot(UserRole excludedRole) {
        return this.userRepository.findByRoleNot(excludedRole);
    }
    @Override
    public UserResponseDTO getUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        if(user.isEmpty()) return null;

        return this.modelMapper.map(user, UserResponseDTO.class);
    }
    
}

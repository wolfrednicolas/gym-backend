package com.wolfred.gym.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wolfred.gym.Dto.CreateUserAdminRequestDTO;
import com.wolfred.gym.Dto.SignUpDto;
import com.wolfred.gym.Models.User;
import com.wolfred.gym.Repository.UserRepository;



@Service
public class AuthService implements UserDetailsService {

  @Autowired
  UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    var user = repository.findByEmail(username);
    return user;
  }

  public UserDetails signUp(SignUpDto data){
    if (repository.findByEmail(data.email()) != null) {
        return null;
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User();
    newUser.setEmail(data.email());
    newUser.setPassword(encryptedPassword);
    newUser.setRole(data.role());
    return repository.save(newUser);
  }

  public UserDetails CreateUserAdmin(CreateUserAdminRequestDTO data){
    if (repository.findByEmail(data.email()) != null) {
      return null;
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User();
    newUser.setEmail(data.email());
    newUser.setPassword(encryptedPassword);
    newUser.setRole(data.role());
    return repository.save(newUser);
  }
}

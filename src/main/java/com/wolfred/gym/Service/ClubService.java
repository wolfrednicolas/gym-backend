package com.wolfred.gym.Service;

import java.util.List;

import com.wolfred.gym.Dto.ClubRequestDTO;
import com.wolfred.gym.Dto.ClubResponseDTO;


public interface ClubService {
    List<ClubResponseDTO> findAll();
    void create(ClubRequestDTO club);
    ClubResponseDTO find(long id);
    ClubResponseDTO update(long id, ClubRequestDTO club);
    void delete(Long id);
}


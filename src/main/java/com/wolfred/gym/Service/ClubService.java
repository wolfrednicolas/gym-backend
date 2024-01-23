package com.wolfred.gym.Service;

import java.util.List;

import com.wolfred.gym.Dto.ClubDto;


public interface ClubService {
    List<ClubDto> findAll();
    void create(ClubDto club);
    ClubDto find(long id);
    ClubDto update(long id, ClubDto club);
    void delete(Long id);
}


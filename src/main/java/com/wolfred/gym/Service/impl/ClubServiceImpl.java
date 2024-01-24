package com.wolfred.gym.Service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolfred.gym.Dto.ClubRequestDTO;
import com.wolfred.gym.Dto.ClubResponseDTO;
import com.wolfred.gym.Models.Club;
import com.wolfred.gym.Repository.ClubRepository;
import com.wolfred.gym.Service.ClubService;
import org.modelmapper.ModelMapper;


@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;

    @Autowired
    private ModelMapper modelMapper; 

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubResponseDTO> findAll() {
        List<Club> clubs = this.clubRepository.findAll();
        return clubs.stream().map((club) -> this.modelMapper.map(club, ClubResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void create(ClubRequestDTO clubRequestDTO) {
        this.clubRepository.save(this.modelMapper.map(clubRequestDTO, Club.class));
    }

    @Override
    public ClubResponseDTO find(long id) {
        Optional<Club> find = this.clubRepository.findById(id);
        if(find.isEmpty()) return null;

        return this.modelMapper.map(this.clubRepository.findById(id).get(), ClubResponseDTO.class);
    }

    @Override
    public ClubResponseDTO update(long id, ClubRequestDTO clubRequestDTO) {
        Club club = this.modelMapper.map(clubRequestDTO, Club.class);
        club.setId(id);
        return this.modelMapper.map(club, ClubResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        this.clubRepository.deleteById(id);
    }
    
}


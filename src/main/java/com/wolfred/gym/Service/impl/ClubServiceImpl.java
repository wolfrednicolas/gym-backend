package com.wolfred.gym.Service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolfred.gym.Dto.ClubDto;
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
    public List<ClubDto> findAll() {
        List<Club> clubs = this.clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    private ClubDto mapToClubDto(Club club){
        return ClubDto.builder()
                .id(club.getId()) 
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())  
                .updateOn(club.getUpdateOn())                   
                .build();
    }

    private Club mapToClub(ClubDto clubdDto){
        return Club.builder()
                .id(clubdDto.getId()) 
                .title(clubdDto.getTitle())
                .photoUrl(clubdDto.getPhotoUrl())
                .content(clubdDto.getContent())
                .createdOn(clubdDto.getCreatedOn())  
                .updateOn(clubdDto.getUpdateOn())                   
                .build();
    }

    @Override
    public void create(ClubDto clubDto) {
        this.clubRepository.save(mapToClub(clubDto));
    }

    @Override
    public ClubDto find(long id) {
        Optional<Club> find = this.clubRepository.findById(id);
        if(find.isEmpty()) return null;

        return this.modelMapper.map(this.clubRepository.findById(id).get(), ClubDto.class);
    }

    @Override
    public ClubDto update(long id, ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        club.setId(id);
        return mapToClubDto(this.clubRepository.save(club));
    }

    @Override
    public void delete(Long id) {
        this.clubRepository.deleteById(id);
    }
    
}


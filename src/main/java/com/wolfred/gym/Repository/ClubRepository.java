package com.wolfred.gym.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wolfred.gym.Models.Club;


public interface ClubRepository extends JpaRepository<Club, Long> {
    
    Optional<Club> findByTitle(String title);
}

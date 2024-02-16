package com.wolfred.gym.Dto;

import lombok.Data;
@Data

public class UserResponseDTO {
    private Long id;
    private String email;

    public UserResponseDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}

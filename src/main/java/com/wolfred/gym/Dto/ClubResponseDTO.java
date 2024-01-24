package com.wolfred.gym.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubResponseDTO {
    private Long id;
    private String title;
    private String photoUrl;
    private String content;
    private String address;
}

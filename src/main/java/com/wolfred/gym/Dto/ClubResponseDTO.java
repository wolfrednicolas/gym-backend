package com.wolfred.gym.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClubResponseDTO {
    private Long id;
    private String title;
    private String photoUrl;
    private String content;
    private String address;
    private String city;
    private String state;
    private String zipcode;
}

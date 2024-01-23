package com.wolfred.gym.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubDto {
    private Long id;
    @NotEmpty(message = "Club title should not be empty")
    private String title;
    @NotEmpty(message = "Photo Url should not be empty")
    private String photoUrl;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    @NotEmpty(message = "Address should not be empty")
    private String address;
    
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;
}

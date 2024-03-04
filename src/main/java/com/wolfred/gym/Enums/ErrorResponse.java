package com.wolfred.gym.Enums;

import lombok.Getter;

@Getter
public enum ErrorResponse {
    UserWithProvidedEmailAlreadyExists("User with the provided email already exists");

    private String message;

    ErrorResponse(String message){
        this.message = message;
    }
}

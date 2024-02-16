package com.wolfred.gym.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseUtil {

    public static ResponseEntity<?> statusOkResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<?> statusCreatedResponse() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public static ResponseEntity<?> statusUnauthorizedResponse() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public static ResponseEntity<?> statusUnauthorizedResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", data);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    public static ResponseEntity<?> statusUnprocessableEntityResponse(BindingResult data) {
        Map<String, Object> response = new HashMap<>();
        response.put("errors", getValidationErrors(data));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    public static ResponseEntity<?> statusNotFoundEntityResponse(String data) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", data);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public static ResponseEntity<?> statusConflictResponse(String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("errors", errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    public static ResponseEntity<?> statusBadRequestResponse(BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        response.put("errors", getValidationErrors(result));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private static List<String> getValidationErrors(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }
}


package com.wolfred.gym.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseUtil {
    static Map<String, Object> response = new HashMap<>();

    public static ResponseEntity<?> statusOkResponse(Object data) {
        response.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<?> statusCreatedResponse() {
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static ResponseEntity<?> statusUnauthorizedResponse() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    public static ResponseEntity<?> statusUnauthorizedResponse(Object data) {
        response.put("error", data);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    public static ResponseEntity<?> statusUnprocessableEntityResponse(Object data) {
        response.put("error", data);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    public static ResponseEntity<?> statusNotFoundEntityResponse(Object data) {
        response.put("error", data);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public static ResponseEntity<?> statusConflictResponse(String errorMessage) {
        response.put("errors", errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    public static ResponseEntity<?> statusBadRequestResponse(BindingResult result) {
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


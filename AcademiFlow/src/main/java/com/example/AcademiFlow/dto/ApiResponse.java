package com.example.AcademiFlow.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private LocalDateTime timestamp;
    private String message;
    private int status;

    public ApiResponse(T data, int status, String message) {
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    public ApiResponse() {

    }

    public ApiResponse(int status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

}

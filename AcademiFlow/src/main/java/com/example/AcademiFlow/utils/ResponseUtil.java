package com.example.AcademiFlow.utils;

import com.example.AcademiFlow.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utility class for creating standardized API responses.
 */
public class ResponseUtil {

    /**
     * Creates a success response entity.
     *
     * @param data the data to include in the response
     * @param message the message to include in the response
     * @param <T> the type of the data
     * @return the response entity containing the success message and data
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>(data, HttpStatus.OK.value(), message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        ApiResponse<T> response = new ApiResponse<>( HttpStatus.CREATED.value(), message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>(data, HttpStatus.CREATED.value(), message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message) {
        ApiResponse<T> response = new ApiResponse<>(HttpStatus.CREATED.value(), message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Creates a delete response entity.
     *
     * @param message the message to include in the response
     * @param <T> the type of the data
     * @return the response entity containing the delete message and data
     */
    public static <T> ResponseEntity<ApiResponse<T>> delete( String message) {
        ApiResponse<T> response = new ApiResponse<>( HttpStatus.NO_CONTENT.value(), message);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(String errorMessage, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setData(null);
        response.setMessage(errorMessage);
        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }
}

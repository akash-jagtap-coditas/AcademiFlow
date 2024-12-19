package com.example.AcademiFlow.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for user exception details.
 */
@Getter
@Setter
public class ExceptionDto {

    /**
     * The HTTP status code of the exception.
     */
    private int status;

    /**
     * The error message of the exception.
     */
    private String message;

    /**
     * The timestamp when the exception occurred.
     */
    private long timestamp;

    /**
     * Sets the timestamp for the exception.
     *
     * @param l the timestamp to set
     */
    public void setTimeStamp(long l) {
        this.timestamp = l;
    }
}

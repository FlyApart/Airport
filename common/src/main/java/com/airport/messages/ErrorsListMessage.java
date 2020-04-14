package com.airport.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorsListMessage {
    private Long errorCode;

    private List<FieldError> errors;

    private String message;

    public ErrorsListMessage(List<FieldError> errors, String message) {
        this.errors = errors;
        this.message = message;
    }

}

package com.airport.controller.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage {
    private Long errorCode;

    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }
}

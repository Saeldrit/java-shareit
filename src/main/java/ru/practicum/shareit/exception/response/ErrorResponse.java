package ru.practicum.shareit.exception.response;

import lombok.Value;

@Value
public class ErrorResponse {
	String error;
	String message;
}

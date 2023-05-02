package ru.practicum.shareit.exception;

public class RequestWithoutAccessPermission extends RuntimeException {
	public RequestWithoutAccessPermission(String message) {
		super(message);
	}
}

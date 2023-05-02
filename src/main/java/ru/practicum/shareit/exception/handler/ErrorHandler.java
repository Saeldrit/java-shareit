package ru.practicum.shareit.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.exception.DuplicateEmailException;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.RequestWithoutAccessPermission;
import ru.practicum.shareit.exception.response.ErrorResponse;

@RestControllerAdvice("ru.practicum.shareit")
public class ErrorHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handlerDefaultException(Exception exception) {
		return new ErrorResponse("Exception", exception.getMessage());
	}

	@ExceptionHandler({
			DuplicateEmailException.class})
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handlerUserValid(Exception exception) {
		return new ErrorResponse("Valid Exception", exception.getMessage());
	}

	@ExceptionHandler({
			MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleValidationException(Exception exception) {
		return new ErrorResponse("Valid Exception", exception.getMessage());
	}

	@ExceptionHandler({EntityNotFoundException.class,
			RequestWithoutAccessPermission.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handlerEntityFoundException(Exception exception) {
		return new ErrorResponse("Found exception", exception.getMessage());
	}
}

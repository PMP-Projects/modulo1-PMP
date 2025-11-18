package br.com.fatec.modulo1.controller.advice;

import br.com.fatec.modulo1.controller.dto.response.ErrorResponse;
import br.com.fatec.modulo1.exception.BadRequestException;
import br.com.fatec.modulo1.exception.InternalServerException;
import br.com.fatec.modulo1.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerException.class, Exception.class})
    public ErrorResponse handleInternalServerError(
            final Exception exception,
            final HttpServletRequest request) {
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFound(
            final NotFoundException exception,
            final HttpServletRequest request) {
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            HttpMessageNotReadableException.class})
    public ErrorResponse handleBadRequest(
            final Exception exception,
            final HttpServletRequest request) {
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                exception.getMessage()
        );
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(final MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return new ErrorResponse(
                LocalDateTime.now(),
                null,
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                errors.toString());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleValidationExceptions(final ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations()
                .forEach(error -> {
                    String errorMessage = error.getMessage();
                    errors.add(errorMessage);
                });
        return new ErrorResponse(
                LocalDateTime.now(),
                null,
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                errors.toString());
    }

}

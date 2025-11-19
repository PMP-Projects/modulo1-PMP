package br.com.fatec.modulo1;

import br.com.fatec.modulo1.controller.advice.RestExceptionHandler;
import br.com.fatec.modulo1.exception.BadRequestException;
import br.com.fatec.modulo1.exception.InternalServerException;
import br.com.fatec.modulo1.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestExceptionHandlerTest {

    private RestExceptionHandler handler;
    private HttpServletRequest request;

    @BeforeEach
    void setup() {
        handler = new RestExceptionHandler();
        request = mock(HttpServletRequest.class);
        when(request.getServletPath()).thenReturn("/test-path");
    }

    @Test
    void testHandleInternalServerError() {
        InternalServerException ex = new InternalServerException("Erro interno");
        var response = handler.handleInternalServerError(ex, request);

        assertEquals(500, response.status());
        assertEquals("Erro interno", response.message());
        assertEquals("/test-path", response.path());
    }

    @Test
    void testHandleNotFound() {
        NotFoundException ex = new NotFoundException("Não encontrado");
        var response = handler.handleNotFound(ex, request);

        assertEquals(404, response.status());
        assertEquals("Não encontrado", response.message());
        assertEquals("/test-path", response.path());
    }

    @Test
    void testHandleBadRequest_withBadRequestException() {
        BadRequestException ex = new BadRequestException("Requisição inválida");
        var response = handler.handleBadRequest(ex, request);

        assertEquals(400, response.status());
        assertEquals("Requisição inválida", response.message());
    }

    @Test
    void testHandleBadRequest_withHttpMessageNotReadableException() {
        var ex = new org.springframework.http.converter.HttpMessageNotReadableException("Mensagem inválida");
        var response = handler.handleBadRequest(ex, request);

        assertEquals(400, response.status());
        assertEquals("Mensagem inválida", response.message());
    }

    @Test
    void testHandleValidationExceptions_MethodArgumentNotValidException() {
        BindingResult bindingResult = mock(BindingResult.class);
        ObjectError error = new ObjectError("field", "Campo inválido");
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(error));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        var response = handler.handleValidationExceptions(ex);

        assertEquals(400, response.status());
        assertTrue(response.message().contains("Campo inválido"));
    }

    @Test
    void testHandleValidationExceptions_ConstraintViolationException() {
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getMessage()).thenReturn("Violação de restrição");
        ConstraintViolationException ex = new ConstraintViolationException(Set.of(violation));

        var response = handler.handleValidationExceptions(ex);

        assertEquals(400, response.status());
        assertTrue(response.message().contains("Violação de restrição"));
    }
}

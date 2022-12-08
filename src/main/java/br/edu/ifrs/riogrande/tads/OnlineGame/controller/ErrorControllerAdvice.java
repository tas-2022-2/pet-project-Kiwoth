package br.edu.ifrs.riogrande.tads.OnlineGame.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.ExceptionResponse;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Map<String, Object>> ExceptionHandler(Exception exception, HttpServletRequest request) {
        var status = getExceptionResponseCode(exception);
        var body = buildBody(status, exception, request.getRequestURI());
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Map<String, Object>> DataIntegrityViolationExceptionHandler(
            DataIntegrityViolationException exception, HttpServletRequest request) {
        var status = getExceptionResponseCode(exception);
        var body = buildBody(status, exception, request.getRequestURI(), "database error");
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, Object>> MethodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var message = exception.getFieldErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());
        var body = buildBody(status, exception, request.getRequestURI(), message);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Map<String, Object>> ConstraintViolationExceptionHandler(
            ConstraintViolationException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var message = exception.getConstraintViolations().stream()
                .map(e -> e.getMessageTemplate())
                .collect(Collectors.toList());
        var body = buildBody(status, exception, request.getRequestURI(), message);
        return new ResponseEntity<>(body, status);
    }

    private Map<String, Object> buildBody(HttpStatus status, Exception exception, String path) {
        return buildBody(status, exception, path, exception.getLocalizedMessage());
    }

    private Map<String, Object> buildBody(HttpStatus status, Exception exception, String path, Object message) {
        return ExceptionResponse
                .builder()
                .httpStatus(status)
                .exception(exception)
                .message(message)
                .path(path)
                .build()
                .toMap();
    }

    private HttpStatus getExceptionResponseCode(Exception exception) {
        var responseStatus = AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (responseStatus != null)
            httpStatus = responseStatus.code();
        return httpStatus;
    }

}
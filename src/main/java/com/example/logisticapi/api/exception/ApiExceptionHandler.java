package com.example.logisticapi.api.exception;

import com.example.logisticapi.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ArrayList<Exception.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();

            campos.add(new Exception.Campo(nome, mensagem));
        }

        Exception exception = new Exception();
        exception.setStatus(status.value());
        exception.setDataHora(OffsetDateTime.now());
        exception.setTitulo("Um ou mais campos estão inválidos. Preencha corretamente!");
        exception.setCampos(campos);

        return handleExceptionInternal(ex, exception, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Exception exception = new Exception();
        exception.setStatus(status.value());
        exception.setDataHora(OffsetDateTime.now());
        exception.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);
    }
}

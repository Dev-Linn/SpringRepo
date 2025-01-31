package com.example.demo.controller.common;

import com.example.demo.dto.ErroCampo;
import com.example.demo.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
       List<FieldError> fieldErrors = exception.getFieldErrors();
        List<ErroCampo> listaDeErros = fieldErrors.stream().
                map(fe -> new ErroCampo(
                        fe.getField(),
                        fe.getDefaultMessage())).
                        collect(Collectors.
                        toList());
        System.out.println(exception);
        return  new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",
                listaDeErros);
    }
}

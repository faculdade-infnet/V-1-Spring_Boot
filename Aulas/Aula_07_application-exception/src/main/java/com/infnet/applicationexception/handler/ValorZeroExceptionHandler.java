package com.infnet.applicationexception.handler;

import com.infnet.applicationexception.model.ErrorModel;
import com.infnet.applicationexception.exception.ValorZeroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// Manipulador global
@ControllerAdvice
public class ValorZeroExceptionHandler {

    // Tratamento do erro
    // Retorna uma menssagem erro 412, Response body indica que a mensagem de erro será retornado no cor oda requisição
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(ValorZeroException.class)
    public ErrorModel handler(ValorZeroException ex) {
        return new ErrorModel(ex.getMessage(), ex.getLancamento());
    }
}

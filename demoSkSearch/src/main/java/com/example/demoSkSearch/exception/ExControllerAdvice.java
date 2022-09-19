package com.example.demoSkSearch.exception;

import com.example.demoSkSearch.response.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BasicResponse missingParamExHandle(MissingServletRequestParameterException e) {
        log.error("[MissingServletRequestParameterException] ex", e);
        return new BasicResponse(BasicResponse.NOK,e.getMessage(),null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public BasicResponse exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new BasicResponse(BasicResponse.NOK,e.getMessage(), null);
    }
}

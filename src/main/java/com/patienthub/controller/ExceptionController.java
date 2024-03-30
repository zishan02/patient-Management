package com.patienthub.controller;

import com.patienthub.exceptions.MobileNotExistException;
import com.patienthub.exceptions.PatientExistsException;
import com.patienthub.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(PatientExistsException.class)
    public ResponseEntity<ErrorResponse> handlePatientExistsException(PatientExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value() ,ex.getMessage()));
    }

    @ExceptionHandler(MobileNotExistException.class)
    public ResponseEntity<ErrorResponse> handleMobileNotExistsException(MobileNotExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value() ,ex.getMessage()));
    }

}

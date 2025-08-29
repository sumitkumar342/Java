package com.mb.IPL.exception;

import com.mb.IPL.entity.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception) {
        ResponseStructure<String> resp = new ResponseStructure<>();
        resp.setData(exception.getMessage());
        resp.setStatusCode(HttpStatus.NOT_FOUND.value());
        resp.setMessage("This id is not available in our database");
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlaceNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handlePlaceNotFoundException(PlaceNotFoundException exception) {
        ResponseStructure<String> resp = new ResponseStructure<>();
        resp.setData(exception.getMessage());
        resp.setStatusCode(HttpStatus.NOT_FOUND.value());
        resp.setMessage("This place is not available in our database");
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
}

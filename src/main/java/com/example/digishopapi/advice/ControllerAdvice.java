package com.example.digishopapi.advice;

import com.example.digishopapi.exceptions.ExceptionObject;
import com.example.digishopapi.exceptions.FoundException;
import com.example.digishopapi.exceptions.NotFoundException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionObject> notfoundException(NotFoundException notFoundException){
        ExceptionObject exceptionObject=new ExceptionObject();
        exceptionObject.setMessage(notFoundException.getMessage());
        return  new ResponseEntity<ExceptionObject>(exceptionObject, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionObject> usernameNotFoundException(UsernameNotFoundException notFoundException){
        ExceptionObject exceptionObject=new ExceptionObject();
        exceptionObject.setMessage(notFoundException.getMessage());
        return  new ResponseEntity<ExceptionObject>(exceptionObject, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionObject> usernameNotFoundException(MethodArgumentNotValidException e){
        ExceptionObject exceptionObject=new ExceptionObject();
        e.getBindingResult().getAllErrors()
                .stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .forEach(fieldError -> exceptionObject.setMessage(fieldError.getDefaultMessage()));

        return  new ResponseEntity<ExceptionObject>(exceptionObject, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(FoundException.class)
    public ResponseEntity<ExceptionObject> foundException(FoundException foundException){
        ExceptionObject exceptionObject=new ExceptionObject();
        exceptionObject.setMessage(foundException.getMessage());
        return  new ResponseEntity<ExceptionObject>(exceptionObject, HttpStatus.CONFLICT);
    }

}

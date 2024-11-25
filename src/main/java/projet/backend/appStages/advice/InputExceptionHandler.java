package projet.backend.appStages.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import projet.backend.appStages.exception.ElementNotFoundException;


import java.util.Map;

@RestControllerAdvice
public class InputExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errorMap.put(err.getField(), err.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidInput(ConstraintViolationException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getConstraintViolations().forEach(err->{
            errorMap.put("message : ", err.getMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleElementNotFound(ElementNotFoundException ex){
        Map<String,String> erroMap = new HashMap<>();
        erroMap.put("message : ", ex.getMessage());
        return erroMap;
    }

}

/**
 * ExceptionHandingAdvice.java
 */
package com.vk.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * A controller class for handling exceptions
 */
@ControllerAdvice
public class ExceptionHandlingAdvice {
    /**
     * Handles the UserNotFoundException
     * @param ex UserNotFoundException
     * @return Error message
     */
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the Exception thrown for invalid input parameters
     * @param ex InvalidInputException
     * @return Error message
     */
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidInputErrorHandler(InvalidInputException ex) {
        return ex.getMessage();
    }
}

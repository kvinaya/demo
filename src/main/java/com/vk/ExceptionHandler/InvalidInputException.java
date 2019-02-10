/**
 * InvalidInputException.java
 */
package com.vk.ExceptionHandler;

import java.util.List;

/**
 * Exception class for Input field validation failure
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(List<String> inputFields) {
        super("Invalid input value for " + inputFields.toString());
    }
}

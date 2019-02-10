/**
 * UserNotFoundException
 */
package com.vk.ExceptionHandler;

/**
 * An Exception class for User not found
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("The user with id " + id + " does not exist");
    }
}

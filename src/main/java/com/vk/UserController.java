/**
 * UserController.java
 *
 */
package com.vk;

import com.vk.ExceptionHandler.InvalidInputException;
import com.vk.ExceptionHandler.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class to handle User CRUD operations
 */
@RestController
public class UserController {
    //private final UserRepository userRepository;

    /**UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    } **/
    @Autowired
    UserRepository userRepository;

    /**
     * Gets all the Users information
     * @return List of Users
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    /**
     * Gets User by ID
     * @param id id of the User
     * @return user
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() ->  new UserNotFoundException(id));

    }

    /**
     * Creates a new user in Database
     * @param user User to be added
     * @param result Returns the created User
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    User createUser(@RequestBody @Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            List<String> inputFields = result.getFieldErrors().stream()
                    .map(error -> {
                        return error.getField();
                    })
                    .collect(Collectors.toList());

           throw new InvalidInputException(inputFields);
         }

        return userRepository.save(user);
    }

     /**
     * Updates User details
     * @param user user to be updated
     * @param id id of the user
     * @return updated user
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, produces = "application/json")
    User updateUser(@RequestBody @Valid User user, @PathVariable long id) {
        return userRepository.findById(id)
                .map(editedUser -> {
                    editedUser.setFamilyName(user.getFamilyName());
                    editedUser.setGivenName(user.getGivenName());
                    editedUser.setEmail(user.getEmail());
                    return userRepository.save(editedUser);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    user.setCreated(new Date());
                    return userRepository.save(user);
                });
    }


    /**
     * Deletes a User
     * @param id Id of the user to be deleted
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    void deleteUser(@PathVariable long id) {
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException(id);
        }
    }


}

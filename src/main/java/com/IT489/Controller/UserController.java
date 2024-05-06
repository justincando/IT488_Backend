package com.IT489.Controller;

import com.IT489.Model.User;
import com.IT489.Repository.UserRepository;
import com.IT489.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles requests to create, retrieve, and update the stats of a User.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    UserRepository userRepository;

    /**
     * Creates the user through an HTTP request
     *
     * @param user
     * @return a user profile
     */

    @Operation(summary = "Create a new User")
    @Parameter(description = "Create a new User")
    @PostMapping
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    /**
     * Retrieves a list of all users
     *
     * @return a list of users
     */


    @Operation(summary = "Get all Users")
    @GetMapping
    @Parameter(description = "Get all Users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Allows a way to retrieve a user by ID or Username. If the ID cannot be parsed as an integer, will be able to assume string and find by username.
     *
     * @param identifier
     * @return
     */

    @Operation(summary = "Get a user by Id or Username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })

    @GetMapping("/{identifier}")
    public User findUserByIdOrUsername(@Parameter(description = "Identifier of User to be searched")
                                       @PathVariable String identifier) {
        try {
            Integer id = (Integer) Integer.parseInt(identifier);
            return userService.findUserById(id);
        } catch (NumberFormatException e) {
            // Assume identifier is a string username
            return userService.findByUsername(identifier);

        }
    }

}
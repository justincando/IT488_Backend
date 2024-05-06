package com.IT489.Service;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.IT489.Model.User;
import com.IT489.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * This class contains the methods to create and retrieve Users,
 * validate credentials against database.
 *
 */
@Service
public class UserService {

    /**
     * These are the dependencies that will be injected into the UserController
     */
    private final UserRepository userRepository;
    private final BCrypt.Hasher hasher;
    private final String SALT = ".512HxpO$qvUt!7y";

    /**
     * Constructor that allows the dependencies to be injected via IoC container
     *
     * @param userRepository UserRepository bean
     * @param hasher         Password encryptor bean
     */
    public UserService(UserRepository userRepository, BCrypt.Hasher hasher) {
        this.userRepository = userRepository;
        this.hasher = hasher;
    }

    /**
     * Creates a new user
     *
     * @param user Creates/persists a new User
     * @return a completed registration form
     */
    public User createNewUser(User user) {

        try {
            // Encrypt the password
            String encPass = encryptPassword(user.getPassword());

            // Set password as encrypted version
            user.setPassword(encPass);

            // Persist and returns a user profile
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Could not encrypt password!");
        }
    }

    /**
     * Retrieves a list of all Users
     *
     * @return The list of Users found
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a User by ID
     *
     * @param id ID to search by
     * @return The User found
     */
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }


    /**
     * Finds the User by username in the database
     *
     * @param username Username to search by
     * @return The User found or throws an exception stating User was not found
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("findByUsername: No User found!"));
    }

    /**
     * Encrypts the password of users
     *
     * @param password Password to encrypt
     * @return The encrypted password
     */
    private String encryptPassword(String password) {
        return new String(hasher.hash(4,
                SALT.getBytes(StandardCharsets.UTF_8),
                password.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
    }

    /**
     * Compares submitted password with database password
     *
     * @param userPass   Password of user being checked
     * @param dbUserPass Password of user from the database to check/validate against
     * @return True/false whether password match
     */
    public boolean comparePassword(String userPass, String dbUserPass) {

        // Encrypts the password for checking
        String encPass = encryptPassword(userPass);

        // Returns true/false if password matches
        return encPass.equals(dbUserPass);
    }


}
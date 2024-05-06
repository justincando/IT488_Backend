package com.IT489.Controller;

import com.IT489.Model.Accounts;
import com.IT489.Service.AccountService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This controller handles requests to modify account balance.
 */
@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

    @Autowired
    AccountService accountService;



    /**
     * Search a User's Account.
     *
     * @param identifier is used to search for user
     */

    @GetMapping("/{identifier}")
    public Optional<Accounts> findUAccountById(@Parameter(description = "Identifier of account to be searched")
                                       @PathVariable Integer identifier) {
            return accountService.findAccountById(identifier);
    }

    @GetMapping("/user-accounts/{userId}")
    @ResponseBody
    public Optional<Accounts> getAllAccountsByUserId(@PathVariable Integer userId) {
        return accountService.findAllAccountsByUserId(userId);

    }

    /**
     * Create a User's Account.
     *
     * @param accounts is created for user
     */

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Accounts createNewAccount(@RequestBody Accounts accounts) {
        return accountService.createNewAccount(accounts);
    }

    /**
     * Update a User's Account balance.
     *
     * @param balance updated to user Account
     */

//    @Operation(summary = "Update User's account balance")
//    @Parameter(description = "Update a User's account balance")
//    @PutMapping("/{id}")
    // creates a request where if an id is added in a put request
    //then it will put the new amount where user id is found

}
package com.IT489.Service;

import com.IT489.Model.Accounts;
import com.IT489.Model.User;
import com.IT489.Repository.AccountRepository;
import com.IT489.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * This class contains the methods to update a User's account balance
 *
 */
@Service
public class AccountService {


    @Autowired
    AccountRepository accountRepository;

//    /**
//     * Sets a User's About Me text to a new value.
//     *
//     * @param account updated to The User
//     * @param account The new account balance
//     * @return The saved user object
//     */

    public Accounts createNewAccount(Accounts accounts){return accountRepository.save(accounts);}

    public Optional<Accounts> findAllAccountsByUserId(Integer userId){return accountRepository.findByUserId(userId);}

    public Optional<Accounts> findAccountById(Integer identifier) {
        return accountRepository.findById(identifier);
    }
}
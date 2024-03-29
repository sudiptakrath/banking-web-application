package com.bank.web.controller;

import com.bank.web.entity.Account;
import com.bank.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/bank-api/account")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/createAccount/{userId}")
    public ResponseEntity<String> createAccountForUser(@PathVariable Integer userId, @RequestBody Account account) {
        try {
            accountService.createAccountForUser(userId, account);
            return ResponseEntity.ok("Account created successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-money")
    public ResponseEntity<String> addMoneyToAccount(@RequestBody Account account) {
        try {
            accountService.addMoneyToAccount(account.getAccountId(), account.getBalance());
            return ResponseEntity.ok("Money added successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding money to the account.");
        }
    }


    //Fetch in Account Component
    @GetMapping("/accountsByUserId/{userId}")
    public List<Account> findAllAccounts(@PathVariable int userId){
        List<Integer> users = new ArrayList<Integer>();
        users.add(userId);
        return accountService.getAccount(users);

    }
    @GetMapping("/accountById/{userId}")
    public Account findAccountById(@PathVariable int userId){
        return accountService.getAccountById(userId);
    }
}

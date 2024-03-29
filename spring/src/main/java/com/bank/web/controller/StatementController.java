package com.bank.web.controller;

import com.bank.web.entity.Transaction;
import com.bank.web.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-api/statements")
@CrossOrigin(origins = "http://localhost:3000")
public class StatementController {
    @Autowired
    private StatementService statementService;
    @GetMapping("/getStatement/{accountId}")
    public ResponseEntity<List<Transaction>> getAllStatementForAccount (@PathVariable Integer accountId) {
        try {
            List<Transaction> statements = statementService.getAccountStatement(accountId);
            if (statements.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(statements);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

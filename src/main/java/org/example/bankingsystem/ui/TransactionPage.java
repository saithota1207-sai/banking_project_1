package org.example.bankingsystem.ui;

import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.model.Transaction;
import org.example.bankingsystem.service.AccountService;
import org.example.bankingsystem.service.TransactionService;
import org.example.bankingsystem.util.UIUtil;

public class TransactionPage {

    private final TransactionService transactionService = new TransactionService();
    private final AccountService accountService = new AccountService();

    public void show() {
        UIUtil.printHeader("Transaction Page");
        System.out.println("View / create transactions");
    }

    public void create(Transaction tx) throws Exception {
        transactionService.create(tx);


        Account account = accountService.getAccountById(tx.getAccountId());
        System.out.println(" Transaction successful!");
        System.out.println(" Current Balance: " + account.getBalance());
    }
}

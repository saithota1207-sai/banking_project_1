package org.example.bankingsystem.service;

import org.example.bankingsystem.dao.AccountDAO;
import org.example.bankingsystem.dao.impl.AccountDAOImpl;
import org.example.bankingsystem.model.Account;

import java.util.List;

public class AccountService {
    private final AccountDAO dao = new AccountDAOImpl();

    public boolean create(Account account) {
        try {
            return dao.save(account) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Account> getAccountsByCustomerId(int customerId) {
        try {
            return dao.findByCustomerId(customerId);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // return empty list if exception
        }
    }

    public void deposit(int id, double amount) {
        // you can implement later
    }

    public void withdraw(int id, double amount) {
        // you can implement later
    }

    public Account getAccountById(int id) throws Exception {
        return dao.findById(id); // ✅ FIXED — use dao, not accountDAO
    }

    public List<Account> getAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

}

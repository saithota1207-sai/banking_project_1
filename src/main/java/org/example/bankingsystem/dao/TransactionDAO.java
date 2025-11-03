package org.example.bankingsystem.dao;

import org.example.bankingsystem.model.Transaction;
import java.util.List;

public interface TransactionDAO {
    Transaction findById(int id) throws Exception;
    List<Transaction> findByAccountId(int accountId) throws Exception;
    int save(Transaction tx) throws Exception;
}

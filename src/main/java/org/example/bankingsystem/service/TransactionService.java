package org.example.bankingsystem.service;

import org.example.bankingsystem.dao.TransactionDAO;
import org.example.bankingsystem.dao.impl.TransactionDAOImpl;
import org.example.bankingsystem.model.Transaction;
import org.example.bankingsystem.util.LoggerUtil;
import org.slf4j.Logger;

import java.util.List;

public class TransactionService {
    private final TransactionDAO dao = new TransactionDAOImpl();
    private static final Logger logger = LoggerUtil.getLogger(TransactionService.class);

    public Transaction getById(int id) throws Exception { return dao.findById(id); }
    public List<Transaction> getByAccount(int accountId) throws Exception { return dao.findByAccountId(accountId); }
    public int create(Transaction t) throws Exception { return dao.save(t); }
}

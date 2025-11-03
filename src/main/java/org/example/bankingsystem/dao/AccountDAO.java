package org.example.bankingsystem.dao;

import org.example.bankingsystem.model.Account;
import java.util.List;

public interface AccountDAO {
    Account findById(int id) throws Exception;
    List<Account> findByCustomerId(int customerId) throws Exception;
    List<Account> findAll() throws Exception;
    int save(Account account) throws Exception;
    void update(Account account) throws Exception;
    void delete(int id) throws Exception;
}

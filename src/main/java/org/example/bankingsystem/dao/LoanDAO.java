package org.example.bankingsystem.dao;

import org.example.bankingsystem.model.Loan;
import java.util.List;

public interface LoanDAO {
    Loan findById(int id) throws Exception;
    List<Loan> findByCustomerId(int customerId) throws Exception;
    int save(Loan loan) throws Exception;
    void update(Loan loan) throws Exception;
    void delete(int id) throws Exception;
}

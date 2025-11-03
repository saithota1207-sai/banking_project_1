package org.example.bankingsystem.service;

import org.example.bankingsystem.dao.LoanDAO;
import org.example.bankingsystem.dao.impl.LoanDAOImpl;
import org.example.bankingsystem.model.Loan;
import org.example.bankingsystem.util.LoggerUtil;
import org.slf4j.Logger;

import java.util.List;

public class LoanService {
    private final LoanDAO dao = new LoanDAOImpl();
    private static final Logger logger = LoggerUtil.getLogger(LoanService.class);

    public Loan getById(int id) throws Exception { return dao.findById(id); }
    public List<Loan> getByCustomer(int customerId) throws Exception { return dao.findByCustomerId(customerId); }
    public int create(Loan l) throws Exception { return dao.save(l); }
    public void update(Loan l) throws Exception { dao.update(l); }
    public void delete(int id) throws Exception { dao.delete(id); }
}

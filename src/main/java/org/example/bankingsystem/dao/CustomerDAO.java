package org.example.bankingsystem.dao;

import org.example.bankingsystem.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    Customer findById(int id);
    Customer findByName(String name);
    boolean validateCustomer(String name, String password);
    boolean save(Customer customer);
    List<Customer> findAll();

    Customer login(String username, String password) throws SQLException;
}

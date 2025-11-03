package org.example.bankingsystem.service;

import org.example.bankingsystem.dao.CustomerDAO;
import org.example.bankingsystem.dao.impl.CustomerDAOImpl;
import org.example.bankingsystem.model.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAO dao = new CustomerDAOImpl();


    public Customer loginAndFetchUser(String username, String password) {
        try {
            return dao.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Customer getCustomerDetails(String username) {
        try {
            return dao.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Customer> getAllCustomers() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

package org.example.bankingsystem.ui;

import org.example.bankingsystem.dao.impl.CustomerDAOImpl;
import org.example.bankingsystem.model.Customer;
import java.util.Scanner;

public class Dashboard {
    private final CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public void show(String role, String username) {
        Scanner sc = new Scanner(System.in);

        if (role.equalsIgnoreCase("admin")) {
            System.out.println("\n=== Admin Dashboard ===");
            System.out.println("1. Accounts\n2. Customers\n3. Transactions\n4. Loans\n5. Cards\n6. Branches\n7. Admins\n8. Exit");
        }
        else if (role.equalsIgnoreCase("customer")) {
            Customer c = customerDAO.findByName(username);
            if (c != null) {
                System.out.println("\nWelcome, " + c.getName() + "!");
                System.out.println("Email: " + c.getEmail());
                System.out.println("Phone: " + c.getPhone());
                System.out.println("Address: " + c.getAddress());
            } else {
                System.out.println(" Customer details not found.");
            }

            System.out.println("\n=== Customer Dashboard ===");
            System.out.println("1. View Balance\n2. View Transactions\n3. Apply for Loan\n4. Exit");
        }
        else {
            System.out.println("Invalid role.");
        }
    }
}

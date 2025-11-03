package org.example.bankingsystem.ui;

import org.example.bankingsystem.dao.impl.AdminDAOImpl;
import org.example.bankingsystem.dao.impl.CustomerDAOImpl;
import java.util.Scanner;

public class LoginPage {
    private final AdminDAOImpl adminDAO = new AdminDAOImpl();
    private final CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public String[] login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Login as (admin/customer): ");
        String role = scanner.nextLine().trim().toLowerCase();

        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        boolean success = false;

        if (role.equals("admin")) {
            success = adminDAO.validateAdmin(username, password);
        } else if (role.equals("customer")) {
            success = customerDAO.validateCustomer(username, password);
        }

        if (success) {
            System.out.println(" Login successful! Welcome, " + username);
            return new String[]{role, username};
        } else {
            System.out.println(" Login failed. Invalid credentials.");
            return null;
        }
    }
}

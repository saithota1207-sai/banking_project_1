package org.example.bankingsystem.ui;

import org.example.bankingsystem.model.Customer;
import org.example.bankingsystem.service.CustomerService;

import java.util.Scanner;

public class CustomerLoginPage {
    private final CustomerService service = new CustomerService();

    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Banking System Login ===");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();


        Customer loggedInUser = service.loginAndFetchUser(name, password);

        if (loggedInUser == null) {
            System.out.println(" Invalid name or password!");
            return;
        }

        System.out.println(" Login successful!");
        System.out.println("Welcome, " + loggedInUser.getName() + " (" + loggedInUser.getRole() + ")");
        System.out.println("Email: " + loggedInUser.getEmail());
        System.out.println("Phone: " + loggedInUser.getPhone());
        System.out.println("Address: " + loggedInUser.getAddress());

        // Redirect to menu based on role
        if ("admin".equalsIgnoreCase(loggedInUser.getRole())) {
            System.out.println(" Redirecting to Admin Menu...");
            // You can call: new AdminMenu().show();
        } else {
            System.out.println(" Redirecting to Customer Menu...");

        }
    }
}

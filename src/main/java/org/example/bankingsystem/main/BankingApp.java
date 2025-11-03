package org.example.bankingsystem.main;

import org.example.bankingsystem.model.*;
import org.example.bankingsystem.service.*;

import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        BranchService branchService = new BranchService();
        LoanService loanService = new LoanService();
        TransactionService transactionService = new TransactionService();
        CardService cardService = new CardService();

        System.out.print("Enter username: ");
        String name = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        Customer loggedInUser = customerService.loginAndFetchUser(name, password);

        if (loggedInUser == null) {
            System.out.println(" Invalid credentials!");
            return;
        }

        System.out.println(" Login successful! Welcome, " + loggedInUser.getName() + " (" + loggedInUser.getRole() + ")");

        if ("admin".equalsIgnoreCase(loggedInUser.getRole())) {
            showAdminMenu(sc, branchService, accountService, customerService);
        } else {
            showCustomerMenu(sc, loggedInUser, accountService, loanService, transactionService, cardService, branchService);
        }
    }

    // ===================== CUSTOMER MENU =====================
    private static void showCustomerMenu(Scanner sc, Customer customer, AccountService accountService,
                                         LoanService loanService, TransactionService transactionService,
                                         CardService cardService, BranchService branchService) throws Exception {

        boolean running = true;
        while (running) {
            System.out.println("\n===== Customer Menu =====");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Create Loan");
            System.out.println("4. Make Transaction");
            System.out.println("5. Issue Card");
            System.out.println("6. Create Branch");
            System.out.println("7. View Branches");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter branch ID: ");
                    int branchId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter account type: ");
                    String type = sc.nextLine();
                    Account acc = new Account(0, customer.getId(), branchId, 0.0, type);
                    accountService.create(acc);
                    System.out.println(" Account created successfully!");
                }
                case 2 -> {
                    var accounts = accountService.getAccountsByCustomerId(customer.getId());
                    if (accounts.isEmpty()) System.out.println("No accounts found.");
                    else accounts.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Enter loan amount: ");
                    double principal = sc.nextDouble();
                    Loan loan = new Loan(0, customer.getId(), principal, principal);
                    loanService.create(loan);
                    System.out.println(" Loan created successfully!");
                }
                case 4 -> {
                    System.out.print("Enter account ID: ");
                    int accId = sc.nextInt();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter type (deposit/withdraw): ");
                    String type = sc.nextLine();
                    Transaction tx = new Transaction(0, accId, amt, type);
                    transactionService.create(tx);
                    System.out.println(" Transaction recorded successfully!");
                }
                case 5 -> {
                    System.out.print("Enter card type: ");
                    String type = sc.nextLine();
                    Card card = new Card(0, customer.getId(), "CARD" + System.currentTimeMillis(), type);
                    cardService.create(card);
                    System.out.println(" Card issued successfully!");
                }
                case 6 -> {
                    System.out.print("Enter Branch Name: ");
                    String bName = sc.nextLine();
                    System.out.print("Enter Branch Location: ");
                    String loc = sc.nextLine();
                    Branch branch = new Branch(bName, loc);
                    branchService.create(branch);
                    System.out.println(" Branch created successfully!");
                }
                case 7 -> {
                    var branches = branchService.getAll();
                    if (branches.isEmpty()) System.out.println("No branches available.");
                    else branches.forEach(System.out::println);
                }
                case 8 -> {
                    System.out.println(" Exiting... Thank you!");
                    running = false;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ===================== ADMIN MENU =====================
    private static void showAdminMenu(Scanner sc, BranchService branchService,
                                      AccountService accountService, CustomerService customerService) throws Exception {

        boolean running = true;
        while (running) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Create Branch");
            System.out.println("2. View Branches");
            System.out.println("3. View All Accounts");
            System.out.println("4. View All Customers");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Branch Name: ");
                    String bName = sc.nextLine();
                    System.out.print("Enter Branch Location: ");
                    String loc = sc.nextLine();
                    System.out.println(bName+" "+loc);
                    Branch branch = new Branch(bName, loc);
                    branchService.create(branch);
                    System.out.println(" Branch created successfully!");
                }
                case 2 -> {
                    var branches = branchService.getAll();
                    if (branches.isEmpty()) System.out.println("No branches available.");
                    else branches.forEach(System.out::println);
                }
                case 3 -> {
                    var accounts = accountService.getAll();
                    if (accounts.isEmpty()) {
                        System.out.println("No accounts found.");
                    } else {
                        accounts.forEach(acc -> {
                            System.out.println(acc);
                            System.out.println(" Balance: " + acc.getBalance());
                        });

            }
                }
                case 4 -> {
                    var customers = customerService.getAllCustomers();
                    if (customers.isEmpty()) System.out.println("No customers found.");
                    else customers.forEach(System.out::println);
                }
                case 5 -> {
                    System.out.println("     Exiting Admin Panel...");
                    running = false;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

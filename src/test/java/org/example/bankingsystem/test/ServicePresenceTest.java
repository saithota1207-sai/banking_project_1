package org.example.bankingsystem.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServicePresenceTest {

    @Test
    public void testService1() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.service.AccountService");
        assertNotNull(c, "org.example.bankingsystem.service.AccountService should be present");
    }

    @Test
    public void testService2() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.service.AdminService");
        assertNotNull(c, "org.example.bankingsystem.service.AdminService should be present");
    }

    @Test
    public void testService3() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.service.BranchService");
        assertNotNull(c, "org.example.bankingsystem.service.BranchService should be present");
    }

    @Test
    public void testService4() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.service.CardService");
        assertNotNull(c, "org.example.bankingsystem.service.CardService should be present");
    }

    @Test
    public void testService5() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.service.CustomerService");
        assertNotNull(c, "org.example.bankingsystem.service.CustomerService should be present");
    }

    @Test
    public void testService6() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.service.LoanService");
        assertNotNull(c, "org.example.bankingsystem.service.LoanService should be present");
    }

    @Test
    public void testService7() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.service.TransactionService");
        assertNotNull(c, "org.example.bankingsystem.service.TransactionService should be present");
    }

}

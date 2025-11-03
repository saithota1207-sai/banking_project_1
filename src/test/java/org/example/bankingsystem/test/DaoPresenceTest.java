package org.example.bankingsystem.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DaoPresenceTest {

    @Test
    public void testDao1() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.dao.AccountDAO");
        assertNotNull(c, "org.example.bankingsystem.dao.AccountDAO should be present");
    }

    @Test
    public void testDao2() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.dao.AdminDAO");
        assertNotNull(c, "org.example.bankingsystem.dao.AdminDAO should be present");
    }

    @Test
    public void testDao3() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.dao.BranchDAO");
        assertNotNull(c, "org.example.bankingsystem.dao.BranchDAO should be present");
    }

    @Test
    public void testDao4() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.dao.CardDAO");
        assertNotNull(c, "org.example.bankingsystem.dao.CardDAO should be present");
    }

    @Test
    public void testDao5() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.dao.CustomerDAO");
        assertNotNull(c, "org.example.bankingsystem.dao.CustomerDAO should be present");
    }

    @Test
    public void testDao6() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.dao.LoanDAO");
        assertNotNull(c, "org.example.bankingsystem.dao.LoanDAO should be present");
    }

    @Test
    public void testDao7() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.dao.TransactionDAO");
        assertNotNull(c, "org.example.bankingsystem.dao.TransactionDAO should be present");
    }

}

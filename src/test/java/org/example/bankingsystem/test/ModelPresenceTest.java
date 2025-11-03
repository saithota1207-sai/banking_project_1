package org.example.bankingsystem.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelPresenceTest {

    @Test
    public void testModel1() throws Exception {
        // ensure class exists at runtime
        Class<?> c = Class.forName("org.example.bankingsystem.model.Account");
        assertNotNull(c, "org.example.bankingsystem.model.Account should be present");
    }

    @Test
    public void testModel2() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.model.Admin");
        assertNotNull(c, "org.example.bankingsystem.model.Admin should be present");
    }

    @Test
    public void testModel3() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.model.Branch");
        assertNotNull(c, "org.example.bankingsystem.model.Branch should be present");
    }

    @Test
    public void testModel4() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.model.Card");
        assertNotNull(c, "org.example.bankingsystem.model.Card should be present");
    }

    @Test
    public void testModel5() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.model.Customer");
        assertNotNull(c, "org.example.bankingsystem.model.Customer should be present");
    }

    @Test
    public void testModel6() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.model.Loan");
        assertNotNull(c, "org.example.bankingsystem.model.Loan should be present");
    }

    @Test
    public void testModel7() throws Exception {

        Class<?> c = Class.forName("org.example.bankingsystem.model.Transaction");
        assertNotNull(c, "org.example.bankingsystem.model.Transaction should be present");
    }

}

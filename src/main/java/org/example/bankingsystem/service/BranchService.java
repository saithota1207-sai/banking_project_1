package org.example.bankingsystem.service;

import org.example.bankingsystem.dao.BranchDAO;
import org.example.bankingsystem.dao.impl.BranchDAOImpl;
import org.example.bankingsystem.model.Branch;
import java.util.List;

public class BranchService {
    private final BranchDAO dao = new BranchDAOImpl();

    public boolean create(Branch branch) {
        try {
            return dao.save(branch) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Branch> getAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

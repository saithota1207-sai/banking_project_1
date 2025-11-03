package org.example.bankingsystem.dao;

import org.example.bankingsystem.model.Branch;
import java.util.List;

public interface BranchDAO {
    Branch findById(int id) throws Exception;
    List<Branch> findAll() throws Exception;
    int save(Branch branch) throws Exception;
    void update(Branch branch) throws Exception;
    void delete(int id) throws Exception;
}

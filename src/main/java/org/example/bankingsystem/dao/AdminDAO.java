package org.example.bankingsystem.dao;

import org.example.bankingsystem.model.Admin;
import java.util.List;

public interface AdminDAO {
    Admin findById(int id);
    Admin findByUsername(String username);
    boolean save(Admin admin);
    boolean update(Admin admin);
    boolean delete(int id);
    boolean validateAdmin(String username, String password);
    List<Admin> findAll();
}

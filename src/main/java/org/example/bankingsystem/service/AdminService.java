package org.example.bankingsystem.service;

import org.example.bankingsystem.dao.AdminDAO;
import org.example.bankingsystem.dao.impl.AdminDAOImpl;
import org.example.bankingsystem.model.Admin;
import org.example.bankingsystem.util.LoggerUtil;
import org.slf4j.Logger;

public class AdminService {
    private final AdminDAO dao = new AdminDAOImpl();
    private static final Logger logger = LoggerUtil.getLogger(AdminService.class);

    public Admin getById(int id) { return dao.findById(id); }
    public Admin getByUsername(String username) { return dao.findByUsername(username); }
    public boolean create(Admin a) { return dao.save(a); }
    public boolean update(Admin a) { return dao.update(a); }
    public boolean delete(int id) { return dao.delete(id); }
}

package org.example.bankingsystem.dao.impl;

import org.example.bankingsystem.dao.LoanDAO;
import org.example.bankingsystem.model.Loan;
import org.example.bankingsystem.util.DBConnection;
import org.example.bankingsystem.util.LoggerUtil;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAOImpl implements LoanDAO {
    private static final Logger logger = LoggerUtil.getLogger(LoanDAOImpl.class);

    @Override
    public Loan findById(int id) throws Exception {
        String sql = "SELECT id, customer_id, principal, outstanding FROM loans WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return map(rs); }
        } catch (SQLException e) { logger.error("Error in findById: {}", e.getMessage()); throw e; }
        return null;
    }

    @Override
    public List<Loan> findByCustomerId(int customerId) throws Exception {
        List<Loan> list = new ArrayList<>();
        String sql = "SELECT id, customer_id, principal, outstanding FROM loans WHERE customer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(map(rs)); }
        } catch (SQLException e) { logger.error("Error in findByCustomerId: {}", e.getMessage()); throw e; }
        return list;
    }

    @Override
    public int save(Loan loan) throws Exception {
        String sql = "INSERT INTO loans (customer_id, principal, outstanding) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, loan.getCustomerId());
            ps.setDouble(2, loan.getPrincipal());
            ps.setDouble(3, loan.getOutstanding());
            int affected = ps.executeUpdate();
            if (affected == 0) throw new SQLException("Insert failed");
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) { int id = keys.getInt(1); loan.setId(id); return id; }
                else throw new SQLException("No id generated");
            }
        } catch (SQLException e) { logger.error("Error in save: {}", e.getMessage()); throw e; }
    }

    @Override
    public void update(Loan loan) throws Exception {
        String sql = "UPDATE loans SET customer_id=?, principal=?, outstanding=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, loan.getCustomerId());
            ps.setDouble(2, loan.getPrincipal());
            ps.setDouble(3, loan.getOutstanding());
            ps.setInt(4, loan.getId());
            ps.executeUpdate();
        } catch (SQLException e) { logger.error("Error in update: {}", e.getMessage()); throw e; }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM loans WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
        } catch (SQLException e) { logger.error("Error in delete: {}", e.getMessage()); throw e; }
    }

    private Loan map(ResultSet rs) throws SQLException {
        return new Loan(rs.getInt("id"), rs.getInt("customer_id"), rs.getDouble("principal"), rs.getDouble("outstanding"));
    }
}

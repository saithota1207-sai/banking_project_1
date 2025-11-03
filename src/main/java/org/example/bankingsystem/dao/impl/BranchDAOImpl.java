package org.example.bankingsystem.dao.impl;

import org.example.bankingsystem.dao.BranchDAO;
import org.example.bankingsystem.model.Branch;
import org.example.bankingsystem.util.DBConnection;
import org.example.bankingsystem.util.LoggerUtil;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDAOImpl implements BranchDAO {
    private static final Logger logger = LoggerUtil.getLogger(BranchDAOImpl.class);

    @Override
    public Branch findById(int id) throws Exception {
        String sql = "SELECT id, name, address FROM branches WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return map(rs); }
        } catch (SQLException e) { logger.error("Error in findById: {}", e.getMessage()); throw e; }
        return null;
    }

    @Override
    public List<Branch> findAll() throws Exception {
        List<Branch> list = new ArrayList<>();
        String sql = "SELECT id, name, address FROM branches";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) { while (rs.next()) list.add(map(rs)); }
        catch (SQLException e) { logger.error("Error in findAll: {}", e.getMessage()); throw e; }
        return list;
    }

    @Override
    public int save(Branch branch) throws Exception {
        String sql = "INSERT INTO branches(name, address) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, branch.getName());
            ps.setString(2, branch.getAddress());
            int affected = ps.executeUpdate();
            if (affected == 0) throw new SQLException("Insert failed");
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) { int id = keys.getInt(1); branch.setId(id); return id; }
                else throw new SQLException("No id generated");
            }
        } catch (SQLException e) { logger.error("Error in save: {}", e.getMessage()); throw e; }
    }

    @Override
    public void update(Branch branch) throws Exception {
        String sql = "UPDATE branches SET name=?, address=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, branch.getName());
            ps.setString(2, branch.getAddress());
            ps.setInt(3, branch.getId());
            ps.executeUpdate();
        } catch (SQLException e) { logger.error("Error in update: {}", e.getMessage()); throw e; }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM branches WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
        } catch (SQLException e) { logger.error("Error in delete: {}", e.getMessage()); throw e; }
    }

    private Branch map(ResultSet rs) throws SQLException {
        return new Branch(rs.getInt("id"), rs.getString("name"), rs.getString("address"));
    }
}

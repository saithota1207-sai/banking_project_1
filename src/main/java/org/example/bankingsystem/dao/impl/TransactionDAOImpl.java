package org.example.bankingsystem.dao.impl;

import org.example.bankingsystem.dao.TransactionDAO;
import org.example.bankingsystem.model.Transaction;
import org.example.bankingsystem.util.DBConnection;
import org.example.bankingsystem.util.LoggerUtil;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.bankingsystem.dao.impl.AccountDAOImpl.log;

public class TransactionDAOImpl implements TransactionDAO {
    private static final Logger logger = LoggerUtil.getLogger(TransactionDAOImpl.class);

    @Override
    public Transaction findById(int id) throws Exception {
        String sql = "SELECT id, account_id, amount, type, timestamp FROM transactions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return map(rs); }
        } catch (SQLException e) { logger.error("Error in findById: {}", e.getMessage()); throw e; }
        return null;
    }

    @Override
    public List<Transaction> findByAccountId(int accountId) throws Exception {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT id, account_id, amount, type, timestamp FROM transactions WHERE account_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(map(rs)); }
        } catch (SQLException e) { logger.error("Error in findByAccountId: {}", e.getMessage()); throw e; }
        return list;
    }

    @Override
    public int save(Transaction tx) throws Exception {
        String sql = "INSERT INTO transactions (account_id, amount, type, timestamp) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tx.getAccountId());
            ps.setDouble(2, tx.getAmount());
            ps.setString(3, tx.getType());
            int rows = ps.executeUpdate();

            // Update account balance
            String updateSql;
            if (tx.getType().equalsIgnoreCase("deposit")) {
                updateSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            } else if (tx.getType().equalsIgnoreCase("withdraw")) {
                updateSql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
            } else {
                throw new Exception("Invalid transaction type");
            }

            try (PreparedStatement ps2 = conn.prepareStatement(updateSql)) {
                ps2.setDouble(1, tx.getAmount());
                ps2.setInt(2, tx.getAccountId());
                ps2.executeUpdate();
            }

            return rows;
        } catch (SQLException e) {
            log.error("Error in save: {}", e.getMessage());
            throw e;
        }
    }

    private Transaction map(ResultSet rs) throws SQLException {
        Transaction t = new Transaction();
        t.setId(rs.getInt("id"));
        t.setAccountId(rs.getInt("account_id"));
        t.setAmount(rs.getDouble("amount"));
        t.setType(rs.getString("type"));
        t.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        return t;
    }
}

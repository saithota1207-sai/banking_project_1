package org.example.bankingsystem.dao.impl;

import org.example.bankingsystem.dao.AccountDAO;
import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    static final Logger log = LoggerFactory.getLogger(AccountDAOImpl.class);

    @Override
    public Account findById(int id) throws Exception {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapAccount(rs);
            }
        } catch (SQLException e) {
            log.error("Error in findById: {}", e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public List<Account> findByCustomerId(int customerId) throws Exception {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE customer_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapAccount(rs));
            }
            log.debug("Found {} accounts for customerId={}", list.size(), customerId);
        } catch (SQLException e) {
            log.error("Error in findByCustomerId: {}", e.getMessage());
            throw e;
        }
        return list;
    }

    @Override
    public List<Account> findAll() throws SQLException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Account acc = new Account(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("branch_id"),
                        rs.getDouble("balance"),
                        rs.getString("type")
                );
                list.add(acc);
            }
        }
        return list;
    }

    @Override
    public int save(Account account) throws SQLException {
        String sql = "INSERT INTO accounts (customer_id, branch_id, balance, type) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, account.getCustomerId());
            ps.setInt(2, account.getBranchId());
            ps.setDouble(3, account.getBalance());
            ps.setString(4, account.getType() != null ? account.getType() : "SAVINGS");
            int rows = ps.executeUpdate();
            log.info("Account saved for customerId={}, rows={}", account.getCustomerId(), rows);
            return rows;
        } catch (SQLException e) {
            log.error("Error in save: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(Account account) throws Exception {
        String sql = "UPDATE accounts SET balance = ?, account_type = ?, branch_id = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, account.getBalance());
            ps.setString(2, account.getType());
            ps.setInt(3, account.getBranchId());
            ps.setInt(4, account.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error in update: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error in delete: {}", e.getMessage());
            throw e;
        }
    }

    private Account mapAccount(ResultSet rs) throws SQLException {
        String accountType = rs.getString("account_type");
        if (accountType == null || accountType.isEmpty()) {
            accountType = "SAVINGS";
        }
        double balance = rs.getDouble("balance");

        return new Account(
                rs.getInt("id"),
                rs.getInt("customer_id"),
                rs.getInt("branch_id"),
                balance,
                accountType
        );
    }
}

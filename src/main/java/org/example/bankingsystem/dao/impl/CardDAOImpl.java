package org.example.bankingsystem.dao.impl;

import org.example.bankingsystem.dao.CardDAO;
import org.example.bankingsystem.model.Card;
import org.example.bankingsystem.util.DBConnection;
import org.example.bankingsystem.util.LoggerUtil;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDAOImpl implements CardDAO {
    private static final Logger logger = LoggerUtil.getLogger(CardDAOImpl.class);

    @Override
    public Card findById(int id) throws Exception {
        String sql = "SELECT id, customer_id, card_number, type FROM cards WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return map(rs); }
        } catch (SQLException e) { logger.error("Error in findById: {}", e.getMessage()); throw e; }
        return null;
    }

    @Override
    public List<Card> findByCustomerId(int customerId) throws Exception {
        List<Card> list = new ArrayList<>();
        String sql = "SELECT id, customer_id, card_number, type FROM cards WHERE customer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) list.add(map(rs)); }
        } catch (SQLException e) { logger.error("Error in findByCustomerId: {}", e.getMessage()); throw e; }
        return list;
    }

    @Override
    public int save(Card card) throws Exception {
        String sql = "INSERT INTO cards (customer_id, card_number, type) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, card.getCustomerId());
            ps.setString(2, card.getCardNumber());
            ps.setString(3, card.getType());
            int affected = ps.executeUpdate();
            if (affected == 0) throw new SQLException("Insert failed");
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) { int id = keys.getInt(1); card.setId(id); return id; }
                else throw new SQLException("No id generated");
            }
        } catch (SQLException e) { logger.error("Error in save: {}", e.getMessage()); throw e; }
    }

    @Override
    public void update(Card card) throws Exception {
        String sql = "UPDATE cards SET customer_id=?, card_number=?, type=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, card.getCustomerId());
            ps.setString(2, card.getCardNumber());
            ps.setString(3, card.getType());
            ps.setInt(4, card.getId());
            ps.executeUpdate();
        } catch (SQLException e) { logger.error("Error in update: {}", e.getMessage()); throw e; }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM cards WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
        } catch (SQLException e) { logger.error("Error in delete: {}", e.getMessage()); throw e; }
    }

    private Card map(ResultSet rs) throws SQLException {
        return new Card(rs.getInt("id"), rs.getInt("customer_id"), rs.getString("card_number"), rs.getString("type"));
    }
}

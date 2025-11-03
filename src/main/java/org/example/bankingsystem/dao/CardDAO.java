package org.example.bankingsystem.dao;

import org.example.bankingsystem.model.Card;
import java.util.List;

public interface CardDAO {
    Card findById(int id) throws Exception;
    List<Card> findByCustomerId(int customerId) throws Exception;
    int save(Card card) throws Exception;
    void update(Card card) throws Exception;
    void delete(int id) throws Exception;
}

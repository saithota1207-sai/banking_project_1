package org.example.bankingsystem.service;

import org.example.bankingsystem.dao.CardDAO;
import org.example.bankingsystem.dao.impl.CardDAOImpl;
import org.example.bankingsystem.model.Card;
import org.example.bankingsystem.util.LoggerUtil;
import org.slf4j.Logger;

import java.util.List;

public class CardService {
    private final CardDAO dao = new CardDAOImpl();
    private static final Logger logger = LoggerUtil.getLogger(CardService.class);

    public Card getById(int id) throws Exception { return dao.findById(id); }
    public List<Card> getByCustomer(int customerId) throws Exception { return dao.findByCustomerId(customerId); }
    public int create(Card c) throws Exception { return dao.save(c); }
    public void update(Card c) throws Exception { dao.update(c); }
    public void delete(int id) throws Exception { dao.delete(id); }
}

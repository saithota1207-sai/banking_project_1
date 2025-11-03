package org.example.bankingsystem.model;

public class Card {
    private int id; private int customerId; private String cardNumber; private String type;
    public Card(){} public Card(int id,int customerId,String cardNumber,String type){this.id=id;this.customerId=customerId;this.cardNumber=cardNumber;this.type=type;}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public int getCustomerId(){return customerId;} public void setCustomerId(int customerId){this.customerId=customerId;}
    public String getCardNumber(){return cardNumber;} public void setCardNumber(String cardNumber){this.cardNumber=cardNumber;}
    public String getType(){return type;} public void setType(String type){this.type=type;}
    @Override public String toString(){ return "Card{id="+id+", customerId="+customerId+", cardNumber='"+cardNumber+"', type='"+type+"'}";}
}

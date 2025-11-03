package org.example.bankingsystem.model;

public class Account {
    private int id;
    private int customerId;
    private int branchId;
    private double balance;
    private String type;

    public Account() {}
    public Account(int id, int customerId, int branchId, double balance, String type) {
        this.id = id; this.customerId = customerId; this.branchId = branchId; this.balance = balance; this.type = type;
    }
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public int getCustomerId(){return customerId;} public void setCustomerId(int customerId){this.customerId=customerId;}
    public int getBranchId(){return branchId;} public void setBranchId(int branchId){this.branchId=branchId;}
    public double getBalance(){return balance;} public void setBalance(double balance){this.balance=balance;}
    public String getType(){return type;} public void setType(String type){this.type=type;}
    @Override public String toString(){ return "Account{id="+id+", customerId="+customerId+", branchId="+branchId+", balance="+balance+", type='"+type+"'}";}


}

package org.example.bankingsystem.model;

public class Loan {
    private int id; private int customerId; private double principal; private double outstanding;
    public Loan(){} public Loan(int id,int customerId,double principal,double outstanding){this.id=id;this.customerId=customerId;this.principal=principal;this.outstanding=outstanding;}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public int getCustomerId(){return customerId;} public void setCustomerId(int customerId){this.customerId=customerId;}
    public double getPrincipal(){return principal;} public void setPrincipal(double principal){this.principal=principal;}
    public double getOutstanding(){return outstanding;} public void setOutstanding(double outstanding){this.outstanding=outstanding;}
    @Override public String toString(){ return "Loan{id="+id+", customerId="+customerId+", principal="+principal+", outstanding="+outstanding+"}";}
}

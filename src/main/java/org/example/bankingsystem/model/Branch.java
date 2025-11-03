package org.example.bankingsystem.model;

public class Branch {
    private int id;
    private String name;
    private String address;
    public Branch(){} public Branch(int id,String name,String address){this.id=id;this.name=name;this.address=address;}

    public Branch(String bName, String loc) {
        this.name = bName;
        this.address = loc;
    }

    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getAddress(){return address;} public void setAddress(String address){this.address=address;}
    @Override public String toString(){ return "Branch{id="+id+", name='"+name+"', address='"+address+"'}";}
}

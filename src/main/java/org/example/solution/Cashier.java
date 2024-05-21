package org.example.solution;

public class Cashier {
    private static int cashierCount = 0;
    private final int id;
    private final String name;
    private double salary;

    public Cashier(String name, double salary){
        this.id = generateId();
        this.name = name;
        this.salary = salary;
    }

    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public double getSalary(){
        return this.salary;
    }

    public void setSalary(double newSalary){
        this.salary = newSalary;
    }

    private synchronized int generateId(){
        return cashierCount++;
    }
}

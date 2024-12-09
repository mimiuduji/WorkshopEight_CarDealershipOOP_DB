package com.ps;

public class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private String vehicle;
    // Abstract methods for subclasses to implement
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

    // Constructor, getters, and setters
}

package com.ps;

public class SalesContract {
    private double salesTax;
    private double recordingFee = 100;
    private double processingFee;
    private boolean isFinanced;

    // Override getTotalPrice() and getMonthlyPayment()
    @Override
    public double getTotalPrice() {
        // Calculation logic here
    }

    @Override
    public double getMonthlyPayment() {
        // Calculation logic here
    }
}

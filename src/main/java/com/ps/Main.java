package com.ps;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
}
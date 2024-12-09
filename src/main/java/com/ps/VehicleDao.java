package com.ps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VehicleDao {
    public void addVehicle(String vin, String make, String model, int year, String color, double price, boolean sold) {
        String sql = "INSERT INTO vehicles (vin, make, model, year, color, price, sold) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, vin);
            preparedStatement.setString(2, make);
            preparedStatement.setString(3, model);
            preparedStatement.setInt(4, year);
            preparedStatement.setString(5, color);
            preparedStatement.setDouble(6, price);
            preparedStatement.setBoolean(7, sold);

            preparedStatement.executeUpdate();
            System.out.println("Vehicle added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findVehicleByVin(String vin) {
        String sql = "SELECT * FROM vehicles WHERE vin = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, vin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("VIN: " + resultSet.getString("vin"));
                System.out.println("Make: " + resultSet.getString("make"));
                System.out.println("Model: " + resultSet.getString("model"));
                System.out.println("Year: " + resultSet.getInt("year"));
                System.out.println("Color: " + resultSet.getString("color"));
                System.out.println("Price: $" + resultSet.getDouble("price"));
                System.out.println("Sold: " + resultSet.getBoolean("sold"));
            } else {
                System.out.println("Vehicle not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

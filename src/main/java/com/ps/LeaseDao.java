package com.ps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaseDao {
    private static final String INSERT_LEASE_CONTRACT =
        "INSERT INTO lease_contracts (vehicle_vin, customer_name, lease_term, monthly_payment) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_LEASES =
            "SELECT * FROM lease_contracts";
    private static final String DELETE_LEASE_CONTRACT =
            "DELETE FROM lease_contracts WHERE id = ?";
    private static final String GET_LEASE_BY_ID =
            "SELECT * FROM lease_contracts WHERE id = ?";

    // Method to save a lease contract to the database
    public void saveLeaseContract(LeaseContract leaseContract) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_LEASE_CONTRACT)) {

            statement.setString(1, leaseContract.getVehicleVin());
            statement.setString(2, leaseContract.getCustomerName());
            statement.setInt(3, leaseContract.getLeaseTerm());
            statement.setDouble(4, leaseContract.getMonthlyPayment());

            statement.executeUpdate();
            System.out.println("Lease contract saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all lease contracts from the database
    public List<LeaseContract> getAllLeaseContracts() {
        List<LeaseContract> leaseContracts = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_LEASES)) {

            while (resultSet.next()) {
                LeaseContract leaseContract = new LeaseContract(
                        resultSet.getInt("id"),
                        resultSet.getString("vehicle_vin"),
                        resultSet.getString("customer_name"),
                        resultSet.getInt("lease_term"),
                        resultSet.getDouble("monthly_payment")
                );
                leaseContracts.add(leaseContract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaseContracts;
    }

    // Method to get a lease contract by ID
    public LeaseContract getLeaseById(int id) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LEASE_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new LeaseContract(
                            resultSet.getInt("id"),
                            resultSet.getString("vehicle_vin"),
                            resultSet.getString("customer_name"),
                            resultSet.getInt("lease_term"),
                            resultSet.getDouble("monthly_payment")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to delete a lease contract by ID
    public void deleteLeaseContract(int id) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_LEASE_CONTRACT)) {

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Lease contract deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

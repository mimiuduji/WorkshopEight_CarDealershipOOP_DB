package com.ps;

public class SalesDao {
   import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



        public void addSalesContract(SalesContract contract) {
            String sql = "INSERT INTO sales_contracts (id, vehicle_id, customer_id, sale_price, tax, total_price, down_payment, monthly_payment) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection = DatabaseConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, contract.getId());
                preparedStatement.setInt(2, contract.getVehicleId());
                preparedStatement.setInt(3, contract.getCustomerId());
                preparedStatement.setDouble(4, contract.getSalePrice());
                preparedStatement.setDouble(5, contract.getTax());
                preparedStatement.setDouble(6, contract.getTotalPrice());
                preparedStatement.setDouble(7, contract.getDownPayment());
                preparedStatement.setDouble(8, contract.getMonthlyPayment());

                preparedStatement.executeUpdate();
                System.out.println("Sales contract added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

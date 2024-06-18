package main.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.model.DatabaseConnection;
import main.model.Transaction;

public class TransactionController {
	

	public static void addTransaction(Transaction transaction) {
		String query = "INSERT INTO Transactions (transaction_date, customer_name, table_number, start_time, end_time) VALUES (?, ?, ?, ?, ?)";

		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setDate(1, java.sql.Date.valueOf(transaction.getTransactionDate()));
			statement.setString(2, transaction.getCustomerName());
			statement.setString(3, transaction.getTableNumber());
			statement.setTime(4, java.sql.Time.valueOf(transaction.getStartTime()));
			statement.setTime(5, java.sql.Time.valueOf(transaction.getEndTime()));
			
			// Execute the update
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new transaction was inserted successfully!");
			} else {
				System.out.println("Failed to insert a new transaction.");
			}
			
			connection.close();
				statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

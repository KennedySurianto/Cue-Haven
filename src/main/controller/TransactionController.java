package main.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import main.model.DatabaseConnection;
import main.model.Transaction;

public class TransactionController {

	public static void addTransaction(Transaction transaction) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Transaction> getTransactions() {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		
		String query = "SELECT \r\n"
				+ "	transaction_id, transaction_date, customer_name, \r\n"
				+ "	table_number, start_time, end_time, \r\n"
				+ "	end_time - start_time AS play_time, \r\n"
				+ "	ROUND((EXTRACT(EPOCH FROM (end_time - start_time)) / 3600.0) * 50000, 0) AS charge\r\n"
				+ "FROM Transactions\r\n"
				+ "ORDER BY transaction_id DESC;";
		
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Integer transactionId = rs.getInt("transaction_id");
				LocalDate transactionDate = rs.getDate("transaction_date").toLocalDate();
				String customerName = rs.getString("customer_name");
				String tableNumber = rs.getString("table_number");
				LocalTime startTime = rs.getTime("start_time").toLocalTime();
				LocalTime endTime = rs.getTime("end_time").toLocalTime();
				LocalTime playTime = rs.getTime("play_time").toLocalTime();
				Double charge = rs.getDouble("charge");
				
				transactionList.add(new Transaction(transactionId, transactionDate, customerName, tableNumber, startTime, endTime, playTime, charge));
			}
			
			connection.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transactionList;
	}

}

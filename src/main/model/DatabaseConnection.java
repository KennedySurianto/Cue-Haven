package main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
	
	public static Connection getConnection() throws SQLException {
		Dotenv dotenv = Dotenv.configure().load();
		
		final String URL = dotenv.get("DB_URL");
		final String USERNAME = dotenv.get("DB_USER");
		final String PASSWORD = dotenv.get("DB_PASS");
		
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}

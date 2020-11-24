package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public static Connection connection = null;

	public static Connection connect(){
		final String URL = "jdbc:postgresql://localhost:5432/mail-lab7";
		final String username = "postgres";
		final String password = "кщще";

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Нет коннекта к базе: " + e);
		}
		return connection;
	}

	public static Connection getConnection() {
		return connection;
	}
}

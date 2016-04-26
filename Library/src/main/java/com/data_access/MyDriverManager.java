package com.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * MyDriverManager is helper class for getting the connection string specified
 * for these project
 *
 */
public class MyDriverManager {

	private static final String connectionString = "jdbc:postgresql://localhost:5432/library";

	public static Connection getMyConnection() throws SQLException {
		return DriverManager.getConnection(connectionString, "postgres", "postgrespass");

	}
}
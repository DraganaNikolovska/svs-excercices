package com.seavus.twitter.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDriverManager {

	private static final String connectionString = "jdbc:postgresql://localhost:5432/jdbc-twitter";

	public static Connection getMyConnection() throws SQLException {
		return DriverManager.getConnection(connectionString, "postgres", "postgrespass");

	}
}

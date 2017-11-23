package com.cg.trg.bankaccount.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	static Connection connection;
	static String url;
	static String username;
	static String password;

	static {
		// load propeties file....
		Properties prop = new Properties(); // key value
		FileInputStream fis;
		try {
			fis = new FileInputStream("jdbc.properties");
			prop.load(fis);
		} catch (IOException e) {
			System.out.println("Problem while loading properties file"
					+ e.getMessage());
		}
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");

	}

	public static Connection openConnection() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error while obtaining connection"
					+ e.getMessage());
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error while closing connection"
						+ e.getMessage());
			}// close connection to DB
		}

	}

}

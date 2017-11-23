package com.capgemini.cab.util;

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
	
	static{											//static block ...since it has to be executed just once
		Properties prop = new Properties(); 		//to get key /value
		FileInputStream fis;
		
		try {
			fis = new FileInputStream("jdbc.properties");		//to load the file
			prop.load(fis);										//to get the key/value pair
		} catch (IOException e) {
			System.out.println("Problem while loading properties\t" + e.getMessage());
		}
		
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
				
	}

	
	public static Connection openConnection(){		//Connection from java.sql
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error while establishing connection\t" + e.getMessage());
		}
		
		return connection;
	}
	
	public static void closeConnection(){
		if(connection!= null){
			try {
				connection.commit();   			//***********
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error while closing connection" + e.getMessage());
			}//close connection to DB;
		}
		
	}
}

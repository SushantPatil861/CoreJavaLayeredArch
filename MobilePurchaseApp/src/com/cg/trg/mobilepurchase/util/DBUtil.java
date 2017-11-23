package com.cg.trg.mobilepurchase.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {

	static Connection connection;
	static String url;
	static String username;
	static String password;

	static {
		// load properties file

		Properties prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("jdbc.properties");
			prop.load(fis);

		} catch (IOException e) {
			System.out.println("File not loaded" + e.getMessage());
		}
url = prop.getProperty("url");
username = prop.getProperty("username");
password = prop.getProperty("password");
	}

	public static Connection OpenConnection() {
		try{
			connection = DriverManager.getConnection(url,username,password);   //open connection
		}
		catch(SQLException e){
			System.out.println("Error while obtaining connection"+e.getMessage());
			
		}
return connection;
	}

	public static void CloseConnection() {
if(connection!=null){
	try{
		connection.commit();
		connection.close();            //close connection
	}catch(SQLException e){
		System.out.println("Connection not closed"+e.getMessage());
	}
	
}
	}
}

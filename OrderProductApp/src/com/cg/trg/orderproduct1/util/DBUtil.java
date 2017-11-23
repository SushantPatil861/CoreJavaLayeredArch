package com.cg.trg.orderproduct1.util;

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
	static
	{
		//load properties file
		FileInputStream fis;
		Properties prop=new Properties();
		try {
			fis = new FileInputStream("jdbc.properties");  //opens the properties file in read mode
			prop.load(fis);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem while loading properites file"+e.getMessage());
		}
		url=prop.getProperty("url");    
		username=prop.getProperty("username");
		password=prop.getProperty("password");
		
	}
	public static Connection openConnection(){
		try {
			connection=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while obtaining connection"+e.getMessage());
		}
		return connection;
	}
	public static void closeConnection() 
	{
		if(connection!=null)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println();
			}
		}
	}
}

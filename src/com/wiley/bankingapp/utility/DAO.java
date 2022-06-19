package com.wiley.bankingapp.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.wiley.bankingapp.objects.Customer;



public class DAO {
	private String dbconfigPath = "D:\\JavaDevelopment\\BankingSystem\\src\\dbconfig.properties";
	private String driver;
	private String url;
	private String username;
	private String password;
	
	
	public Connection config() {
		try { 
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(dbconfigPath);
			prop.load(in);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			in.close();
			Class.forName(driver);
			return DriverManager.getConnection(url,username,password);
			
		} catch (FileNotFoundException e){
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void customerDBEntry(int id, String name, int age, long mobileNumber, String aadharNumber) {
		Connection conn = config();
		String statement = "INSERT INTO bank.customer(id,name,age,mobileNumber,aadharNumber) VALUES(?,?,?,?,?)";
		try {
			java.sql.PreparedStatement ps = conn.prepareStatement(statement);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setLong(4, mobileNumber);
			ps.setString(5, aadharNumber);
			
			ps.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getLastCustomerID() {
		Connection conn = config();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT count(*) FROM bank.customer");
			while(rs.next()) {
				return rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

	public ArrayList<Customer> getAllCustomers() {
		Connection conn = config();
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM bank.customer");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				long mobileNumber = rs.getLong("mobilenumber");
				String aadharNumber = rs.getString("aadharNumber");
				Customer cust = new Customer(id, name, age, mobileNumber, aadharNumber);
				customers.add(cust);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

	public Customer getCustomer(int searchId) {
		Connection conn = config();
		Customer customer = new Customer();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM bank.customer WHERE ID=" + searchId);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				long mobileNumber = rs.getLong("mobilenumber");
				String aadharNumber = rs.getString("aadharNumber");
				customer = new Customer(id, name, age, mobileNumber, aadharNumber);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}
	
	
}

package Model;

import java.util.*;
import java.sql.*;

public class ProductManager {
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String storeName;
	
	public ProductManager() {
	};

	public ProductManager(String username, String storeName){
		 this.username = username;
		 this.storeName = storeName;
	}

	public void setUsername(String username){
		this.username = username;
	}
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}
	public void setFname(String fname){
		this.fname = fname;
	}
	public void setLname(String lname){
		this.lname = lname;
	}

	public String getUsername(){
		return username;
	}

	public String getFname(){
		return fname;
	}
	public String getLname(){
		return lname;
	}
	
	public String getStoreName(){
		return storeName;
	}

	
	
	public static ProductManager toProductManager(ResultSet rs){
		ProductManager pm = new ProductManager();
		try{
			if(rs.next())
			{
				pm.setUsername(rs.getString("username"));
				pm.setStoreName(rs.getString("store_name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pm;
	}

	public void setName(String fName, String lName) {
		// TODO Auto-generated method stub
		this.fname = fName;
		this.lname = lName;
	}

	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}


}
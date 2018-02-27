package Model;

import java.util.*;
import java.sql.*;

public class ProductManager {
	private String username;
	private String fname;
	private String lname;
	private String storeName;
	
	public ProductManager() {
	};

	public ProductManager(String username, String fname, String lname, String storeName){
		 this.username = username;
		 this.fname = fname;
		 this.lname = lname;
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
			pm.setUsername(rs.getString("username"));
			pm.setFname(rs.getString("fname"));
			pm.setLname(rs.getString("lname"));
			pm.setStoreName(rs.getString("store_name"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pm;
	}

}
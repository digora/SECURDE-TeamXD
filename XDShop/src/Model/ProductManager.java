package Model;

import java.util.*;
import java.sql.*;

public class ProductManager {
	private String username;
	private String storeName;
	private int prod_id;
	
	public ProductManager() {
	};

	public ProductManager(String username, String fname, String lname, String storeName, int prod_id){
		 this.username = username;
		 this.storeName = storeName;
		 this.prod_id = prod_id;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public void setUsername(String username){
		this.username = username;
	}
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	public String getUsername(){
		return username;
	}

	public String getStoreName(){
		return storeName;
	}

	public static ProductManager toProductManager(ResultSet rs){
		ProductManager pm = new ProductManager();
		try{
			pm.setUsername(rs.getString("username"));
			pm.setStoreName(rs.getString("store_name"));
			pm.setProd_id(rs.getInt("prod_man_id"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return pm;
	}

}
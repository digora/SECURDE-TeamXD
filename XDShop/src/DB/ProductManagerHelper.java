package DB;

import java.sql.*;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Model.DBConnection;

//TODO import product manager
public class ProductManagerHelper {
	
	private DBConnection dbc;
	public ProductManagerHelper(){
		dbc = new DBConnection();
	}
	
	private ProductManager login(String username, String password) {
		String query = "SELECT p FROM product_manager p WHERE p.username = " + username + " AND u.password = " + password;
		ResultSet rs = getProductManagerFromQuery(query);
		return ProductManager.toProductManager(rs);
	}
	
	private ProductManager[] getProductManagerFromQuery(String query) {
			ProductManager[] finalArr = null;
			ArrayList<ProductManager> tempArr = new ArrayList<>();
			try{
				ResultSet rs = dbc.executeQuery(query);
				while(rs.next()){
					ProductManager pm = new ProductManager();
					pm.setId(rs.getInt("user_id"));
					pm.setName(rs.getString("fname"), rs.getString("lname"));
					pm.setUserName(rs.getString("username"));
					pm.setPassword(rs.getString("password"));
					pm.setCredits(rs.getDouble("credits"));
					tempArr.add(pm);
				}
				
				finalArr = new ProductManager[tempArr.size()];
				
				for(int i = 0 ; i < tempArr.size(); i ++){
					finalArr[i] = tempArr.get(i);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return finalArr;
	}
}

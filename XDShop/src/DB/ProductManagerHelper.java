package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import Model.ProductManager;
import Model.User;

//TODO import product manager
public class ProductManagerHelper {
	
	private DBConnection dbc;
	public ProductManagerHelper(){
		dbc = new DBConnection();
	}
	
	public ProductManager getProductManagerByUsername(String username){
		String query = "SELECT * FROM product_manager WHERE username = '"+ username + "'";
		ResultSet rs = dbc.executeQuery(query);
		
		return Objects.isNull(rs) ? ProductManager.empty : ProductManager.toProductManager(rs);
	}
	
	public ProductManager login(String username, String password) {
		System.out.println("Logging in user " + username);
		String query = "SELECT p FROM product_manager p WHERE p.username = " + username + " AND u.password = " + password;
		ResultSet rs = dbc.executeQuery(query);
		return Objects.isNull(rs) ? ProductManager.empty : ProductManager.toProductManager(rs);
	}
	
	private ProductManager[] getProductManagerFromQuery(String query) {
			ProductManager[] finalArr = null;
			ArrayList<ProductManager> tempArr = new ArrayList<>();
			try{
				ResultSet rs = dbc.executeQuery(query);
				while(rs.next()){
					ProductManager pm = new ProductManager();
					pm.setName(rs.getString("fname"), rs.getString("lname"));
					pm.setUsername(rs.getString("username"));
					pm.setPassword(rs.getString("password"));
					pm.setStoreName(rs.getString("store_name"));
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

	public boolean register(ProductManager pm, String pass) {
		boolean regSuccess = false;
		
		ProductManager pmCheck = getProductManagerByUsername(pm.getUsername());
		System.out.println("Hello");
		if(pmCheck == ProductManager.empty){
			regSuccess = true;
			String query = "INSERT INTO product_manager(username, password, store_name) VALUES("
					+ "'" + pm.getUsername() + "', "
					+ "'" + pass+ "', "
					+ "'" + pm.getStoreName() + "');";
			
			dbc.updateQuery(query);
		}
		return regSuccess;
	}
}

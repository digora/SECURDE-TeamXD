package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import Model.Product;
import Model.ProductManager;
import Model.User;

//TODO import product manager
public class ProductManagerHelper {
	
	private DBConnection dbc;
	public ProductManagerHelper(){
		dbc = new DBConnection();
	}
	
	public ProductManager getProductManagerByUsername(String username) throws SQLException{
		String query = "SELECT * FROM product_manager WHERE username = '"+ username + "'";
		System.out.println(username);
		ResultSet rs = dbc.executeQuery(query);
		
		ProductManager pm = null;
		if(rs.next()){
			pm = ProductManager.toProductManager(rs);
		}
		return pm;
	}
	
	public ProductManager login(String username, String password) {
		System.out.println("Logging in user " + username);
		String query = "SELECT FROM product_manager WHERE username = " + username + " AND password = " + password;
		ResultSet rs = dbc.executeQuery(query);
		return Objects.isNull(rs) ? ProductManager.empty : ProductManager.toProductManager(rs);
	}
	
	private ProductManager[] getProductManagerFromQuery(String query) {
			ProductManager[] finalArr = null;
			ArrayList<ProductManager> tempArr = new ArrayList<>();
			try{
				ResultSet rs = dbc.executeQuery(query);
				while(rs.next()){
					tempArr.add(ProductManager.toProductManager(rs));
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

	public boolean register(ProductManager pm, String pass) throws SQLException {
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

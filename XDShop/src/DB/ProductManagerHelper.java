package DB;

import java.sql.*;
import java.util.ArrayList;
import Model.*;

//TODO import product manager
public class ProductManagerHelper {
	
	private DBConnection dbc;
	public ProductManagerHelper(){
		dbc = new DBConnection();
	}
	
	public ProductManager login(String username, String password) {
		String query = "SELECT p FROM product_manager p WHERE p.username = " + username + " AND u.password = " + password;
		ProductManager pm = getProductManagerFromQuery(query)[0];
		return pm;
	}
	
	//register only fails when store name / username is taken
	public boolean register(ProductManager pm, String password){
		boolean regSuccess = false;
		String queryChecker = "SELECT * FROM product_manager WHERE username = '" + pm.getUsername() + "' "
				+ "OR store_name = '" + pm.getStoreName() + "'";
		
		if(getProductManagerFromQuery(queryChecker) == null){
			String query = "INSERT INTO product_manager(username, password, store_name) VALUES('"
					+ pm.getUsername() + "','"
					+ password + "','"
					+ pm.getStoreName() + "')";
			regSuccess = true;
			
			dbc.updateQuery(query);
		}
		
		return regSuccess;
	}
	
	public void addItem(Product p, int manager_id, int qty){
		String query = "INSERT INTO product(prod_name, p_manager, price, qty, img_link) VALUES('"
			+ p.getName()+ "',"
			+ manager_id + ","
			+ p.getPrice() +","
			+ qty + ",'"
			+ p.getImg_link() +"')";
		dbc.updateQuery(query);
	}
	
	private ProductManager[] getProductManagerFromQuery(String query) {
			ProductManager[] finalArr = null;
			ArrayList<ProductManager> tempArr = new ArrayList<>();
			try{
				ResultSet rs = dbc.executeQuery(query);
				while(rs.next()){
					tempArr.add(ProductManager.toProductManager(rs));
				}
				finalArr = (ProductManager[]) tempArr.toArray();
			}catch(Exception e){
				e.printStackTrace();
			}
			return finalArr;
	}
}

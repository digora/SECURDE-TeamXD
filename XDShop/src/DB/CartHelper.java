package DB;

import java.sql.*;
import java.util.ArrayList;
import Model.*;

//TODO import cart
public class CartHelper {
	
	private DBConnection dbc;
	public CartHelper(){
		dbc = new DBConnection();
	}
	
	public Cart[] getCartForUser(int userId) {
		String query = "SELECT * FROM cart WHERE user_id = " + userId;
		
		return getCartFromQuery(query);
	}
	
	public boolean checkoutCart(int userId, String address) {
		boolean success = false;
		String query = "DELETE * FROM cart WHERE user_id = " + userId;
		String query2 = "INSERT INTO order(u_id, date_created, address) VALUES("
				+ userId + ", CURDATE(), '"
				+ address + "')";
		String query3 = "SELECT MAX(order_id) FROM order WHERE u_id = " + userId;
		
		Cart[] carts = getCartForUser(userId);
		dbc.updateQuery(query2);
		int order_id;
		try{
			order_id = dbc.executeQuery(query3).getInt("MAX(order_id)");
			String query4 = "SELECT MAX(detail_id) FROM order_details WHERE orderid = "+order_id;
			
			for(Cart c : carts){
				String query5 = "INSERT INTO order_details(orderid,product_id, qty) VALUES("
						+ order_id + ", "
						+ c.getPid() + ", "
						+ c.getQty() + ")";
				dbc.updateQuery(query5);
				
				int latestDetailId = dbc.executeQuery(query4).getInt("MAX(detail_id)");
				
				String query6 = "INSERT INTO order_status(date,status,detail_id) VALUES(CURDATE(),"
						+ "'Order Placed', "
						+ latestDetailId + ")";
				
				dbc.updateQuery(query6);
			}
			
			dbc.updateQuery(query);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return success;
	}
	
	public void addItemToCart(Product p, int userId, int qty){
		String query = "INSERT INTO cart(user_id, p_id, qty) VALUES("
				+ p.getP_id() +","
				+ userId + ","
				+ qty + ")";
		
		dbc.updateQuery(query);
	}
	
	public void removeItemFromCart(Product p, int userId){
		String query = "DELETE FROM cart WHERE p_id = " + p.getPrice() + " AND user_id = " + userId;
		
		dbc.updateQuery(query);
	}
	
	public Cart[] getCartFromQuery(String query) {
		Cart[] finalArr = null;
		ArrayList<Cart> tempArr = new ArrayList<>();
		try{
			ResultSet rs = dbc.executeQuery(query);
			while(rs.next()){
				tempArr.add(Cart.toCart(rs));
			}
			finalArr = (Cart[]) tempArr.toArray();
		}catch(Exception e){
			e.printStackTrace();
		}
		return finalArr;
	}
}
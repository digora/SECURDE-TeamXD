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
		String queryBalance = "SELECT credits FROM users WHERE user_id = " + userId;
		Cart[] carts = getCartForUser(userId);
		dbc.updateQuery(query2);
		int order_id;
		try{
			ResultSet rs = dbc.executeQuery(queryBalance);
			Double userBalance = rs.getDouble("credits");
			
			rs = dbc.executeQuery(query3);
			order_id = rs.getInt("MAX(order_id)");
			
			Double cartTotal = 0.0;
			String queryPrice;
			
			for(Cart c : carts){
				queryPrice = "SELECT price FROM product WHERE prod_id = " + c.getPid();
				cartTotal += dbc.executeQuery(queryPrice).getDouble("price") * c.getQty();
			}
			
			if(cartTotal <= userBalance){
				String query4 = "SELECT MAX(detail_id) FROM order_details WHERE orderid = "+order_id;
				
				for(Cart c : carts){
					String query5 = "INSERT INTO order_details(orderid,product_id, qty) VALUES("
							+ order_id + ", "
							+ c.getPid() + ", "
							+ c.getQty() + ")";
					dbc.updateQuery(query5);
					
					rs = dbc.executeQuery(query4);
					int latestDetailId = rs.getInt("MAX(detail_id)");
					
					String query6 = "INSERT INTO order_status(date,status,detail_id) VALUES(CURDATE(),"
							+ "'Order Placed', "
							+ latestDetailId + ")";
					
					dbc.updateQuery(query6);
				}
				String updateUserBalance = "UPDATE users SET credits = credits - " + cartTotal + " WHERE user_id = " + userId;
				dbc.updateQuery(query);
				dbc.updateQuery(updateUserBalance);
				success = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean addItemToCart(Product p, int userId, int qty){
		boolean succ = false;
		String queryCheck = "SELECT * FROM cart WHERE user_id = " + userId + " p_id = " + p.getP_id();
		String getQty = "SELECT qty FROM product WHERE prod_id = " + p.getP_id();
		String updateStock = "";
		try{
			//get current stock
			ResultSet rs = dbc.executeQuery(getQty);
			int qt = rs.getInt("qty");
			//check if qty is not mroe than the stock
			if(qt>=qty){
				//check if order alr exists
				Cart c  = Cart.toCart(dbc.executeQuery(queryCheck));
				if(c!= null){
					int newqt = qty - c.getQty();
					//check if new qty is more than stock
					if(qt>newqt){
						String updateCart = "UPDATE cart SET qty = qty + " + newqt + " WHERE user_id = " + userId + " AND p_id = " + p.getP_id();
						updateStock = "UPDATE products SET qty = qty - " + newqt + " WHERE prod_id = " + p.getP_id();
					}
				}else{
					String query = "INSERT INTO cart(user_id, p_id, qty) VALUES("
							+ p.getP_id() +","
							+ userId + ","
							+ qty + ")";
					updateStock = "UPDATE products SET qty = qty - " + qty + " WHERE prod_id = " + p.getP_id();
					dbc.updateQuery(query);
				}
				dbc.updateQuery(updateStock);
				succ = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return succ;
	}
	
	public void removeItemFromCart(Product p, int userId){
		String query = "DELETE FROM cart WHERE p_id = " + p.getP_id() + " AND user_id = " + userId;
		
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
			finalArr = tempArr.toArray(new Cart[tempArr.size()]);		
		}catch(Exception e){
			e.printStackTrace();
		}
		return finalArr;
	}
}
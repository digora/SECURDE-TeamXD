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

import Model.Cart;


//TODO import cart
public class CartHelper {
	
	private DBConnection dbc;
	public CartHelper(){
		dbc = new DBConnection();
	}
	
	private Cart getCartForUser(int userId) {
		String query = "SELECT c FROM cart c, users u WHERE u.user_id = " + userId + " AND c.user_id = " + userId;
		
		return Cart.toCart(getCartFromQuery(query));
	}
	
	private boolean checkoutCart(int userId) {
		String query = "DELETE c FROM cart c, users u WHERE u.user_id = " + userId + " AND c.user_id = " + userId;
		
		return (getCartFromQuery(query).length == 0);
		//if it's 0 successful checkout
	}
	
	private Cart[] getCartFromQuery(String query) {
		Cart[] finalArr = null;
		ArrayList<Cart> tempArr = new ArrayList<>();
		try{
			ResultSet rs = dbc.executeQuery(query);			
			while(rs.next()){
				Cart cart = new Cart();
				cart.setUid(rs.getInt("user_id"));
				cart.setPid((rs.getInt("p_id")));
				cart.setQty((rs.getInt("qty")));
				tempArr.add(cart);
			}
			
			finalArr = new Cart[tempArr.size()];
			
			for(int i = 0 ; i < tempArr.size(); i ++){
				finalArr[i] = tempArr.get(i);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return finalArr;
	}
}

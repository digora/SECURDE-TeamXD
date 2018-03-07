package DB;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import Model.ProductManager;
import Model.User;

public class UserHelper {
	private DBConnection dbc;
	public UserHelper(){
		dbc = new DBConnection();
		dbc.getConnection();
	};
	
	public User getUserByUsername(String username) throws SQLException{
		String query = "SELECT * FROM users WHERE username = '"+ username + "'";
		System.out.println(username);
		ResultSet rs = dbc.executeQuery(query);
		
		User u = null;
		if(rs.next()){
			u = User.toUser(rs);
		}
		return u;
	}
	
	public User login(String username, String password) throws SQLException{
		System.out.println("Logging in user " + username);
		String query = "SELECT * FROM users WHERE username = '"+ username + "' "
				+ "AND password = '" + password + "'";
		ResultSet rs = dbc.executeQuery(query);
		User u = null;
		if(rs.next()){
			u = User.toUser(rs);
		}
		return u;
	}
	
	public boolean register(User user, String password) throws SQLException{
		boolean regSuccess = false;
		
		User u = getUserByUsername(user.getUsername());
		
		if(u == User.empty){
			regSuccess = true;
			String query = "INSERT INTO users(fname, lname, username, password, credits) VALUES("
					+ "'" + user.getFname() + "', "
					+ "'" + user.getLname() + "', "
					+ "'" + user.getUsername() + "', "
					+ "'" + password+ "', "
					+ "'" + user.getCredits() + "');";
			
			dbc.updateQuery(query);
		}
		return regSuccess;
	}
	
}

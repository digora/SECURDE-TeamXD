package DB;


import java.sql.PreparedStatement;
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
	
	public double getCredits(String username) throws SQLException {
		String query = "SELECT credits FROM users WHERE username = '"+ username + "'";
		ResultSet rs = dbc.executeQuery(query);
		double val = 0;
		if(rs.next())
		{
			val = rs.getDouble("credits");
		}
		return val;
	}
	public void reloadCredits(String username, double amount) throws SQLException {
		String query = "UPDATE USERS SET credits = credits + ? WHERE username = ?";
		try{
			
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			pstmt.setDouble(1, amount);
			pstmt.setString(2, username);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Credits reloaded by " + amount);
		}catch(Exception e){
			e.printStackTrace();
		}
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

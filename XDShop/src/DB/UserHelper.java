package DB;


import java.sql.ResultSet;

import Model.User;

public class UserHelper {
	private DBConnection dbc;
	public UserHelper(){
		dbc = new DBConnection();
		dbc.getConnection();
	};
	
	public User getUserByUsername(String username){
		String query = "SELECT * FROM users WHERE username = '"+ username + "'";
		ResultSet rs = dbc.executeQuery(query);
		
		return User.toUser(rs);
	}
	
	public User login(String username, String password){
		String query = "SELECT * FROM users WHERE username = '"+ username + "' "
				+ "AND password = '" + password + "'";
		ResultSet rs = dbc.executeQuery(query);
		
		return User.toUser(rs);
	}
	
	
	public boolean register(User user, String password){
		boolean check_If_Email_Is_Not_Taken_This_Variable_Is_So_Long_Lmao = false;
		
		User u = getUserByUsername(user.getUsername());
		
		if(u!= null){
			check_If_Email_Is_Not_Taken_This_Variable_Is_So_Long_Lmao = true;
			String query = "INSERT INTO users(fname, lname, username, password, credits) VALUES("
					+ "'" + user.getFname() + "', "
					+ "'" + user.getLname() + "', "
					+ "'" + user.getUsername() + "', "
					+ "'" + password+ "', "
					+ "'" + user.getCredits() + "');";
			
			dbc.updateQuery(query);
		}
		return check_If_Email_Is_Not_Taken_This_Variable_Is_So_Long_Lmao;
	}
	
}

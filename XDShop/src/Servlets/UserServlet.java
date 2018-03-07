package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.*;
import Model.User;
import Model.ProductManager;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final UserHelper helper = new UserHelper();
    private final ProductManagerHelper pmHelper = new ProductManagerHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = (String) request.getParameter("param").split("&")[0];
		System.out.println(param);
		boolean b = false;
		
		if(param.compareToIgnoreCase("loggedin") == 0){
			Cookie cookie = request.getCookies()[0];
			System.out.println("yay");
			b = true;
			//add 3 weeks to life
			cookie.setMaxAge(cookie.getMaxAge() + 60*60*24*21);
		}else if(param.compareToIgnoreCase("login") == 0){
			String user = (String) request.getParameter("user").split("&")[0];
			String pass = (String) request.getParameter("pass").split("&")[0];
			boolean remember = Boolean.parseBoolean((String)request.getParameter("remembered").split("&")[0]);
			try {
				if(helper.login(user, pass) != null){
					b = true;
					Cookie cookie = new Cookie("username", user);
					if(remember){
						cookie.setMaxAge(60*60*24*21);
					}
					response.addCookie(cookie);
				}else if(pmHelper.login(user,pass) != null){
					b = true;
					Cookie cookie = new Cookie("username", user);
					if(remember){
						cookie.setMaxAge(60*60*24*21);
					}
					response.addCookie(cookie);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write(String.valueOf(b));
		}else if(param.compareToIgnoreCase("user") == 0){
			System.out.println("Getting by user");
			String user = (String) request.getParameter("user").split("&")[0];
			User u = null;
			try {
				u = helper.getUserByUsername(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(u!=null){
				System.out.println("Im GAY");
				response.setContentType("text/plain");
				response.getWriter().write(u.toJSONformat());
			}
			
		}else if(param.compareToIgnoreCase("logout") == 0){
			Cookie cook = request.getCookies()[0];
			cook.setMaxAge(0);
			response.addCookie(cook);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
			String userName = (String) request.getParameter("user").split("&")[0];
			String pass = (String) request.getParameter("pass").split("&")[0];
			String fName = (String) request.getParameter("fName").split("&")[0];
			String lName = (String) request.getParameter("lName").split("&")[0];
			User user = new User(userName, 0.0, fName, lName);
			boolean b = false;
			try {
				b = helper.register(user, pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write(String.valueOf(b));
	}

}

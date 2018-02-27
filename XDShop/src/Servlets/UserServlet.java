package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.Helpers;
import Model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		PrintWriter out = response.getWriter();
		out.write("<h2>Henlo</h2>");
		String param = (String) request.getParameter("param").split("&")[0];
		System.out.println(request.toString());
		boolean b = false;
		
		if(param.compareToIgnoreCase("loggedin") == 0){
			Cookie cookie = request.getCookies()[0];
			b = true;
			//add 3 weeks to life
			cookie.setMaxAge(cookie.getMaxAge() + 60*60*24*21);
		}else if(param.compareToIgnoreCase("login") == 0){
			String user = (String) request.getParameter("user").split("&")[0];
			String pass = (String) request.getParameter("pass").split("&")[0];
			boolean remember = Boolean.parseBoolean((String)request.getParameter("remembered").split("&")[0]);
			if(Helpers.getUser().login(user, pass) != null){
				b = true;
				Cookie cookie = new Cookie("username", user);
				if(remember){
					cookie.setMaxAge(60*60*24*21);
				}
				response.addCookie(cookie);
			}
			response.getWriter().write(String.valueOf(b));
		}else if(param.compareToIgnoreCase("user") == 0){
			String user = (String) request.getParameter("user").split("&")[0];
			User u = Helpers.getUser().getUserByUsername(user);
			
			
			if(u!=null){
				response.setContentType("text/plain");
				response.getWriter().write(u.getUsername());
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
		doGet(request, response);
	}

}

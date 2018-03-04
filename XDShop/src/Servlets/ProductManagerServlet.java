package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DB.*;
import Model.ProductManager;
import Model.User;
/**
 * Servlet implementation class ProjectManagerServlet
 */
@WebServlet("/ProductManagerServlet")
public class ProductManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ProductManagerHelper helper = new ProductManagerHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("I want to die");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = (String) request.getParameter("user").split("&")[0];
		String pass = (String) request.getParameter("pass").split("&")[0];
		String storeName = (String) request.getParameter("storeName").split("&")[0];
		ProductManager pm = new ProductManager(userName, storeName);
		boolean b = helper.register(pm, pass);
		response.getWriter().write(String.valueOf(b));
	}

}

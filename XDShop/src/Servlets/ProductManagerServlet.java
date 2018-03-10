package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import DB.*;
import Model.Product;
import Model.ProductManager;
import Model.User;
/**
 * Servlet implementation class ProjectManagerServlet
 */
@WebServlet("/ProductManagerServlet")
public class ProductManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ProductManagerHelper helper = new ProductManagerHelper();
    private final ProductHelper pHelper = new ProductHelper();
    private final Gson gson = new GsonBuilder().create();
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
		String param = (String) request.getParameter("param").split("&")[0];
		String username = (String) request.getParameter("username").split("&")[0];
		System.out.println(param);
		if (param.compareToIgnoreCase("getByPM") == 0) 
		{
			Product[] products = null;
			System.out.println("getByPM");
			int managerId = -1;
			try {
				managerId = helper.getProductManagerIdByUsername(username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			products = pHelper.getProductsByManagerId(managerId);
			response.getWriter().write(gson.toJson(products));
		} else
			response.getWriter().write("false");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = (String) request.getParameter("param").split("&")[0];
		//TODO store and get the productManager's ID for literally everything in this servlet
		String username = (String) request.getParameter("username").split("&")[0];
		int prodManagerId = -1;
		try {
			prodManagerId = helper.getProductManagerIdByUsername(username);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//TODO As mentioned above, just 2 TODOs for emphasis
		System.out.println(param);
		boolean b = false;
		
		if(param.compareToIgnoreCase("restock") == 0)
		{
			int prodId = Integer.parseInt(request.getParameter("prodId").split("&")[0]);
			int quantity = Integer.parseInt(request.getParameter("qty").split("&")[0]);
			b = pHelper.restockProduct(prodId, prodManagerId, quantity);
		}
		else if (param.compareToIgnoreCase("add") == 0)
		{
			String name = (String) request.getParameter("name").split("&")[0];
			double price = Double.parseDouble((String) request.getParameter("price").split("&")[0]);
			int quantity = Integer.parseInt(request.getParameter("qty").split("&")[0]);
			String imageLink = (String) request.getParameter("imgLink").split("&")[0];
			b = pHelper.addProduct(name, prodManagerId, price, quantity, imageLink);
		}
		
		else if (param.compareToIgnoreCase("delete") == 0)
		{
			int prodId = Integer.parseInt(request.getParameter("prodId").split("&")[0]);
			b = pHelper.deleteProduct(prodId, prodManagerId);
		}
		
		else if (param.compareToIgnoreCase("edit") == 0)
		{
			int managerId = -1;
			try {
				managerId = helper.getProductManagerIdByUsername(username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int prodId = Integer.parseInt(request.getParameter("prodId").split("&")[0]);
			
			String imageLink = null;
			String name = null;
			double price = -1;
			
			if(!(request.getParameter("imgLink").split("&")[0].equalsIgnoreCase("none"))){
				imageLink = (String) request.getParameter("imgLink").split("&")[0];
				System.out.println("Link set");
			}
			if(!(request.getParameter("price").split("&")[0].equalsIgnoreCase("none"))){
				price = Double.parseDouble((String) request.getParameter("price").split("&")[0]);
				System.out.println("Price set");
			}
			if(!(request.getParameter("name").split("&")[0].equalsIgnoreCase("none"))){
				name = (String) request.getParameter("name").split("&")[0];
				System.out.println("Name set");
			}
			if(price != -1){
				b = pHelper.editProductPrice(price, managerId, prodId);
				System.out.println("Price updated!");
			}if((!Objects.isNull(imageLink))){
				b = pHelper.editProductImage(imageLink, managerId, prodId);
				System.out.println("Image link updated!");
			}if((!Objects.isNull(name))){
				b = pHelper.editProductName(name, managerId, prodId);
				System.out.println("Product name updated!");
			}
		}
		
		else if (param.compareToIgnoreCase("register") == 0)
		{
			String userName = (String) request.getParameter("user").split("&")[0];
			String pass = (String) request.getParameter("pass").split("&")[0];
			String storeName = (String) request.getParameter("storeName").split("&")[0];
			ProductManager pm = new ProductManager(userName, storeName);
			
			try {
				b = helper.register(pm, pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		response.getWriter().write(String.valueOf(b));
		
	}

}

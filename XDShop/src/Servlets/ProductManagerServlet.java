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
    private final ProductHelper pHelper = new ProductHelper();
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = (String) request.getParameter("param").split("&")[0];
		//TODO store and get the productManager's ID for literally everything in this servlet
		int managerId = 1;
		//TODO As mentioned above, just 2 TODOs for emphasis
		System.out.println(param);
		if(param.compareToIgnoreCase("restock") == 0)
		{
			int prodId = Integer.parseInt(request.getParameter("prodId").split("&")[0]);
			int quantity = Integer.parseInt(request.getParameter("qty").split("&")[0]);
			pHelper.restockProduct(prodId, managerId, quantity);
		}
		
		else if (param.compareToIgnoreCase("add") == 0)
		{
			String name = (String) request.getParameter("name").split("&")[0];
			double price = Double.parseDouble((String) request.getParameter("price").split("&")[0]);
			int quantity = Integer.parseInt(request.getParameter("qty").split("&")[0]);
			String imageLink = (String) request.getParameter("imgLink").split("&")[0];
			pHelper.addProduct(name, managerId, price, quantity, imageLink);
		}
		
		else if (param.compareToIgnoreCase("delete") == 0)
		{
			int prodId = Integer.parseInt(request.getParameter("prodId").split("&")[0]);
			pHelper.deleteProduct(prodId);
		}
		
		else if (param.compareToIgnoreCase("edit") == 0)
		{
			int prodId = Integer.parseInt(request.getParameter("prodId").split("&")[0]);
			String name = (String) request.getParameter("name").split("&")[0];
			double price = Double.parseDouble((String) request.getParameter("price").split("&")[0]);
			int quantity = Integer.parseInt(request.getParameter("qty").split("&")[0]);
			String imageLink = (String) request.getParameter("imgLink").split("&")[0];
			pHelper.editProduct(prodId, name, managerId, price, quantity, imageLink);
		}
		
		else if (param.compareToIgnoreCase("register") == 0)
		{
			String userName = (String) request.getParameter("user").split("&")[0];
			String pass = (String) request.getParameter("pass").split("&")[0];
			String storeName = (String) request.getParameter("storeName").split("&")[0];
			ProductManager pm = new ProductManager(userName, storeName);
			boolean b = helper.register(pm, pass);
			response.getWriter().write(String.valueOf(b));
		}
		
	}

}

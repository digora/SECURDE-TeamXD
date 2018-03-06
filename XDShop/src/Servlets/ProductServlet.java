package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DB.*;
import com.google.gson.*;

import Model.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ProductHelper helper = new ProductHelper();
    private final ProductManagerHelper pmHelper = new ProductManagerHelper();
    private final Gson gson = new GsonBuilder().create();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//TODO get all products
		String param = (String) request.getParameter("param").split("&")[0];
		Product[] products = new Product[1]; //1 is for queries that return only one product
		if(param.compareToIgnoreCase("all") == 0) {
			products = helper.getAllProducts();
		}else if (param.compareToIgnoreCase("search") == 0) {
			String searchString = (String) request.getParameter("searchString").split("&")[0];
			products = helper.searchProducts(searchString);
		}else if (param.compareToIgnoreCase("getById") == 0) {
			int id = Integer.parseInt(request.getParameter("id").split("&")[0]);
			products[0] = helper.getProductById(id);
		}else if (param.compareToIgnoreCase("getByPMId") == 0) {
			int pmId = Integer.parseInt(request.getParameter("pmId").split("&")[0]);
			products = helper.getProductsByManagerId(pmId);
		}else if (param.compareToIgnoreCase("getByPrice") == 0) {
			int startPrice = Integer.parseInt(request.getParameter("start").split("&")[0]);
			int endPrice = Integer.parseInt(request.getParameter("end").split("&")[0]);
			products = helper.getProductsByPrice(startPrice, endPrice);
		}
		response.getWriter().write(gson.toJson(products));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import DB.DBConnection;
import DB.OrderHelper;
import Model.Order;
import Model.Order_Details;
import Model.Order_Status;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(urlPatterns = {"/addOrderStatus", "/getOrderByUserId", "/getOrdersByProductManager"})
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Gson gson;
	private final OrderHelper oh = new OrderHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        gson = new GsonBuilder().create();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

		switch(path){
		case "/addOrderStatus":
			addOrderStatus(request, response);
			break;
			
		case "/getOrderByUserId":
			getOrderByUserId(request, response);
			break;
			
		case "/getOrdersByProductManager":
			getOrderByUserId(request, response);
			break;
		}
	}
	
	private void addOrderStatus(HttpServletRequest request, HttpServletResponse response){
		Order_Status os = new Order_Status();
		
		int detail_id = Integer.parseInt(request.getParameter("detail_id"));
		String status = request.getParameter("status");
		
		os.setDetail_id(detail_id);
		os.setStatus(status);
		
		oh.addOrderStatus(os);
	}
	
	private void getOrderByUserId(HttpServletRequest request, HttpServletResponse response){
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		
		Order[] orders = oh.getOrdersByUserId(userId);
		
		try {
			response.getWriter().write(gson.toJson(orders));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getOrdersByProductManager(HttpServletRequest request, HttpServletResponse response){
		int prodman_id = Integer.parseInt(request.getParameter("id"));
		
		Order_Details[] orders = oh.getOrdersByProductManager(prodman_id);
		
		try {
			response.getWriter().write(gson.toJson(orders));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

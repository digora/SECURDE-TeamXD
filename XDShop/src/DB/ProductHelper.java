package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Product;
import Model.ProductManager;

public class ProductHelper {
	private DBConnection dbc;
	public ProductHelper(){
		dbc = new DBConnection();
		dbc.getConnection();
	};
	
	private Product[] getProductArr(String query){
		ArrayList<Product> arr = new ArrayList<>();
		try{
			int i = 0;
			ResultSet rs = dbc.executeQuery(query);
			while(rs.next()){			
				arr.add(Product.toProduct(rs));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return arr.toArray(new Product[arr.size()]);
	}
	
	public boolean addProduct(String name, String manager, double price, int quantity, String imageLink){
		String query = "INSERT INTO product(prod_name, username, price, qty, img_link) VALUES(?,?,?,?,?,?)";
		try{
			
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setString(2, manager);
			pstmt.setDouble(3, price);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, imageLink);
			
			
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product added!");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean restockProduct(int prodId, String manager, int quantity){
		String query = "UPDATE product SET qty = qty + ? WHERE prod_id = ? AND username = ?"
				+ "AND p_manager = ?";
		try{
			
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, prodId);
			pstmt.setString(3, manager);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product restocked by " + quantity);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean editProduct(int id, String name, String manager, double price, int quantity, String imageLink){
		String query = "UPDATE product SET prod_id = ? SET prod_name = ? SET price = ? SET qty = ? SET img_link = ? WHERE prod_id = ? AND username = ?";
		try{
			
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setDouble(3, price);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, imageLink);
			pstmt.setInt(6, id);
			pstmt.setString(7, manager);
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product edited!");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteProduct(int id, String manager){
		String query = "DELETE FROM product WHERE prod_id = ? AND username = ?";
		try{
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product deleted!");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Product[] searchProducts(String searchName){
		String query = "SELECT * FROM products WHERE prod_name LIKE '%"+searchName+"%'";
		return getProductArr(query);
	}
	
	public Product[] getProductsByManagerId(String username){
		String query = "SELECT * FROM products WHERE p_manager = '"+username+"'";
		return getProductArr(query);
	}
	
	public Product getProductById(int id){
		String query = "SELECT * FROM product WHERE prod_id = '" + id + "'";
		ResultSet rs = dbc.executeQuery(query);
		return Product.toProduct(rs);
	}
	
	public Product[] getProductsByPrice(int start, int end){
		String query = "SELECT * FROM product WHERE price >= " + start + " AND price <= " + end;
		return getProductArr(query);
	}
	
	public Product[] getAllProducts(){
		String query = "SELECT * FROM product";
		return getProductArr(query);
	}
}

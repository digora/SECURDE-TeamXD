package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Product;

public class ProductHelper {
	private DBConnection dbc;
	public ProductHelper(){
		dbc = new DBConnection();
		dbc.getConnection();
	};
	
	private Product[] getProductArr(String query){
		ArrayList<Product> arr = new ArrayList<>();
		try{
			ResultSet rs = dbc.executeQuery(query);
			while(rs.next()){
				arr.add(Product.toProduct(rs));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return (Product[]) arr.toArray();
	}
	
	public void addProduct(String name, int manager, double price, int quantity, String imageLink){
		String query = "INSERT INTO product(prod_name, p_manager, price, qty, img_link) VALUES(?,?,?,?,?,?)";
		try{
			
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, manager);
			pstmt.setDouble(3, price);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, imageLink);
			
			
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product added!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void restockProduct(int prodId, int manager, int quantity){
		String query = "UPDATE product SET qty = qty + ? WHERE prod_id = ? AND manager = ?"
				+ "AND p_manager = ?";
		try{
			
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, prodId);
			pstmt.setInt(3, manager);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product restocked by " + quantity);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void editProduct(int id, String name, int manager, double price, int quantity, String imageLink){
		String query = "UPDATE product SET prod_id = ? SET prod_name = ? SET price = ? SET qty = ? SET img_link = ? WHERE prod_id = ? AND p_manager = ?";
		try{
			
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setDouble(3, price);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, imageLink);
			pstmt.setInt(6, id);
			pstmt.setInt(7, manager);
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product edited!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteProduct(int id){
		String query = "DELETE FROM product WHERE prod_id = ?";
		try{
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product deleted!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Product[] searchProducts(String searchName){
		String query = "SELECT * FROM products WHERE prod_name LIKE '%"+searchName+"%'";
		return getProductArr(query);
	}
	
	public Product[] getProductsByManagerId(int id){
		String query = "SELECT * FROM products WHERE p_manager = '"+id+"'";
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

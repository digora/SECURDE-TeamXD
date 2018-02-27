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
	
	private void addProduct(int id, String name, int manager, double price, int quantity, String imageLink){
		String query = "INSERT INTO product(prod_id, prod_name, p_manager, price, qty, img_link) VALUES(?,?,?,?,?,?)";
		try{
			
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, manager);
			pstmt.setDouble(4, price);
			pstmt.setInt(5, quantity);
			pstmt.setString(6, imageLink);
			
			
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product added!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void editProduct(int id, String name, int manager, double price, int quantity, String imageLink){
		String query = "UPDATE product SET prod_id = ? SET prod_name = ? SET p_manager = ? SET price = ? SET qty = ? set img_link = ? WHERE prod id = ?";
		try{
			
			PreparedStatement pstmt = dbc.createPreparedStatement(query);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, manager);
			pstmt.setDouble(4, price);
			pstmt.setInt(5, quantity);
			pstmt.setString(6, imageLink);
			pstmt.setInt(7, id);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("Product edited!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void deleteProduct(int id){
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
	
	public Product[] getAllProducts(){
		return getProductArr("SELECT * FROM products");
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
}

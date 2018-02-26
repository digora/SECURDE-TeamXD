package DB;

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

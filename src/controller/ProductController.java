package controller;

import java.util.ArrayList;

import model.Product;

public class ProductController {

	public static ArrayList<Product> getProducts() {
        return Product.getAllProducts();
    }
	
	public static String updateStock(String productId, String stockInput) {
	    if (stockInput.isEmpty()) return "Stock must be filled!";

	    for (int i = 0; i < stockInput.length(); i++) {
	        if (!Character.isDigit(stockInput.charAt(i))) return "Stock must be numeric!";
	    }

	    int stock = Integer.parseInt(stockInput);
	    if (stock < 0) return "Stock cannot be negative!";

	    boolean success = Product.updateStock(productId, stock);
	    if (!success) return "Failed to update stock!";

	    return null; 
	}

}

package controller;

import java.util.ArrayList;

import model.Product;

public class ProductController {

	public static ArrayList<Product> getProducts() {
        return Product.getAllProducts();
    }
}

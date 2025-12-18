package model;

import java.util.ArrayList;

import utils.DatabaseConnection;

public class Product {

	private String idProduct;
    private String name;
    private double price;
    private int stock;
    private String category;

    public Product(String idProduct, String name, double price, int stock, String category) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
    
    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();

        try {
            String query = "SELECT * FROM products";
            var rs = DatabaseConnection.query(query);

            while (rs.next()) {
                products.add(new Product(
                    rs.getString("idProduct"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("category")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    
    public static Product getProduct(String idProduct) {
        Product product = null;

        try {
            String query = "SELECT * FROM products WHERE idProduct = ?";
            var rs = DatabaseConnection.query(query, idProduct);

            if (rs.next()) {
                product = new Product(
                    rs.getString("idProduct"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("category")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
    
    public static boolean reduceStock(String idProduct, int qty) {
        String query = "UPDATE products SET stock = stock - ? WHERE idProduct = ?";
        return DatabaseConnection.update(query, qty, idProduct);
    }
    
    public static boolean editProductStock(String productId, int stock) {
        String query = "UPDATE products SET stock = ? WHERE idProduct = ?";
        return DatabaseConnection.update(query, stock, productId);
    }

    public String getIdProduct() { return idProduct; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }

}

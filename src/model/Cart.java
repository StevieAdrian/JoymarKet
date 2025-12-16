package model;

import java.util.ArrayList;

import utils.DatabaseConnection;

public class Cart {

	private Product product;
    private int qty;

    public Cart(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public int getQty() {
        return qty;
    }

    public double getSubtotal() {
        return product.getPrice() * qty;
    }
    
	public static boolean insertOrUpdate(String idCustomer, String idProduct, int qty) {
        try {
            String check = "SELECT count FROM cart_items WHERE idCustomer = ? AND idProduct = ?";

            var rs = DatabaseConnection.query(check, idCustomer, idProduct);

            if (rs.next()) {
                String update = "UPDATE cart_items SET count = count + ? WHERE idCustomer = ? AND idProduct = ?";

                return DatabaseConnection.update(update, qty, idCustomer, idProduct);
            } else {
                String insert = "INSERT INTO cart_items (idCustomer, idProduct, count) VALUES (?, ?, ?)";

                return DatabaseConnection.update(insert, idCustomer, idProduct, qty);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
	
	public static ArrayList<Cart> getByCustomer(String idCustomer) {
        ArrayList<Cart> carts = new ArrayList<>();

        String query = "SELECT p.idProduct, p.name, p.price, p.stock, p.category, c.count FROM cart_items c JOIN products p ON c.idProduct = p.idProduct WHERE c.idCustomer = ?";

        try {
            var rs = DatabaseConnection.query(query, idCustomer);

            while (rs.next()) {
                Product product = new Product(
                    rs.getString("idProduct"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("category")
                );

                carts.add(new Cart(product, rs.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carts;
    }
	
	public static boolean updateQty(String idCustomer, String idProduct, int qty) {
        String query = "UPDATE cart_items SET count = ? WHERE idCustomer = ? AND idProduct = ?";
        return DatabaseConnection.update(query, qty, idCustomer, idProduct);
    }

    public static boolean remove(String idCustomer, String idProduct) {
        String query = "DELETE FROM cart_items WHERE idCustomer = ? AND idProduct = ?";
        return DatabaseConnection.update(query, idCustomer, idProduct);
    }

    public static boolean clear(String idCustomer) {
        String query = "DELETE FROM cart_items WHERE idCustomer = ?";
            
        return DatabaseConnection.update(query, idCustomer);
    }

}

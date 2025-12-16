package model;

import utils.DatabaseConnection;

public class OrderDetail {

	public static boolean create(String idOrder, String idProduct, int qty) {
        String query = "INSERT INTO order_details (idOrder, idProduct, qty) VALUES (?, ?, ?)";

        return DatabaseConnection.update(query, idOrder, idProduct, qty);
    }
}

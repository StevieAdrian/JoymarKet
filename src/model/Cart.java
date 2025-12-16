package model;

import utils.DatabaseConnection;

public class Cart {

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
}

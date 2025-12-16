package controller;

import model.Cart;
import model.Product;
import model.User;
import utils.AppManager;

public class CartController {

    public static String addToCart(Product product, String qtyText) {

        if (qtyText.isEmpty())
            return "Quantity must be filled!";

        int qty;
        try {
            qty = Integer.parseInt(qtyText);
        } catch (Exception e) {
            return "Quantity must be numeric!";
        }

        if (qty < 1)
            return "Quantity must be at least 1!";

        if (qty > product.getStock())
            return "Quantity exceeds product stock!";

        User user = AppManager.getCurrentUser();

        boolean success = Cart.insertOrUpdate(
            user.getIdUser(),
            product.getIdProduct(),
            qty
        );

        if (!success)
            return "Failed to add product to cart!";

        return null;
    }
}

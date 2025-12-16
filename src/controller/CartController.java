package controller;

import java.util.ArrayList;

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
    
    public static ArrayList<Cart> getCartItems() {
        return Cart.getByCustomer(AppManager.getCurrentUser().getIdUser());
    }

    public static String updateCart(Product product, String qtyText) {

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

        boolean success = Cart.updateQty(
            AppManager.getCurrentUser().getIdUser(),
            product.getIdProduct(),
            qty
        );

        if (!success)
            return "Failed to update cart item!";

        return null;
    }

    public static String removeFromCart(Product product) {

        boolean success = Cart.remove(
            AppManager.getCurrentUser().getIdUser(),
            product.getIdProduct()
        );

        if (!success)
            return "Failed to remove cart item!";

        return null;
    }

}

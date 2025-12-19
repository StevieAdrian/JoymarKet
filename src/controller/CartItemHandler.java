package controller;

import java.util.ArrayList;

import model.CartItem;
import model.Product;
import model.User;
import utils.AppManager;

public class CartItemHandler {

    public static String createCartItem(Product product, String qtyText) {

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

        boolean success = CartItem.insertOrUpdate(
            user.getIdUser(),
            product.getIdProduct(),
            qty
        );

        if (!success)
            return "Failed to add product to cart!";

        return null;
    }
    
    public static ArrayList<CartItem> getCartItems() {
        return CartItem.getCartItems(AppManager.getCurrentUser().getIdUser());
    }

    public static String editCartItem(Product product, String qtyText) {

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

        boolean success = CartItem.editCartItem(
            AppManager.getCurrentUser().getIdUser(),
            product.getIdProduct(),
            qty
        );

        if (!success)
            return "Failed to update cart item!";

        return null;
    }

    public static String deleteCartItem(Product product) {

        boolean success = CartItem.deleteCartItem(
            AppManager.getCurrentUser().getIdUser(),
            product.getIdProduct()
        );

        if (!success)
            return "Failed to remove cart item!";

        return null;
    }

}

package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.CartItem;
import model.Customer;
import model.OrderDetail;
import model.OrderHeader;
import model.Product;
import model.Promo;
import utils.AppManager;

public class CheckoutHandler {

    private static String generateOrderId() {
        DateTimeFormatter id = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return "OR" + LocalDateTime.now().format(id);
    }

	public static String checkout(String promoCode) {
        String customerId = AppManager.getCurrentUser().getIdUser();

        ArrayList<CartItem> cartItems = CartItem.getCartItems(customerId);

        if (cartItems.isEmpty()) return "Cart is empty!";

        double total = 0;
        for (CartItem c : cartItems) {
            total += c.getSubtotal();
        }

        String promoId = null;
        double discountPercent = 0;

        if (promoCode != null && !promoCode.isEmpty()) {
            Promo promo = Promo.getPromo(promoCode);
            
            if (promo == null) return "Promo code is invalid!";

            promoId = promo.getIdPromo();
            discountPercent = promo.getDiscountPercentage();
        }

        double discount = total * discountPercent / 100;
        double finalTotal = total - discount;

        double balance = Customer.getBalance(customerId);

        if (balance < finalTotal) return "Insufficient balance!";

        String orderId = generateOrderId();

        boolean headerSuccess = OrderHeader.createOrderHeader(orderId, customerId, promoId, finalTotal);

        if (!headerSuccess) return "Failed to create order!";

        for (CartItem c : cartItems) {
            Product p = c.getProduct();

            boolean detailSuccess = OrderDetail.createOrderDetail(orderId, p.getIdProduct(), c.getQty());
            if (!detailSuccess) return "Failed to create order detail!";

            boolean stockSuccess = Product.reduceStock(p.getIdProduct(), c.getQty());
            if (!stockSuccess) return "Failed to update product stock!";
        }

        boolean balanceSuccess = Customer.updateBalance(customerId, finalTotal);

        if (!balanceSuccess) return "Failed to update balance!";
        
        CartItem.clear(customerId);

        return null;
    }

}

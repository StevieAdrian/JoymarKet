package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Cart;
import model.Customer;
import model.OrderDetail;
import model.OrderHeader;
import model.Product;
import model.Promo;
import utils.AppManager;

public class CheckoutController {

    private static String generateOrderId() {
        DateTimeFormatter id = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return "OR" + LocalDateTime.now().format(id);
    }

	public static String checkout(String promoCode) {
        String customerId = AppManager.getCurrentUser().getIdUser();

        ArrayList<Cart> carts = Cart.getByCustomer(customerId);

        if (carts.isEmpty()) return "Cart is empty!";

        double total = 0;
        for (Cart c : carts) {
            total += c.getSubtotal();
        }

        String promoId = null;
        double discountPercent = 0;

        if (promoCode != null && !promoCode.isEmpty()) {
            Promo promo = Promo.findByCode(promoCode);
            
            if (promo == null) return "Promo code is invalid!";

            promoId = promo.getIdPromo();
            discountPercent = promo.getDiscountPercentage();
        }

        double discount = total * discountPercent / 100;
        double finalTotal = total - discount;

        double balance = Customer.getBalance(customerId);

        if (balance < finalTotal) return "Insufficient balance!";

        String orderId = generateOrderId();

        boolean headerSuccess = OrderHeader.create(orderId, customerId, promoId, finalTotal);

        if (!headerSuccess) return "Failed to create order!";

        for (Cart c : carts) {
            Product p = c.getProduct();

            boolean detailSuccess = OrderDetail.create(orderId, p.getIdProduct(), c.getQty());
            if (!detailSuccess) return "Failed to create order detail!";

            boolean stockSuccess = Product.reduceStock(p.getIdProduct(), c.getQty());
            if (!stockSuccess) return "Failed to update product stock!";
        }

        boolean balanceSuccess = Customer.updateBalance(customerId, finalTotal);

        if (!balanceSuccess) return "Failed to update balance!";
        
        Cart.clear(customerId);

        return null;
    }

}

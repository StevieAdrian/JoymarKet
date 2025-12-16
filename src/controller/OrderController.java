package controller;

import java.util.ArrayList;

import model.OrderHeader;
import utils.AppManager;

public class OrderController {

	public static ArrayList<OrderHeader> getCustomerOrders() {
        String customerId = AppManager.getCurrentUser().getIdUser();
        
        return OrderHeader.findByCustomer(customerId);
    }
	
	public static ArrayList<OrderHeader> getAllOrders() {
	    return OrderHeader.findAll();
	}
}

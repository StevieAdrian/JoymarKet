package controller;

import java.util.ArrayList;

import model.OrderHeader;
import utils.AppManager;

public class OrderHandler {

	public static ArrayList<OrderHeader> getCustomerOrders() {
        String customerId = AppManager.getCurrentUser().getIdUser();
        
        return OrderHeader.getCustomerOrders(customerId);
    }
	
	public static ArrayList<OrderHeader> getAllOrders() {
	    return OrderHeader.getAllOrders();
	}
}

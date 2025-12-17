package controller;

import java.util.ArrayList;

import enums.Status;
import model.Delivery;
import model.OrderHeader;
import utils.AppManager;

public class DeliveryController {

	public static String assignCourier(String idOrder, String idCourier) {
		if (idCourier == null || idCourier.isEmpty()) return "Courier must be selected!";

		boolean success = Delivery.create(idOrder, idCourier);
		if (!success) return "Failed to assign courier!";

		OrderHeader.updateStatus(idOrder, Status.IN_PROGRESS);
		
		return null; 
	}
	
	public static ArrayList<Delivery> getCourierDeliveries() {
        String courierId = AppManager.getCurrentUser().getIdUser();
        
        return Delivery.findByCourier(courierId);
    }

	public static String updateDeliveryStatus(String idOrder, Status newStatus) {
	    String courierId = AppManager.getCurrentUser().getIdUser();

	    boolean success = Delivery.updateStatus(idOrder, courierId, newStatus);
	    if (!success) return "Failed to update delivery status!";
	    if (newStatus == Status.DELIVERED) OrderHeader.updateStatus(idOrder, Status.DELIVERED);
	   

	    return null;
	}
}

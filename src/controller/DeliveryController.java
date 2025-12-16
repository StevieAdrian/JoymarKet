package controller;

import enums.Status;
import model.Delivery;
import model.OrderHeader;

public class DeliveryController {

	public static String assignCourier(String idOrder, String idCourier) {
		if (idCourier == null || idCourier.isEmpty()) return "Courier must be selected!";

		boolean success = Delivery.create(idOrder, idCourier);
		if (!success) return "Failed to assign courier!";

		OrderHeader.updateStatus(idOrder, Status.IN_PROGRESS);
		
		return null; 
	}
}

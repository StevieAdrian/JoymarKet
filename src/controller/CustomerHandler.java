package controller;

import model.Customer;
import utils.AppManager;

public class CustomerHandler {

	public static double getCurrentBalance() {
	    return Customer.getBalance(AppManager.getCurrentUser().getIdUser());
	}

}

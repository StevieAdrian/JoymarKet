package controller;

import model.Customer;
import utils.AppManager;

public class CustomerController {

	public static double getCurrentBalance() {
	    return Customer.getBalance(AppManager.getCurrentUser().getIdUser());
	}

}

package controller;

import java.util.ArrayList;

import model.Courier;

public class CourierController {

	public static ArrayList<Courier> getAllCouriers() {
	    return Courier.findAll();
	}

}

package model;

import enums.Role;

public class Courier extends User {

	public Courier(String idUser, String fullName, String email, String password, String phone, String address,
			String gender, Role role) {
		super(idUser, fullName, email, password, phone, address, gender, Role.COURIER);
		// TODO Auto-generated constructor stub
	}

}

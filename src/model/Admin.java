package model;

import enums.Role;

public class Admin extends User {

	public Admin(String idUser, String fullName, String email, String password, String phone, String address, String gender,
			Role role) {
		super(idUser, fullName, email, password, phone, address, gender, Role.ADMIN);
		// TODO Auto-generated constructor stub
	}

}

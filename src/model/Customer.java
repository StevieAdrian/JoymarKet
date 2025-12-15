package model;

import enums.Role;
import utils.DatabaseConnection;

public class Customer extends User {

	private Double balance;
	
	public Customer(String idUser, String fullName, String email, String password, String phone, String address,
			String gender) {
		super(idUser, fullName, email, password, phone, address, gender, Role.CUSTOMER);
		this.balance = 0.0;
		// TODO Auto-generated constructor stub
	}
	
	 public boolean register() {
        boolean userInserted = User.create(this);
        if (!userInserted) return false;

        String query = "INSERT INTO customers (idUser, balance) VALUES (?, ?)";
        return DatabaseConnection.update(query, this.idUser, this.balance);
    }
	 
	 public double getBalance() {
        return balance;
    }

}

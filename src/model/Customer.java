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
	 
	 public static double getBalance(String idCustomer) {
	    String query = "SELECT balance FROM customers WHERE idUser = ?";
	    try {
	        var rs = DatabaseConnection.query(query, idCustomer);
	        if (rs.next()) {
	            return rs.getDouble("balance");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

	 
	 public static boolean topUp(String idCustomer, double amount) {
	    String query = "UPDATE customers SET balance = balance + ? WHERE idUser = ?";
	    return DatabaseConnection.update(query, amount, idCustomer);
	}

}

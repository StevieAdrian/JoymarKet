package controller;

import javafx.scene.control.ToggleGroup;
import model.Customer;
import model.User;

public class AuthHandler {

	public static String register(User user, String confirmPassword) {
		if (user.getIdUser().isEmpty()) return "User ID must be filled!";
		if (user.getFullName().isEmpty()) return "Full name must be filled!";
		if (!user.getEmail().endsWith("@gmail.com")) return "Email must end with @gmail.com!";
		if (User.findByEmail(user.getEmail()) != null) return "Email already registered!";
		if (user.getPassword().length() < 6) return "Password must be at least 6 characters!";
		if (!user.getPassword().equals(confirmPassword)) return "Confirm password must match password!";
		if (user.getPhone().isEmpty()) return "Phone must be filled!";
		
		String phone = user.getPhone();
		
		for (int i = 0; i < phone.length(); i++) {
			if (!Character.isDigit(phone.charAt(i))) return "Phone must be numeric!";
		}
		
		if (phone.length() < 10 || phone.length() > 13) return "Phone length must be between 10-13 digits!";
		if (user.getAddress().isEmpty()) return "Address must be filled!";
		if (user.getGender() == null) return "Gender must be chosen";
		
		Customer customer = new Customer(
            user.getIdUser(),
            user.getFullName(),
            user.getEmail(),
            user.getPassword(),
            user.getPhone(),
            user.getAddress(),
            user.getGender()
        );
		
		boolean success = customer.registerAccount();
		if (!success) return "Failed to register user!";
		
		return null;
	}
	
	public static User login(String email, String password) {
	    if (email == null || email.isEmpty()) return null;
	    if (password == null || password.isEmpty()) return null;

	    User user = User.findByEmail(email);

	    if (user == null) return null;
	    if (!user.getPassword().equals(password)) return null;

	    return user;
	}


}

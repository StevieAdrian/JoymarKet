package controller;

import javafx.scene.control.ToggleGroup;
import model.User;

public class AuthController {

	public static String register(User user, String confirmPassword) {
		if (user.getId().isEmpty()) return "User ID must be filled!";
		if (user.getFullName().isEmpty()) return "Full name must be filled!";
		if (!user.getEmail().endsWith("@gmail.com")) return "Email must end with @gmail.com!";
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
		
		boolean success = User.create(user);
		if (!success) return "Failed to register user!";
		
		return null;
	}

}

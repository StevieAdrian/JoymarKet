package controller;

import model.User;

public class UserHandler {

	public static String updateProfile(String idUser, String fullName, String phone, String address) {
        if (fullName.isEmpty()) return "Full name must be filled!";
        if (phone.isEmpty()) return "Phone must be filled!";

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) return "Phone must be numeric!";
        }

        if (phone.length() < 10 || phone.length() > 13) return "Phone must be 10-13 digits!";
        if (address.isEmpty()) return "Address must be filled!";

        boolean success = User.editProfile(idUser, fullName, phone, address);
        if (!success) return "Failed to update profile!";

        return null;
    }

}

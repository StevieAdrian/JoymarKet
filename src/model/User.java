package model;

import utils.DatabaseConnection;

public class User {

    private String id;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String gender;
    private int role;

    public User(String id, String fullName, String email, String password, String phone, String address, String gender, int role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.role = role;
    }

    public static boolean create(User user) {
    	try {
    		String query = "INSERT INTO users (id, full_name, email, password, phone, address, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    		
    		return DatabaseConnection.update(
                query,
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getAddress(),
                user.getGender(),
                user.getRole()
//    				0
            );
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getGender() { return gender; }
    public int getRole() { return role; }
}

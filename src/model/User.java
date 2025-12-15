package model;

import enums.Role;
import utils.DatabaseConnection;

public class User {

    protected String idUser;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String gender;
    private Role role;

    public User(String idUser, String fullName, String email, String password, String phone, String address, String gender, Role role) {
        this.idUser = idUser;
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
    		String query = "INSERT INTO users (idUser, fullName, email, password, phone, address, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    		
    		return DatabaseConnection.update(
                query,
                user.getIdUser(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getAddress(),
                user.getGender(),
                user.getRole().name()
            );
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
    }
    
    public static User findByEmail(String email) {
    	User user = null;
    	try {
			String query = "SELECT * FROM users WHERE email = ?";
			
			var rs = DatabaseConnection.query(query, email);
			
			if (rs.next()) {
	            user = new User(
	                rs.getString("idUser"),
	                rs.getString("fullName"),
	                rs.getString("email"),
	                rs.getString("password"),
	                rs.getString("phone"),
	                rs.getString("address"),
	                rs.getString("gender"),
	                Role.valueOf(rs.getString("role"))
	            );
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	 
    	return user;
    }

    public String getIdUser() { return idUser; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getGender() { return gender; }
    public Role getRole() { return role; }
}

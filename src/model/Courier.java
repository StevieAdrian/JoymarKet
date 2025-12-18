package model;

import java.util.ArrayList;

import enums.Role;
import utils.DatabaseConnection;

public class Courier extends User {

	private String vehicleType;
    private String vehiclePlate;

    public Courier(String idUser, String fullName, String email, String password, String phone, String address, String gender, String vehicleType, String vehiclePlate) {
        super(idUser, fullName, email, password, phone, address, gender, Role.COURIER);
        this.vehicleType = vehicleType;
        this.vehiclePlate = vehiclePlate;
    }
    
    public static ArrayList<Courier> getAllCouriers() {
        ArrayList<Courier> couriers = new ArrayList<>();

        String query = "SELECT u.*, c.vehicleType, c.vehiclePlate FROM users u JOIN couriers c ON u.idUser = c.idUser";

        try {
            var rs = DatabaseConnection.query(query);
            while (rs.next()) {
                couriers.add(new Courier(
                    rs.getString("idUser"),
                    rs.getString("fullName"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("gender"),
                    rs.getString("vehicleType"),
                    rs.getString("vehiclePlate")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return couriers;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }
}

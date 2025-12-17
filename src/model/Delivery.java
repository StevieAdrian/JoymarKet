package model;

import java.util.ArrayList;

import enums.Status;
import utils.DatabaseConnection;

public class Delivery {

	private String idOrder;
    private String idCourier;
    private String status;

    public Delivery(String idOrder, String idCourier, String status) {
        this.idOrder = idOrder;
        this.idCourier = idCourier;
        this.status = status;
    }

    public static boolean create(String idOrder, String idCourier) {
        String query = "INSERT INTO deliveries (idOrder, idCourier, status) VALUES (?, ?, ?)";

        return DatabaseConnection.update(query, idOrder, idCourier, Status.IN_PROGRESS.name());
    }

    public static ArrayList<Delivery> findByCourier(String idCourier) {
        ArrayList<Delivery> list = new ArrayList<>();

        String query = "SELECT * FROM deliveries WHERE idCourier = ?";

        try {
            var rs = DatabaseConnection.query(query, idCourier);
            while (rs.next()) {
                list.add(new Delivery(
                    rs.getString("idOrder"),
                    rs.getString("idCourier"),
                    rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean updateStatus(String idOrder, String idCourier, Status status) {
        String query = "UPDATE deliveries SET status = ? WHERE idOrder = ? AND idCourier = ?";

        return DatabaseConnection.update(query, status.name(), idOrder, idCourier);
    }

    public String getIdOrder() { return idOrder; }
    public String getIdCourier() { return idCourier; }
    public String getStatus() { return status; }
}

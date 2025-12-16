package model;

import java.time.LocalDate;
import java.util.ArrayList;

import enums.Status;
import utils.DatabaseConnection;

public class OrderHeader {
	private String idOrder;
    private String idCustomer;
    private String status;
    private LocalDate orderedAt;
    private double totalAmount;

    public OrderHeader(String idOrder, String idCustomer, String status, LocalDate orderedAt, double totalAmount) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.status = status;
        this.orderedAt = orderedAt;
        this.totalAmount = totalAmount;
    }
    
	public static boolean create(String idOrder, String idCustomer, String idPromo, double totalAmount) {
        String query =
            "INSERT INTO order_headers (idOrder, idCustomer, idPromo, status, orderedAt, totalAmount) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        return DatabaseConnection.update(
            query,
            idOrder,
            idCustomer,
            idPromo,
            Status.PENDING.name(),
            LocalDate.now(),
            totalAmount
        );
    }
	
	public static ArrayList<OrderHeader> findByCustomer(String idCustomer) {
        ArrayList<OrderHeader> orders = new ArrayList<>();

        String query = "SELECT * FROM order_headers WHERE idCustomer = ?";

        try {
            var rs = DatabaseConnection.query(query, idCustomer);
            while (rs.next()) {
                orders.add(new OrderHeader(
                    rs.getString("idOrder"),
                    rs.getString("idCustomer"),
                    rs.getString("status"),
                    rs.getDate("orderedAt").toLocalDate(),
                    rs.getDouble("totalAmount")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }
	
	public String getIdOrder() { return idOrder; }
	public String getStatus() { return status; }
	public LocalDate getOrderedAt() { return orderedAt; }
	public double getTotalAmount() { return totalAmount; }

}

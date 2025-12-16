package model;

import java.time.LocalDate;

import enums.Status;
import utils.DatabaseConnection;

public class OrderHeader {

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

}

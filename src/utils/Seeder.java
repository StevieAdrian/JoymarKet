package utils;

public final class Seeder {
	private static void seedUsers() {
        String query =
            "INSERT INTO users (idUser, fullName, email, password, phone, address, gender, role) VALUES " +
            "('U001', 'Stevie Customer', 'stevie@gmail.com', 'password', '081234567890', 'Jakarta', 'Male', 'CUSTOMER'), " +
            "('U002', 'Charelle Admin', 'charelle@gmail.com', 'password', '081234567891', 'Bandung', 'Female', 'ADMIN'), " +
            "('U003', 'Rendy Courier', 'rendy@gmail.com', 'password', '081234567892', 'Surabaya', 'Male', 'COURIER')";
        DatabaseConnection.update(query);
    }

	 private static void seedCustomers() {
        String query =
            "INSERT INTO customers (idUser, balance) VALUES " +
            "('U001', 100000)";

        DatabaseConnection.update(query);
    }

    private static void seedAdmins() {
        String query =
            "INSERT INTO admins (idUser, emergencyContact) VALUES " +
            "('U002', '08111111111')";

        DatabaseConnection.update(query);
    }

    private static void seedCouriers() {
        String query =
            "INSERT INTO couriers (idUser, vehicleType, vehiclePlate) VALUES " +
            "('U003', 'Motorcycle', 'B 1234 XYZ')";

        DatabaseConnection.update(query);
    }

    private static void seedProducts() {
        String query =
            "INSERT INTO products (idProduct, name, price, stock, category) VALUES " +
            "('P001', 'Wiener Dog', 35000, 20, 'Doggo'), " +
            "('P002', 'Stevie Rebus', 75000, 15, 'Meat'), " +
            "('P003', 'Kucing Mentega', 12000, 50, 'Fruit'), " +
            "('P004', 'Pitbull Terbang', 10000, 40, 'Doggo'), " +
            "('P005', 'Susu Expired', 18000, 30, 'Dairy')";

        DatabaseConnection.update(query);
    }

    private static void seedPromos() {
        String query =
            "INSERT INTO promos (idPromo, code, headline, discountPercentage) VALUES " +
            "('PR001', 'PROMO10', 'Discount 10%', 10), " +
            "('PR002', 'PROMO20', 'Discount 20%', 20), " +
            "('PR003', 'PROMO5',  'Discount 5%', 5)";

        DatabaseConnection.update(query);
    }

    public static void run() {
        seedUsers();
        seedCustomers();
        seedAdmins();
        seedCouriers();
        seedProducts();
        seedPromos();

        System.out.println("Seeder executed successfully.");
    }
}

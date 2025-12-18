package model;

import utils.DatabaseConnection;

public class Promo {

	private String idPromo;
	private String code;
	private String headline;
	private double discountPercentage;

	public Promo(String idPromo, String code, String headline, double discountPercentage) {
		this.idPromo = idPromo;
		this.code = code;
		this.headline = headline;
		this.discountPercentage = discountPercentage;
	}

	public static Promo getPromo(String code) {
		String query = "SELECT * FROM promos WHERE code = ?";

		try {
			var rs = DatabaseConnection.query(query, code);

			if (rs.next()) {
				return new Promo(
					rs.getString("idPromo"),
					rs.getString("code"),
					rs.getString("headline"),
					rs.getDouble("discountPercentage")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getIdPromo() {
		return idPromo;
	}

	public String getCode() {
		return code;
	}

	public String getHeadline() {
		return headline;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}
}

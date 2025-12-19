package controller;

import model.Customer;
import utils.AppManager;

public class TopUpHandler {

    public static String topUp(String amountText) {

        if (amountText.isEmpty()) return "Amount must be filled!";

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (Exception e) {
            return "Amount must be numeric!";
        }

        if (amount < 10000) return "Minimum top up is 10,000!";

        boolean success = Customer.topUpBalance(AppManager.getCurrentUser().getIdUser(), amount);

        if (!success) return "Failed to top up balance!";

        return null;
    }

}

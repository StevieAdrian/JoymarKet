package view;

import controller.CheckoutController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.AppManager;
import view.page.Page;

public class CheckoutPage extends Page {
	private VBox container;
	
	public CheckoutPage() {
		// TODO Auto-generated constructor stub
		init();
	}

	@Override
	protected void start() {
		// TODO Auto-generated method stub
        container = new VBox(15);
        container.setPadding(new Insets(20));
        container.setAlignment(Pos.CENTER);
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		Label title = new Label("Checkout");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label promoInfo = new Label("Promo code (optional)");
        promoInfo.setStyle("-fx-font-size: 11px; -fx-text-fill: #666;");

        TextField promoField = new TextField();
        promoField.setPromptText("Enter promo code");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button checkoutBtn = new Button("Place Order");
        Button backBtn = new Button("Back");

        checkoutBtn.setOnAction(e -> {
            String error = CheckoutController.checkout(promoField.getText());

            if (error != null) {
                errorLabel.setText(error);
            } else {
                AppManager.navigate(new ProductPage(), "Products");
            }
        });

        backBtn.setOnAction(e ->
            AppManager.navigate(new ViewCartPage(), "My Cart")
        );

        container.getChildren().addAll(
            title,	
            promoInfo,
            promoField,
            errorLabel,
            checkoutBtn,
            backBtn
        );

        setCenter(container);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setEvent() {
		// TODO Auto-generated method stub

	}

}

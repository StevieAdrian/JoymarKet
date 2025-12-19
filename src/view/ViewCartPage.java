package view;

import java.util.ArrayList;

import controller.CartItemHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CartItem;
import model.Product;
import utils.AppManager;
import view.page.Page;

public class ViewCartPage extends Page {
	
	private VBox container;
	
	public ViewCartPage() {
        super();
        init();
    }
	
	@Override
	protected void start() {
		// TODO Auto-generated method stub
		container = new VBox(15);
        container.setPadding(new Insets(20));
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		Label title = new Label("My Cart");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e ->
            AppManager.navigate(new ProductPage(), "Products")
        );

        HBox header = new HBox(20, title, backBtn);
        header.setAlignment(Pos.CENTER_LEFT);

        GridPane table = new GridPane();
        table.setHgap(15);
        table.setVgap(10);

        table.add(new Label("Product"), 0, 0);
        table.add(new Label("Price"), 1, 0);
        table.add(new Label("Qty"), 2, 0);
        table.add(new Label("Subtotal"), 3, 0);
        table.add(new Label("Action"), 4, 0);

        ArrayList<CartItem> cartItems = CartItemHandler.getCartItems();

        int row = 1;
        double total = 0;

        if (cartItems.isEmpty()) {
            table.add(new Label("Cart is empty"), 0, 1);
        }

        for (CartItem c : cartItems) {
            Product p = c.getProduct();

            TextField qtyField = new TextField(String.valueOf(c.getQty()));
            qtyField.setPrefWidth(60);

            Label subtotalLabel = new Label("Rp " + c.getSubtotal());
            total += c.getSubtotal();

            Button updateBtn = new Button("Update");
            Button removeBtn = new Button("Remove");

            Label errorLabel = new Label();
            errorLabel.setStyle("-fx-text-fill: red;");

            updateBtn.setOnAction(e -> {
                String error = CartItemHandler.editCartItem(p, qtyField.getText());
                if (error != null) {
                    errorLabel.setText(error);
                } else {
                    AppManager.navigate(new ViewCartPage(), "My Cart");
                }
            });

            removeBtn.setOnAction(e -> {
                CartItemHandler.deleteCartItem(p);
                AppManager.navigate(new ViewCartPage(), "My Cart");
            });

            VBox actionBox = new VBox(5, updateBtn, removeBtn, errorLabel);

            table.add(new Label(p.getName()), 0, row);
            table.add(new Label("Rp " + p.getPrice()), 1, row);
            table.add(qtyField, 2, row);
            table.add(subtotalLabel, 3, row);
            table.add(actionBox, 4, row);

            row++;
        }

        Label totalLabel = new Label("Total: Rp " + total);
        totalLabel.setStyle("-fx-font-weight: bold;");

        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setDisable(cartItems.isEmpty()); 

        checkoutBtn.setOnAction(e -> {
        	AppManager.navigate(new CheckoutPage(), "Checkout");
        });

        HBox footer = new HBox(20, totalLabel, checkoutBtn);
        footer.setAlignment(Pos.CENTER_RIGHT);

        container.getChildren().addAll(header, table, footer);
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

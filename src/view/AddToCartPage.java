package view;

import javax.management.RuntimeErrorException;

import controller.CartController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Product;
import utils.AppManager;
import view.page.Page;

public class AddToCartPage extends Page {

    private VBox container;
    private Product product;

    public AddToCartPage(Product product) {
    	super();
        this.product = product;
        init();
    }

    @Override
    protected void start() {
    	container = new VBox(10);
    }

    @Override
    protected void setLayout() {
        Label title = new Label("Add To Cart");
        Label nameLabel = new Label("Product : " + product.getName());
        Label stockLabel = new Label("Stock   : " + product.getStock());

        TextField qtyField = new TextField();
        qtyField.setPromptText("Quantity");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addBtn = new Button("Add");
        Button backBtn = new Button("Back");

        addBtn.setOnAction(e -> {
            String error = CartController.addToCart(product, qtyField.getText());
            if (error != null) {
                errorLabel.setText(error);
            } else {
                AppManager.navigate(new ProductPage(), "Products");
            }
        });

        backBtn.setOnAction(e -> {
            AppManager.navigate(new ProductPage(), "Products");
        });

        container.getChildren().addAll(
            title,
            nameLabel,
            stockLabel,
            qtyField,
            errorLabel,
            addBtn,
            backBtn
        );

        setCenter(container);
    }

    @Override
    protected void setStyle() {}

    @Override
    protected void setEvent() {}
}

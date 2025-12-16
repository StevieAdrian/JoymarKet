package view;

import javax.management.RuntimeErrorException;

import controller.CartController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Product : " + product.getName());
        Label stockLabel = new Label("Stock   : " + product.getStock());

        VBox infoBox = new VBox(5, nameLabel, stockLabel);
        infoBox.setAlignment(Pos.CENTER_LEFT);

        TextField qtyField = new TextField();
        qtyField.setPromptText("Quantity");
        qtyField.setMaxWidth(Double.MAX_VALUE);

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addBtn = new Button("Add");
        Button backBtn = new Button("Back");

        addBtn.setPrefWidth(100);
        backBtn.setPrefWidth(100);

        HBox buttonBox = new HBox(10, backBtn, addBtn);
        buttonBox.setAlignment(Pos.CENTER);

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
            infoBox,
            qtyField,
            errorLabel,
            buttonBox
        );

        setCenter(container);
    }

    @Override
    protected void setStyle() {}

    @Override
    protected void setEvent() {}
}

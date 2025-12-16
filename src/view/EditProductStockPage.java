package view;

import controller.ProductController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Product;
import utils.AppManager;
import view.page.Page;

public class EditProductStockPage extends Page {

    private Product product;

    public EditProductStockPage(Product product) {
        this.product = product;
        init();
    }

	@Override
	protected void start() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		Label title = new Label("Edit Product Stock");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Product Name: " + product.getName());
        Label currentStockLabel = new Label("Current Stock: " + product.getStock());

        TextField stockField = new TextField();
        stockField.setPromptText("New Stock");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button saveBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");

        saveBtn.setOnAction(e -> {
            String error = ProductController.updateStock(
                product.getIdProduct(),
                stockField.getText()
            );

            if (error != null) {
                errorLabel.setText(error);
            } else {
                AppManager.navigate(new ProductPage(), "Products");
            }
        });

        cancelBtn.setOnAction(e ->
            AppManager.navigate(new ProductPage(), "Products")
        );

        VBox container = new VBox(
            10,
            title,
            nameLabel,
            currentStockLabel,
            stockField,
            errorLabel,
            saveBtn,
            cancelBtn
        );

        container.setPadding(new Insets(20));
        container.setAlignment(Pos.CENTER);

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

package view;

import java.util.ArrayList;

import controller.CustomerController;
import controller.ProductController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Product;
import utils.AppManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.page.Page;

public class ProductPage extends Page{

	private VBox container;
	
	 public ProductPage() {
        super();
        init();
    }

    @Override
    protected void start() {
        container = new VBox(10);
        container.setPadding(new Insets(20));
    }

    @Override
    protected void setLayout() {
        Label title = new Label("Product List");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        Label balanceLabel = new Label("Balance: Rp " + CustomerController.getCurrentBalance());

        Button viewCartBtn = new Button("View Cart");
        Button topUpBtn = new Button("Top Up");
        
        HBox header = new HBox(20, title, balanceLabel, viewCartBtn, topUpBtn);
        header.setAlignment(Pos.CENTER_LEFT);
        
        GridPane table = new GridPane();
        table.setHgap(15);
        table.setVgap(10);

        table.add(new Label("Name"), 0, 0);
        table.add(new Label("Price"), 1, 0);
        table.add(new Label("Stock"), 2, 0);
        table.add(new Label("Category"), 3, 0);
		table.add(new Label("Action"), 4, 0);

        ArrayList<Product> products = ProductController.getProducts();

        int i = 1;
        for (Product p : products) {
            table.add(new Label(p.getName()), 0, i);
            table.add(new Label("Rp " + p.getPrice()), 1, i);
            table.add(new Label(String.valueOf(p.getStock())), 2, i);
            table.add(new Label(p.getCategory()), 3, i);

			Button addBtn = new Button("Add");
            table.add(addBtn, 4, i);

            addBtn.setOnAction(e -> {
            	System.out.println("DEBUG Product ID = " + p.getIdProduct());

            	AppManager.navigate(new AddToCartPage(p), "Add To Cart");
            });

            i++;
        }
        
        viewCartBtn.setOnAction(e -> {
            AppManager.navigate(new ViewCartPage(), "My Cart");
        });
        
        topUpBtn.setOnAction(e -> {
            AppManager.navigate(new TopUpPage(), "Top Up");
        });
        
        container.getChildren().addAll(header, table);
        setCenter(container);
    }

    @Override
    protected void setStyle() {}

    @Override
    protected void setEvent() {}
}

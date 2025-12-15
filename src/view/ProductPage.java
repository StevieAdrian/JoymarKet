package view;

import java.util.ArrayList;

import controller.ProductController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Product;
import javafx.scene.control.Label;
import view.page.Page;

public class ProductPage extends Page{

	private VBox container;

    @Override
    protected void start() {
        container = new VBox(10);
        container.setPadding(new Insets(20));
    }

    @Override
    protected void setLayout() {
        Label title = new Label("Product List");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane table = new GridPane();
        table.setHgap(15);
        table.setVgap(10);

        table.add(new Label("Name"), 0, 0);
        table.add(new Label("Price"), 1, 0);
        table.add(new Label("Stock"), 2, 0);
        table.add(new Label("Category"), 3, 0);

        ArrayList<Product> products = ProductController.getProducts();

        int i = 1;
        for (Product p : products) {
            table.add(new Label(p.getName()), 0, i);
            table.add(new Label("Rp " + p.getPrice()), 1, i);
            table.add(new Label(String.valueOf(p.getStock())), 2, i);
            table.add(new Label(p.getCategory()), 3, i);
            i++;
        }

        container.getChildren().addAll(title, table);
        setCenter(container);
    }

    @Override
    protected void setStyle() {}

    @Override
    protected void setEvent() {}
}

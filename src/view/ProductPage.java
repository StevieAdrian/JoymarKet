package view;

import java.util.ArrayList;

import controller.CustomerController;
import controller.ProductController;
import enums.Role;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Product;
import utils.AppManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.page.Page;

public class ProductPage extends Page{

	private VBox container;
	private TableView<Product> table;
	
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
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label balanceLabel = new Label("Balance: Rp " + CustomerController.getCurrentBalance());
        balanceLabel.setStyle("-fx-font-size: 14px;");

        Button viewCartBtn = new Button("View Cart");
        Button topUpBtn = new Button("Top Up");
        Button orderHistoryBtn = new Button("Order History");
        Button adminOrdersBtn = new Button("View All Orders");


        viewCartBtn.setPrefWidth(100);
        topUpBtn.setPrefWidth(100);
        orderHistoryBtn.setPrefWidth(120);
        adminOrdersBtn.setPrefWidth(140);
        
        HBox actionBox = new HBox(10);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        Role role = AppManager.getCurrentUser().getRole();
        
        if (role == Role.CUSTOMER) {
            actionBox.getChildren().addAll(viewCartBtn, topUpBtn, orderHistoryBtn);
        } else if (role == Role.ADMIN) {
            actionBox.getChildren().add(adminOrdersBtn);
        }

        BorderPane header = new BorderPane();
        VBox leftHeader = new VBox(5, title);

        if (role == Role.CUSTOMER) {
            leftHeader.getChildren().add(balanceLabel);
        }
        header.setLeft(leftHeader);
        header.setRight(actionBox);

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Product, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Product, Void> actionCol = new TableColumn<>("Action");

        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button actionBtn = new Button(AppManager.getCurrentUser().getRole() == Role.ADMIN ? "Edit Stock" : "Add");
            {
                actionBtn.setMaxWidth(Double.MAX_VALUE);

                actionBtn.setOnAction(e -> {
                    Product p = getTableView().getItems().get(getIndex());

                    if (AppManager.getCurrentUser().getRole() == Role.ADMIN) AppManager.navigate(new EditProductStockPage(p), "Edit Product Stock");
                    else AppManager.navigate(new AddToCartPage(p), "Add To Cart");
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) setGraphic(null);
                else setGraphic(actionBtn);
            }
        });

        table.getColumns().addAll(nameCol, priceCol, stockCol, categoryCol, actionCol);

        ArrayList<Product> products = ProductController.getProducts();

        table.getItems().addAll(products);

        viewCartBtn.setOnAction(e ->
            AppManager.navigate(new ViewCartPage(), "My Cart")
        );

        topUpBtn.setOnAction(e ->
            AppManager.navigate(new TopUpPage(), "Top Up")
        );
        
        orderHistoryBtn.setOnAction(e ->
		    AppManager.navigate(new OrderHistoryPage(), "Order History")
		);
        
        adminOrdersBtn.setOnAction(e ->
	        AppManager.navigate(new AdminOrderPage(), "All Orders")
	    );

        container.getChildren().addAll(header, table);
        setCenter(container);
    }

    @Override
    protected void setStyle() {}

    @Override
    protected void setEvent() {}
}

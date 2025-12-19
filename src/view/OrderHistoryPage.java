package view;

import java.util.ArrayList;

import controller.CustomerHandler;
import controller.OrderHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.OrderHeader;
import utils.AppManager;
import view.page.Page;

public class OrderHistoryPage extends Page {
	
	private VBox container;
    private TableView<OrderHeader> table;

	public OrderHistoryPage() {
		// TODO Auto-generated constructor stub
		super();
		init();
	}

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		container = new VBox(10);
		container.setPadding(new Insets(20));
    }	

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		Label title = new Label("Order History");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label balanceLabel = new Label("Balance: Rp " + CustomerHandler.getCurrentBalance());
        balanceLabel.setStyle("-fx-font-size: 14px;");

        Button backBtn = new Button("Back to Products");
        backBtn.setPrefWidth(150);

        HBox actionBox = new HBox(backBtn);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane header = new BorderPane();
        header.setLeft(new VBox(5, title, balanceLabel));
        header.setRight(actionBox);

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<OrderHeader, String> idCol = new TableColumn<>("Order ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("idOrder"));

        TableColumn<OrderHeader, String> dateCol = new TableColumn<>("Order Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("orderedAt"));

        TableColumn<OrderHeader, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<OrderHeader, Double> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        table.getColumns().addAll(idCol, dateCol, statusCol, totalCol);

        ArrayList<OrderHeader> orders = OrderHandler.getCustomerOrders();
        table.getItems().addAll(orders);

        backBtn.setOnAction(e ->
            AppManager.navigate(new ProductPage(), "Products")
        );

        container.getChildren().addAll(header, table);
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

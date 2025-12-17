package view;

import java.util.ArrayList;

import controller.DeliveryController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Delivery;
import utils.AppManager;
import view.page.Page;

public class CourierDeliveryPage extends Page {
	private VBox container;
    private TableView<Delivery> table;
    
	public CourierDeliveryPage() {
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
		Label title = new Label("My Deliveries");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back");
        backBtn.setPrefWidth(100);
        
        Button editBtn = new Button("Edit Profile");
        editBtn.setPrefWidth(100);

        HBox actionBox = new HBox(backBtn, editBtn);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane header = new BorderPane();
        header.setLeft(title);
        header.setRight(actionBox);

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Delivery, String> orderCol = new TableColumn<>("Order ID");
        orderCol.setCellValueFactory(new PropertyValueFactory<>("idOrder"));

        TableColumn<Delivery, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Delivery, Void> actionCol = new TableColumn<>("Action");
        actionCol.setPrefWidth(120);
        
		actionCol.setCellFactory(col -> new TableCell<>() {
		    private final Button editBtn = new Button("Update");
		    {
		        editBtn.setOnAction(e -> {
		            Delivery delivery = getTableView().getItems().get(getIndex());
		            AppManager.navigate(new EditDeliveryStatusPage(delivery), "Update Delivery");
		        });
		    }
		
		    @Override
		    protected void updateItem(Void item, boolean empty) {
		        super.updateItem(item, empty);
		        setGraphic(empty ? null : editBtn);
		    }
		});

        table.getColumns().addAll(orderCol, statusCol, actionCol);

        ArrayList<Delivery> deliveries = DeliveryController.getCourierDeliveries();
        table.getItems().addAll(deliveries);

        backBtn.setOnAction(e ->
            AppManager.navigate(new ProductPage(), "Products")
        );
        
        editBtn.setOnAction(e ->
	        AppManager.navigate(new EditProfilePage(), "Edit Profile")
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

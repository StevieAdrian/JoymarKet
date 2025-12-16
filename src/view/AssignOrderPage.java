package view;

import java.util.ArrayList;

import controller.CourierController;
import controller.DeliveryController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Courier;
import model.OrderHeader;
import utils.AppManager;
import view.page.Page;

public class AssignOrderPage extends Page {
	 private VBox container;
	    private OrderHeader order;

	    private ComboBox<Courier> courierBox;
	    private Label errorLabel;

	    public AssignOrderPage(OrderHeader order) {
	        super();
	        this.order = order;
	        init();
	    }

	    @Override
	    protected void start() {
	        container = new VBox(10);
	        container.setPadding(new Insets(20));
	        container.setAlignment(Pos.CENTER);
	    }

	    @Override
	    protected void setLayout() {
	        Label title = new Label("Assign Courier");
	        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

	        Label orderLabel = new Label("Order ID: " + order.getIdOrder());

	        courierBox = new ComboBox<>();
	        courierBox.setPromptText("Select Courier");

	        ArrayList<Courier> couriers = CourierController.getAllCouriers();
	        courierBox.getItems().addAll(couriers);

	        courierBox.setCellFactory(cb -> new javafx.scene.control.ListCell<>() {
	            @Override
	            protected void updateItem(Courier item, boolean empty) {
	                super.updateItem(item, empty);
	                setText(empty || item == null ? null : item.getIdUser());
	            }
	        });
	        courierBox.setButtonCell(courierBox.getCellFactory().call(null));

	        errorLabel = new Label();
	        errorLabel.setStyle("-fx-text-fill: red;");

	        Button assignBtn = new Button("Assign");
	        Button backBtn = new Button("Back");

	        assignBtn.setPrefWidth(120);
	        backBtn.setPrefWidth(120);

	        container.getChildren().addAll(
	            title,
	            orderLabel,
	            courierBox,
	            errorLabel,
	            assignBtn,
	            backBtn
	        );

	        setCenter(container);

	        assignBtn.setOnAction(e -> handleAssign());
	        backBtn.setOnAction(e ->
	            AppManager.navigate(new AdminOrderPage(), "All Orders")
	        );
	    }

	    private void handleAssign() {
	        Courier selected = courierBox.getValue();

	        String error = DeliveryController.assignCourier(order.getIdOrder(), selected != null ? selected.getIdUser() : null);

	        if (error != null) {
	            errorLabel.setText(error);
	        } else {
	            AppManager.navigate(new AdminOrderPage(), "All Orders");
	        }
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

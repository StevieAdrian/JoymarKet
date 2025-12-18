package view;

import controller.DeliveryHandler;
import enums.Status;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Delivery;
import utils.AppManager;
import view.page.Page;

public class EditDeliveryStatusPage extends Page {
	private VBox container;
	private Delivery delivery;
    private ComboBox<Status> statusBox;
    private Label errorLabel;
	
	public EditDeliveryStatusPage(Delivery delivery) {
		// TODO Auto-generated constructor stub
		super();
		this.delivery = delivery;
		init();
	}

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		container = new VBox(10);
        container.setPadding(new Insets(20));
        container.setAlignment(Pos.CENTER);
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		Label title = new Label("Update Delivery Status");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label orderLabel = new Label("Order ID: " + delivery.getIdOrder());

        statusBox = new ComboBox<>();
        statusBox.getItems().addAll(
            Status.IN_PROGRESS,
            Status.DELIVERED
        );
        
        statusBox.setValue(Status.valueOf(delivery.getStatus()));

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button saveBtn = new Button("Save");
        Button backBtn = new Button("Back");

        saveBtn.setPrefWidth(120);
        backBtn.setPrefWidth(120);

        saveBtn.setOnAction(e -> handleSave());
        backBtn.setOnAction(e ->
            AppManager.navigate(new CourierDeliveryPage(), "My Deliveries")
        );

        container.getChildren().addAll(title, orderLabel, statusBox, errorLabel, saveBtn, backBtn);

        setCenter(container);
	}

	private void handleSave() {
        Status selected = statusBox.getValue();

        String error = DeliveryHandler.editDeliveryStatus(delivery.getIdOrder(), selected);

        if (error != null) errorLabel.setText(error);
        else AppManager.navigate(new CourierDeliveryPage(), "My Deliveries");
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

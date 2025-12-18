package view;

import controller.UserHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.User;
import utils.AppManager;
import view.page.Page;

public class EditProfilePage extends Page {
	private VBox container;
    private User user;
    
	public EditProfilePage() {
		// TODO Auto-generated constructor stub
		super();
        this.user = AppManager.getCurrentUser();
        init();
	}

	@Override
	protected void start() {
		// TODO Auto-generated method stub
        container = new VBox(10);
        container.setPadding(new Insets(20));
        container.setAlignment(Pos.CENTER);
	}

	private void goBack() {
	    switch (user.getRole()) {
	        case CUSTOMER:
	        case ADMIN:
	            AppManager.navigate(new ProductPage(), "Product Page");
	            break;
	        case COURIER:
	            AppManager.navigate(new CourierDeliveryPage(), "Courier");
	            break;
	    }
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		Label title = new Label("Edit Profile");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField nameField = new TextField(user.getFullName());
        nameField.setPromptText("Full Name");

        TextField phoneField = new TextField(user.getPhone());
        phoneField.setPromptText("Phone");

        TextField addressField = new TextField(user.getAddress());
        addressField.setPromptText("Address");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button saveBtn = new Button("Save");
        Button backBtn = new Button("Back");

        saveBtn.setPrefWidth(120);
        backBtn.setPrefWidth(120);

        saveBtn.setOnAction(e -> {
            String error = UserHandler.updateProfile(user.getIdUser(), nameField.getText(), phoneField.getText(), addressField.getText());

            if (error != null) {
                errorLabel.setText(error);
            } else {
            	user.setFullName(nameField.getText());
        	    user.setPhone(phoneField.getText());
        	    user.setAddress(addressField.getText());
            	    
                AppManager.navigate(new ProductPage(), "Home");
            }
        });

        backBtn.setOnAction(e -> goBack());

        container.getChildren().addAll(title, nameField, phoneField, addressField, errorLabel, saveBtn, backBtn);

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

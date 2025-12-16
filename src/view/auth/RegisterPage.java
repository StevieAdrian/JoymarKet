package view.auth;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import controller.AuthController;
import enums.Role;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import model.User;
import utils.AppManager;
import view.page.Page;

public final class RegisterPage extends Page {
	
	private VBox container;
	
	private Label titleLabel;
	private Label errorLabel;
	
	private TextField idField;
	private TextField nameField;
	private TextField emailField;
	private PasswordField passwordField;
	private PasswordField confirmPasswordField;
	private TextField phoneField;
	private TextField addressField;
	
	private RadioButton maleRadio;
	private RadioButton femaleRadio;
	private ToggleGroup genderGroup;
	
	private Button registerButton;
	private Hyperlink loginLink;
	
	public RegisterPage() {
        init();
    }
	
	@Override
	public void start() {
		container = new VBox(10);

        titleLabel = new Label("Register");
        errorLabel = new Label();
        
        idField = new TextField();
        idField.setPromptText("User ID");

        nameField = new TextField();
        nameField.setPromptText("Full Name");
        
        emailField = new TextField();
        emailField.setPromptText("Email");
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        
        phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        
        addressField = new TextField();
        addressField.setPromptText("Address");
        
        genderGroup	= new ToggleGroup();
        
        maleRadio = new RadioButton("Male");
        femaleRadio = new RadioButton("Female");
        
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        		
        registerButton = new Button("Register");
        loginLink = new Hyperlink("Already have an account? Login");
	}
	
	@Override
	protected void setLayout() {
		HBox genderBox = new HBox(10, maleRadio, femaleRadio);
		genderBox.setAlignment(Pos.CENTER);
				
		container.getChildren().addAll(
            titleLabel,
            idField,
            nameField,
            emailField,
            passwordField,
            confirmPasswordField,
            phoneField,
            addressField,
            genderBox,
            errorLabel,
            registerButton,
            loginLink
		);
		
		setCenter(container);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub
		container.setStyle("-fx-alignment: center;");
		errorLabel.setStyle("-fx-text-fill: red;");
	}

	@Override
	protected void setEvent() {
		// TODO Auto-generated method stub
		registerButton.setOnAction(e -> {
			String gender = null;
			if (genderGroup.getSelectedToggle() != null) {
				RadioButton selected = (RadioButton) genderGroup.getSelectedToggle();
				gender = selected.getText();
			}
			
			User user = new User(
				idField.getText(),
				nameField.getText(),
				emailField.getText(),
				passwordField.getText(),
				phoneField.getText(),
				addressField.getText(),
				gender,
				Role.CUSTOMER
			);
			
			String error = AuthController.register(user, confirmPasswordField.getText());
			
			if (error != null) errorLabel.setText(error);
			else errorLabel.setText("");
			
		});
		
		loginLink.setOnAction(e -> {
			AppManager.navigate(new LoginPage(), "Login");
		});
	}
}

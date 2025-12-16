package view.auth;

import controller.AuthController;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import model.User;
import utils.AppManager;
import view.AdminOrderPage;
import view.ProductPage;
import view.page.Page;

public class LoginPage extends Page {

private VBox container;
	
	private Label titleLabel;
	private Label errorLabel;
	
	private TextField emailField;
	private PasswordField passwordField;
	
	private Button loginButton;
	private Hyperlink registerLink;
	
	public LoginPage() {
        init();
    }
	
	@Override
	protected void start() {
		// TODO Auto-generated method stub
		container = new VBox(10);

        titleLabel = new Label("Register");
        errorLabel = new Label();
        
        emailField = new TextField();
        emailField.setPromptText("Email");
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        
        loginButton = new Button("Login");
        registerLink = new Hyperlink("Don't have an account? Register");
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		container.getChildren().addAll(
	            titleLabel,
	            emailField,
	            passwordField,
	            errorLabel,
	            loginButton,
	            registerLink
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
		loginButton.setOnAction(e -> {
		    User user = AuthController.login(
		        emailField.getText(),
		        passwordField.getText()
		    );

		    if (user == null) {
		        errorLabel.setText("Invalid email or password!");
		        return;
		    }

		    errorLabel.setText("");
		    AppManager.setCurrentUser(user);
		    
		    switch (user.getRole()) {
		        case CUSTOMER:
		        	AppManager.navigate(new ProductPage(), "Customer");
		        	break;
		        case ADMIN:
		        	AppManager.navigate(new ProductPage(), "Admin");
		        	break;
//		        case COURIER:
//		        	AppManager.navigate(new , "Courier");
//		        	break;
		    }
		});

		
		registerLink.setOnAction(e -> {
			AppManager.navigate(new RegisterPage(), "Register");
		});
	}

}

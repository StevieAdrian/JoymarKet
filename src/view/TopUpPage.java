package view;

import controller.TopUpHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.AppManager;
import view.page.Page;

public class TopUpPage extends Page {

	private VBox container;
	
	public TopUpPage() {
		// TODO Auto-generated constructor stub
		super();
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
		Label title = new Label("Top Up Balance");
        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button topUpBtn = new Button("Top Up");
        Button backBtn = new Button("Back");

        topUpBtn.setOnAction(e -> {
            String error = TopUpHandler.topUp(amountField.getText());
            
            if (error != null) {
                errorLabel.setText(error);
            } else {
                errorLabel.setText("Top up successful!");
            }
        });

        backBtn.setOnAction(e ->
            AppManager.navigate(new ProductPage(), "Products")
        );

        container.getChildren().addAll(
            title,
            amountField,
            errorLabel,
            topUpBtn,
            backBtn
        );

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

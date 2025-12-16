package view;

import java.util.ArrayList;

import controller.CourierController;
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
import model.Courier;
import utils.AppManager;
import view.page.Page;

public class AdminCourierPage extends Page {
    private VBox container;
    private TableView<Courier> table;
    
	public AdminCourierPage() {
		// TODO Auto-generated constructor stub
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
		Label title = new Label("All Couriers");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e ->
            AppManager.navigate(new ProductPage(), "Products")
        );

        HBox actionBox = new HBox(backBtn);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane header = new BorderPane();
        header.setLeft(title);
        header.setRight(actionBox);

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Courier, String> idCol = new TableColumn<>("Courier ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("idUser"));

        TableColumn<Courier, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<Courier, String> vehicleTypeCol = new TableColumn<>("Vehicle Type");
        vehicleTypeCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

        TableColumn<Courier, String> plateCol = new TableColumn<>("Vehicle Plate");
        plateCol.setCellValueFactory(new PropertyValueFactory<>("vehiclePlate"));

        table.getColumns().addAll(idCol, nameCol, vehicleTypeCol, plateCol);

        ArrayList<Courier> couriers = CourierController.getAllCouriers();
        table.getItems().addAll(couriers);

        VBox container = new VBox(10, header, table);
        container.setPadding(new Insets(20));

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

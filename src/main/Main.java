package main;
//import controller.LoginController;
import javafx.stage.Stage;
import utils.AppManager;
import javafx.application.Application;
import javafx.scene.Scene;
//import view.LoginView;
import view.auth.LoginPage;

public class Main extends Application {

	@Override	
	public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new LoginPage(), 900, 600);
        AppManager.start(stage, scene);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

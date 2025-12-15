package main;
//import controller.LoginController;
import javafx.stage.Stage;
import utils.AppManager;
import utils.Seeder;
import javafx.application.Application;
import javafx.scene.Scene;
//import view.LoginView;
import view.auth.LoginPage;

public class Main extends Application {

	/**
     * For database seeding
     * Only for initial setup
     * Change the flag to false after running the code once
     */
    private static final boolean RUN_SEEDER = true;

	@Override	
	public void start(Stage stage) throws Exception {
		if (RUN_SEEDER) {
            Seeder.run();
        }
		 
        Scene scene = new Scene(new LoginPage(), 900, 600);
        AppManager.start(stage, scene);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

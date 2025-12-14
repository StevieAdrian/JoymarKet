package utils;

import javafx.scene.Scene;
import javafx.stage.Stage;

public final class AppManager {
	private static Stage stage;
	
	private AppManager() {}
	
	public static void start(Stage stage2, Scene scene) {
		stage = stage2;
		stage.setScene(scene);
		stage.show();
	}
	
	public static void setTitle(String title) {
		stage.setTitle(title);
	}
}

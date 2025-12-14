package utils;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.page.Page;

public final class AppManager {
	private static Stage stage;
	private static Scene scene;
	
	private AppManager() {}
	
	public static void start(Stage stage2, Scene scene2) {
		stage = stage2;
		scene = scene2;
		stage.setScene(scene);
		stage.show();
	}
	
	public static void setTitle(String title) {
		stage.setTitle(title);
	}
	
	public static void navigate(Page page, String title) {
        scene.setRoot(page);
        stage.setTitle(title);
    }
}

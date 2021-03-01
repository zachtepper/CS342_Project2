import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

public class JavaFXTemplate extends Application {
	public static HashMap<String, Scene> sceneMap = new HashMap<String, Scene>();
	public static Scene currentScene = new Scene(new VBox(), 700, 700);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Connect Four");

		// place all scenes into the hash map
		sceneMap.put("welcome", AllScenes.welcomeScene());
		sceneMap.put("gameplay", AllScenes.gamePlayScene());

		// start program with welcome scene
		currentScene = sceneMap.get("welcome");

		primaryStage.setScene(currentScene);
		primaryStage.show();
	}


}

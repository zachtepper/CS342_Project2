import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

// A class containing all scenes needed for the program

public class AllScenes {

    public static Scene welcomeScene() {
        // Text object to create a title
        Text welcome_text = new Text("Welcome to Connect Four");

        // will start the game when clicked
        Button start_game_button = new Button("Start Game");
        // handler to switch scenes and start game
        start_game_button.setOnAction((e) -> {
            // put code here to switch scenes
            System.out.println("Starting game..");
        });

        // placing all elements into a BorderPane object
        BorderPane start_menu = new BorderPane(start_game_button, welcome_text, null, null, null);

        Scene scene = new Scene(new VBox(start_menu), 700,700);
        return scene;
    }

    public static Scene gamePlayScene() {
        Scene scene;

        Text gameplayText = new Text("Gameplay Screen.");

        BorderPane gameplay_scene = new BorderPane(null, gameplayText, null, null, null);

        scene = new Scene(new VBox(gameplay_scene), 700,700);
        return scene;
    }
    /*
    public static Scene winMessageScene() {

    }
    */

}

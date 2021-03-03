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
    
    // this is what I started yesterday... lmk what you think
   /*
   public class JavaFXTemplate extends Application {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to JavaFX");
		
		Button b = new Button("Play");
		
		
		Scene scene = new Scene(new VBox(b), 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Scene welcomeScene() {
		// Text object to create a title
        Text welcome_text = new Text("Welcome to Connect Four");

        // will start the game when clicked
        // can probably make this look better but that is a later
        // thing ig
        Button start = new Button("Start Game");
        
        // handler to switch scenes and start game
        // lilly note: should this be in main class...? maybe w getButton() function?
        // or do we just pass primary stage into each method and show...?
        start.setOnAction((e) -> {
            // put code here to switch scenes
            System.out.println("Starting game..");
        });

        // placing all elements into a BorderPane object
        BorderPane welcome = new BorderPane(start, welcome_text, null, null, null);
        welcome.setStyle("-fx-background-color: lightGreen");
        return new Scene(new VBox(welcome), 700,700);
        
	}
	
	
	public void gameScene() {
		// put this all in a vbox w that in center ??
		GridPane gameGrid = new GridPane();
		
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				GameButton g = new GameButton(r, c);
				gameGrid.add(g, c, r);
			}
		}
		// add menu bar
		// game play, options, and theme
		// set text to "CONNECT FOUR" or something
		// move info textfield at bottom; initialize to "no moves made"...?
		// player 1 and player 2 on right side, whichever one is playing
		// is highlighted or has arrow next to it ?? (text colors correspond to game piece colors)
		
	}
   */
}

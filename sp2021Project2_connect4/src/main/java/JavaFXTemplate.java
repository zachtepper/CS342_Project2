import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Connect Four");
		
		
		
		
		primaryStage.setScene(welcomeScene(primaryStage));
		primaryStage.show();
	}

	public Scene welcomeScene(Stage primaryStage) {
		// Text object to create a title
        Text welcome_text = new Text("Welcome to Connect Four");

        // will start the game when clicked
        // can probably make this look better but that is a later
        // thing ig
        Button start = new Button("Start Game");
        
        // handler to switch scenes and start game
        // lilly note: should this be in main class...? maybe w getButton() function?
        // or do we just pass primary stage into each method and show...?
        start.setOnAction(e -> primaryStage.setScene(gameScene(primaryStage)));
        // placing all elements into a BorderPane object
        BorderPane welcome = new BorderPane(start, welcome_text, null, null, null);
        welcome.setStyle("-fx-background-color: lightGreen");
        return new Scene(welcome, 900, 900);
        
	}
	
	
	public Scene gameScene(Stage stage) { // not actually void,, return a scene
		Game GameInstance = new Game();
		GridPane gameGrid = GameInstance.getGrid();


		Menu game = new Menu("Game Play");
		MenuItem reverse = new MenuItem("reverse move");
		game.getItems().add(reverse);
		Menu option = new Menu("Options");
		MenuItem howTo = new MenuItem("how to play");
		MenuItem newGame = new MenuItem("new game");
		MenuItem exit = new MenuItem("exit");
		exit.setOnAction(e -> stage.close());
		option.getItems().addAll(howTo, newGame, exit);
		Menu theme = new Menu("Theme");
		MenuItem ogTheme = new MenuItem("original theme");
		
		MenuItem theme1 = new MenuItem("theme one");
		MenuItem theme2 = new MenuItem("theme two");
		theme.getItems().addAll(ogTheme, theme1, theme2);
		MenuBar menu = new MenuBar(game, option, theme);

		// this will hold the entire game's move history
		TextField moveInfo = new TextField("Move List");
		VBox listVBox = new VBox(moveInfo, GameInstance.getMoveList());

		TextField title = new TextField("CONNECT FOUR");
		title.setStyle("-fx-font-size: 25;"+"-fx-border-size: 20;"+ 
				"-fx-border-color: black;");

		VBox players = GameInstance.getPlayerStatus();
		
		
		
		//newGame.setOnAction(e -> GameInstance = new Game());
		
		BorderPane gameBorderPane = new BorderPane(gameGrid, null, listVBox, players, menu);
		// resizing margins on bottom pane
		Node bottom = players;
		Insets bottomInsets = new Insets(0, 200, 100, 200);
		gameBorderPane.setBottom(bottom);
		BorderPane.setMargin(bottom, bottomInsets);

		// adding functions to each button
		reverse.setOnAction(e -> GameInstance.pop());

		howTo.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent a) {
				
				
				Text t = new Text("Take turns clicking non-grayed out spots so that your color"
						+ " has 4 spots in a row (vertically, diagonally, or "
						+ "horizontally) before the other player.");
				t.setStyle("-fx-font-size: 32;");
				t.setWrappingWidth(500);
				VBox v = new VBox(t);
				v.setPrefSize(500, 500);
				Scene howToPlay = new Scene(v, 500, 500);
				Stage htp = new Stage();
				htp.setScene(howToPlay);
				htp.show();
			}
		});
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent a) {
				Game g = new Game();
				GridPane gg = g.getGrid();
				VBox list = new VBox(moveInfo, g.getMoveList());
				VBox p = g.getPlayerStatus();
				gameBorderPane.setCenter(gg);
				gameBorderPane.setRight(list);
				gameBorderPane.setBottom(p);

				// resizing margins on bottom pane
				Node b = players;
				Insets bI = new Insets(0, 200, 100, 200);
				gameBorderPane.setBottom(b);
				BorderPane.setMargin(b, bI);
			}
		});
		ogTheme.setOnAction(e -> ogTheme(gameBorderPane, GameInstance));
		theme1.setOnAction(e -> theme1(gameBorderPane, GameInstance));
		theme2.setOnAction(e -> theme2(gameBorderPane, GameInstance));
		
		
		gameBorderPane.setStyle("-fx-background-color: seaGreen");
		
		return new Scene(gameBorderPane, 900, 900);
	}


	// theme 0
	public void ogTheme(BorderPane bp, Game g) {
		g.ogTheme();
		bp.setStyle("-fx-background-color: seaGreen");
	}

	// theme 1
	public void theme1(BorderPane bp, Game g) {
		g.theme1();
		bp.setStyle("-fx-background-color: mediumTurquoise");
	}

	// theme 2
	public void theme2(BorderPane bp, Game g) {
		g.theme2();
		bp.setStyle("-fx-background-color: silver");
	}
	
}

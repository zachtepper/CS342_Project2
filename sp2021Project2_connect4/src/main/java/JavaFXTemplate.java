import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {
	private GameButton[][] board = new GameButton[6][7];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to JavaFX");
		
		
		
		
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
        start.setOnAction(e -> primaryStage.setScene(gameScene()));
        // placing all elements into a BorderPane object
        BorderPane welcome = new BorderPane(start, welcome_text, null, null, null);
        welcome.setStyle("-fx-background-color: lightGreen");
        return new Scene(welcome, 900, 900);
        
	}
	
	
	public Scene gameScene() { // not actually void,, return a scene
		// put this all in a vbox w that in center ??
		GridPane gameGrid = new GridPane();
		gameGrid.setStyle("-fx-background-color: darkGrey");
		gameGrid.setMaxSize(700, 600);
		
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				board[r][c] = new GameButton();
				board[r][c].setStyle("-fx-background-color: silver" + "-fx-border-size: 20");
				(board[r][c]).setMaxSize(100, 100);
				(board[r][c]).setPrefSize(100, 100);
				gameGrid.add(board[r][c], c, r);
			}
		}
		
		Menu game = new Menu("Game Play");
		MenuItem reverse = new MenuItem("reverse move");
		game.getItems().add(reverse);
		Menu option = new Menu("Options");
		MenuItem howTo = new MenuItem("how to play");
		MenuItem newGame = new MenuItem("new game");
		MenuItem exit = new MenuItem("exit");
		option.getItems().addAll(howTo, newGame, exit);
		Menu theme = new Menu("Theme");
		MenuItem ogTheme = new MenuItem("original theme");
		MenuItem theme1 = new MenuItem("theme one");
		MenuItem theme2 = new MenuItem("theme two");
		theme.getItems().addAll(ogTheme, theme1, theme2);
		MenuBar menu = new MenuBar(game, option, theme);
		
		TextField moveInfo = new TextField();
		TextField title = new TextField("CONNECT FOUR");
		title.setStyle("-fx-font-size: 25;"+"-fx-border-size: 20;"+ 
				"-fx-border-color: black;");
		// highlight or something I guess...?
		TextField p1 = new TextField("Player 1");
		p1.setEditable(false);
		TextField p2 = new TextField("Player 2");
		p2.setEditable(false);
		VBox players = new VBox(p1, p2);
		
		BorderPane gameBorderPane = new BorderPane(gameGrid, null, players, moveInfo, menu);
		gameBorderPane.setStyle("-fx-background-color: darkSeaGreen");
		
		return new Scene(gameBorderPane, 900, 900);
		// set text to "CONNECT FOUR" or something
		// move info textfield at bottom; initialize to "no moves made"...?
		// player 1 and player 2 on right side, whichever one is playing
		// is highlighted or has arrow next to it ?? (text colors correspond to game piece colors)
		
	}
	
	
	public void ogTheme() {
		// color 0 = gray
		// color 1 = black
		// color 2 = white
	}
	
	public void theme1() {
		// color 0 = blue
		// color 1 = light blue
		// color 2 = dark blue
	}
	
	public void theme2() {
		// color 0 = pink
		// color 1 = green
		// color 2 = dark gray ?
	}
	
	public void winScene() {
		// win message: 0 = tie, 1 = p1, 2 = p2...?
		// play again button
		// exit program button
		
	}
	
}

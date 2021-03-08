import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Stack;

public class Game {

    // final variables
    final int NUM_ROWS = 6;
    final int NUM_COLS = 7;

    private int currentTurn; // value is either 1 or 2 to represent player 1 or 2's turn to move
    private Stack<GameButton> buttonStack;
    private ObservableList<String> moveList;
    private ListView<String> listView;
    private GridPane gameGrid;
    private GameButton[][] board;
    private TextField p1;
    private TextField p2;
    private VBox vbox;
    private int theme;
    private ArrayList<GameButton> winningButtons; // holds each button that is part of a four in a row
    private int winner = -1;

    public Game() {
        currentTurn = 1; // start with player 1's turn
        board = new GameButton[6][7];
        gameGrid = new GridPane();
        this.initGrid();
        this.initPlayerStatus();
        theme = 0;
        moveList = FXCollections.observableArrayList();
        listView = new ListView<String>(moveList);
        buttonStack = new Stack<GameButton>();
        winningButtons = new ArrayList<GameButton>();
    }

    // getters and setters
    public int getCurrentTurn() {
        return currentTurn;
    }

    public GridPane getGrid() {
        return gameGrid;
    }

    public VBox getPlayerStatus() {
        return vbox;
    }

    public int getTheme() { return theme; }

    public void setTheme(int t) { theme = t; }

    public ListView<String> getMoveList() { return listView; }

    public GameButton[][] getBoard() {
        return board;
    }

    public Stack<GameButton> getButtonStack() {
        return buttonStack;
    }
    
    // resets all information if new game started
    public void reset() {
    	board = new GameButton[6][7];
        gameGrid = new GridPane();
        this.initGrid();
        this.initPlayerStatus();
        theme = 0;
        moveList = FXCollections.observableArrayList();
        listView = new ListView<String>(moveList);
        buttonStack = new Stack<GameButton>();
        winningButtons = new ArrayList<GameButton>();
    }
    
    // returns 1,2 if players win, 0 if tie, -1 if unfinished game
    public int getWinner() {
    	return winner;
    }
    
    // theme 0
    public void ogTheme() {
    	theme = 0;
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                if (board[r][c].getColor() == 0) {
                    board[r][c].setStyle("-fx-background-color: silver");
                } else if(board[r][c].getColor() == 1) {
                    board[r][c].setStyle("-fx-background-color: yellowGreen");
                } else {
                    board[r][c].setStyle("-fx-background-color: tomato");
                }
            }     
        }
    }

    // theme 1
    public void theme1() {
    	theme = 1;
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                if (board[r][c].getColor() == 0) {
                    board[r][c].setStyle("-fx-background-color: mistyRose");
                } else if(board[r][c].getColor() == 1) {
                    board[r][c].setStyle("-fx-background-color: mediumVioletRed");
                } else {
                    board[r][c].setStyle("-fx-background-color: darkBlue");
                }
            }      
        }
    }

    // theme 2
    public void theme2() {
    	theme = 2;
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                if (board[r][c].getColor() == 0) {
                    board[r][c].setStyle("-fx-background-color: pink");
                } else if (board[r][c].getColor() == 1) {
                    board[r][c].setStyle("-fx-background-color: darkMagenta");
                } else if (board[r][c].getColor() == 2){
                    board[r][c].setStyle("-fx-background-color: darkOrange");
                }
            }      
        }
    }

    // methods to push/pop moveList
    public void push(GameButton button) {
        // push to button stack
        buttonStack.push(button);
        int p = button.getColor();
        int r = button.getRow();
        int c = button.getCol();
        // get data from GameButton and parse into string
        String data = "p" + p + ": " + "row: " + r + ", col: " + c;
        // add to the front of moveList
        moveList.add(0, data);
    }

    public void pop() {
        if (buttonStack.empty()) {
            return;
        }
        // pop from button stack
        GameButton button = buttonStack.pop();
        button.unclick();
        // remove index 0 from ObservableList
        moveList.remove(0);
    }

    // private method creates fills grid with GameButtons
    private void initGrid() {
        gameGrid.setStyle("-fx-background-color: darkGrey");
        gameGrid.setMaxSize(800, 500);
        // adding spacing between each button
        gameGrid.setPadding(new Insets(10, 10, 10, 10));
        gameGrid.setHgap(10);
        gameGrid.setVgap(10);

        // fill GridPane with GameButton instances
        // disarm() each button so they cannot be clicked
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                board[r][c] = new GameButton(r, c);
                (board[r][c]).setMaxSize(100, 100);
                (board[r][c]).setPrefSize(100, 100);
                gameGrid.add(board[r][c], c, r);
            }
        }
        // arm() only the bottom row
        for (int c = 0; c < 7; c++) {
            board[5][c].setDisable(false);
        }
    }

    private void initPlayerStatus() {
        // creating p1 and p2 TextFields
        p1 = new TextField("Player 1");
        p2 = new TextField("Player 2");
        vbox = new VBox(p1, p2);

        // style and disable text fields
        p1.setStyle("-fx-background-color: yellow");
        p1.setEditable(false);
        p2.setStyle("-fx-background-color: grey");
        p2.setEditable(false);
    }
    
    // this method is called when a GameButton is clicked
    private void clickEvent(GameButton b) {
        // scan for a win
    	this.push(b);
        if (scanForWin(currentTurn)) {
            // highlight all buttons in winningButtons
            for (GameButton e : winningButtons) {
                e.setStyle("-fx-border-color: yellow; -fx-background-color: yellow;");
            }
            System.out.println("PLAYER " + currentTurn + " WINS!");
            winner = currentTurn;
            gameGrid.setDisable(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
        	pause.setOnFinished(e-> JavaFXTemplate.stage.setScene(JavaFXTemplate.winScene(winner)));
        	pause.play();
        } else  if (moveList.size() == 42) {
        	// if there is a tie and board is full
        	// set delay
        	PauseTransition pause = new PauseTransition(Duration.seconds(3));
        	pause.setOnFinished(e-> JavaFXTemplate.stage.setScene(JavaFXTemplate.winScene(0)));
        	pause.play();
        }
        
        System.out.println("\n");
        // update currentTurn
        if (currentTurn == 1) {
            currentTurn = 2;
            // update status
            p1.setStyle("-fx-background-color: grey");
            p2.setStyle("-fx-background-color: yellow");
        } else {
            currentTurn = 1;
            // update status
            p1.setStyle("-fx-background-color: yellow");
            p2.setStyle("-fx-background-color: grey");
        }
    }
    
 // this method is called when a GameButton is unclicked
    private void unclickEvent(GameButton b) {
        System.out.println("\n");
        // update currentTurn
        if (currentTurn == 1) {
            currentTurn = 2;
            // update status
            p1.setStyle("-fx-background-color: grey");
            p2.setStyle("-fx-background-color: yellow");
        } else {
            currentTurn = 1;
            // update status
            p1.setStyle("-fx-background-color: yellow");
            p2.setStyle("-fx-background-color: grey");
        }
    }

    // will search the whole board to find four in a row
    private boolean scanForWin(int p) {
        boolean winFound = false;

        System.out.println("Scanning for win...");
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                GameButton current = board[r][c];
                int currRow = current.getRow();
                int currCol = current.getCol();

                // check if button matches with the player we are searching for
                if (current.getColor() == p) {
                    // we can start searching, starting with search up four or down four
                    if (currRow >= 3) {
                        System.out.println("SEARCH UP");
                        if (searchUp(r,c,p)) { winFound = true; }

                    } else {
                        System.out.println("SEARCH DOWN");
                        if (searchDown(r,c,p)) { winFound = true; }

                    }
                    // search left or search right
                    if (currCol >= 3) {
                        System.out.println("SEARCH LEFT");
                        if (searchLeft(r,c,p)) { winFound = true; }

                    }
                    if (currCol <= 3) {
                        System.out.println("SEARCH RIGHT");
                        if (searchRight(r,c,p)) { winFound = true; }

                    }
                    // finally check if we can search diagonally
                    if ( (currRow >= 3) && (currRow <= 5) ) {
                        if (currCol == 3) {
                            System.out.println("SEARCH UP LEFT");
                            if (searchUpLeft(r,c,p)) { winFound = true; }
                            System.out.println("SEARCH UP RIGHT");
                            if (searchUpRight(r,c,p)) { winFound = true; }

                        }
                        else if (currCol < 3) {
                            System.out.println("SEARCH UP RIGHT");
                            if (searchUpRight(r,c,p)) { winFound = true; }
                        } else {
                            System.out.println("SEARCH UP LEFT");
                            if (searchUpLeft(r,c,p)) { winFound = true; }
                        }
                    } else {
                        if (currCol == 3) {
                            System.out.println("SEARCH DOWN LEFT");
                            if (searchDownLeft(r,c,p)) { winFound = true; }
                            System.out.println("SEARCH DOWN RIGHT");
                            if (searchDownRight(r,c,p)) { winFound = true; }
                        }
                        else if (currCol < 3) {
                            System.out.println("SEARCH DOWN RIGHT");
                            if (searchDownRight(r,c,p)) { winFound = true; }
                        } else {
                            System.out.println("SEARCH DOWN LEFT");
                            if (searchDownLeft(r,c,p)) { winFound = true; }
                        }
                    }
                } else { continue; } // skip iteration if button does not match specified player
            }
        }
        
        return winFound; // return 0 means no winning move has been found
    }

    // private helper methods for scanForWin method
    private boolean searchUp(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r-i][c];
            if (curr.getColor() != p) {
                return false;
            }
        }
        // if it doesn't return false, add these buttons into winningButtons
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r-i][c];
            winningButtons.add(curr);
        }
        return true;
    }
    private boolean searchDown(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r+i][c];
            if (curr.getColor() != p) {
                return false;
            }
        }
        // if it doesn't return false, add these buttons into winningButtons
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r+i][c];
            winningButtons.add(curr);
        }
        return true;
    }
    private boolean searchLeft(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r][c-i];
            if (curr.getColor() != p) {
                return false;
            }
        }
        // if it doesn't return false, add these buttons into winningButtons
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r][c-i];
            winningButtons.add(curr);
        }
        return true;
    }
    private boolean searchRight(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r][c+i];
            if (curr.getColor() != p) {
                return false;
            }
        }
        // if it doesn't return false, add these buttons into winningButtons
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r][c+i];
            winningButtons.add(curr);
        }
        return true;
    }
    private boolean searchUpLeft(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r-i][c-i];
            if (curr.getColor() != p) {
                return false;
            }
        }
        // if it doesn't return false, add these buttons into winningButtons
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r-i][c-i];
            winningButtons.add(curr);
        }
        return true;
    }
    private boolean searchUpRight(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r-i][c+i];
            if (curr.getColor() != p) {
                return false;
            }
        }
        // if it doesn't return false, add these buttons into winningButtons
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r-i][c+i];
            winningButtons.add(curr);
        }
        return true;
    }
    private boolean searchDownLeft(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r+i][c-i];
            if (curr.getColor() != p) {
                return false;
            }
        }
        // if it doesn't return false, add these buttons into winningButtons
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r+i][c-i];
            winningButtons.add(curr);
        }
        return true;
    }
    private boolean searchDownRight(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r+i][c+i];
            if (curr.getColor() != p) {
                return false;
            }
        }
        // if it doesn't return false, add these buttons into winningButtons
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r+i][c+i];
            winningButtons.add(curr);
        }
        return true;
    }


    class GameButton extends Button {

        private boolean clicked;
        private int color;
        private int row;
        private int col;

        GameButton(int r, int c) {
            clicked = false;
            color = 0;
            row = r;
            col = c;

            this.setStyle("-fx-background-color: silver; -fx-border-size: 20; -fx-border-color: black;");

            this.setOnAction(e -> this.click());
            this.setDisable(true);
        }

        public boolean isClicked() {
            return clicked;
        }
        
        public void click() {
            clicked = true;
            this.setDisable(true);
            // change color according to which player has clicked
            color = currentTurn;
            if (color == 1) {
                if (theme == 0){
                    this.setStyle("-fx-background-color: yellowGreen; -fx-border-size: 20; -fx-border-color: black;");
                }
                else if (theme == 1) {
                    this.setStyle("-fx-background-color: mediumVioletRed; -fx-border-size: 20; -fx-border-color: black;");
                } else {
                    this.setStyle("-fx-background-color: darkMagenta; -fx-border-size: 20; -fx-border-color: black;");
                }

            }
            else if (color == 2) {
                if (theme == 0) {
                    this.setStyle("-fx-background-color: tomato; -fx-border-size: 20; -fx-border-color: black;");
                }
                else if (theme == 1) {
                    this.setStyle("-fx-background-color: darkBlue; -fx-border-size: 20; -fx-border-color: black;");
                } else {
                    this.setStyle("-fx-background-color: darkOrange; -fx-border-size: 20; -fx-border-color: black;");
                }
            }

            // enable the button above the clicked button
            if (this.row != 0) {
                board[this.row-1][this.col].setDisable(false);
            }

            clickEvent(this);
        }
        
        public void unclick() {
        	clicked = false;
        	this.setDisable(false);
        	
        	// disables button above the unclicked button
        	if (this.row != 0) {
                 board[this.row-1][this.col].setDisable(true);
            }
        	
        	if (theme == 0){
                this.setStyle("-fx-background-color: silver; -fx-border-size: 20; -fx-border-color: black;");
            }
            else if (theme == 1) {
                this.setStyle("-fx-background-color: mistyRose; -fx-border-size: 20; -fx-border-color: black;");
            } else {
                this.setStyle("-fx-background-color: pink; -fx-border-size: 20; -fx-border-color: black;");
            }
        	unclickEvent(this);
        }
        
        
        // getters and setters

        public int getColor() {
            return color;
        }

        public void setColor(int c) {
            color = c;
        }

        public int getRow() {
            return this.row;
        }

        public void setRow(int r) {
            this.row = r;
        }

        public int getCol() {
            return this.col;
        }

        public void setCol(int c) {
            this.col = c;
        }
    }

}

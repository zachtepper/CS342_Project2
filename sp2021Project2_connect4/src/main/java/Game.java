import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Stack;

public class Game {

    // final variables
    final int NUM_ROWS = 6;
    final int NUM_COLS = 7;

    private int currentTurn; // value is either 1 or 2 to represent player 1 or 2's turn to move
    private Stack<GameButton> moveList;
    private GridPane gameGrid;
    private GameButton[][] board;
    private TextField p1;
    private TextField p2;
    private VBox vbox;
    private int theme;
    private Player player1;
    private Player player2;

    public Game() {
        currentTurn = 1; // start with player 1's turn
        moveList = new Stack<GameButton>();
        board = new GameButton[6][7];
        gameGrid = new GridPane();
        this.initGrid();
        vbox = this.initPlayerStatus();
        theme = 0;
        player1 = new Player(1);
        player2 = new Player(2);
    }

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

    public void setTheme(int t) {
        theme = t;
    }

    // theme 0
    public void ogTheme() {
        // color 0 = gray
        // color 1 = green
        // color 2 = red
    }

    // theme 1
    public void theme1() {
        // color 0 = blue
        // color 1 = light blue
        // color 2 = dark blue
    }

    // theme 2
    public void theme2() {
        // color 0 = pink
        // color 1 = purple
        // color 2 = orange
    }

    // private methods

    // methods to push/pop stack
    private void push(GameButton button) {
        moveList.push(button);
        // will need to call a private method to check for a winning move

    }

    private GameButton pop() {
        return moveList.pop();
    }

    // private method creates fills grid with GameButtons
    private void initGrid() {
        gameGrid.setStyle("-fx-background-color: darkGrey");
        gameGrid.setMaxSize(800, 500);

        // fill GridPane with GameButton instances
        // disarm() each button so they cannot be clicked
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                board[r][c] = new GameButton(r, c);
                board[r][c].setText("[" + r + "][" + c + "]");
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

    private VBox initPlayerStatus() {
        // creating p1 and p2 TextFields
        p1 = new TextField("Player 1");
        p2 = new TextField("Player 2");
        VBox layout = new VBox(p1, p2);

        // style and disable text fields
        p1.setStyle("-fx-background-color: yellow");
        p1.setEditable(false);
        p2.setStyle("-fx-background-color: grey");
        p2.setEditable(false);

        return layout;
    }

    // this method is called when a GameButton is clicked
    private void clickEvent() {
        // scan for a win
        if (scanForWin(currentTurn) == currentTurn) {
            System.out.println("PLAYER " + currentTurn + " WINS!");
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

    // will search the whole board to find four in a row
    private int scanForWin(int p) {
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
                        if (searchUp(r,c,p)) { return p; }

                    } else {
                        System.out.println("SEARCH DOWN");
                        if (searchDown(r,c,p)) { return p; }

                    }
                    // search left or search right
                    if (currCol >= 3) {
                        System.out.println("SEARCH LEFT");
                        if (searchLeft(r,c,p)) { return p; }

                    }
                    if (currCol <= 3) {
                        System.out.println("SEARCH RIGHT");
                        if (searchRight(r,c,p)) { return p; }

                    }
                    // finally check if we can search diagonally
                    if ( (currRow >= 3) && (currRow <= 5) ) {
                        if (currCol == 3) {
                            System.out.println("SEARCH UP LEFT");
                            if (searchUpLeft(r,c,p)) { return p; }
                            System.out.println("SEARCH UP RIGHT");
                            if (searchUpRight(r,c,p)) { return p; }

                        }
                        else if (currCol < 3) {
                            System.out.println("SEARCH UP RIGHT");
                            if (searchUpRight(r,c,p)) { return p; }
                        } else {
                            System.out.println("SEARCH UP LEFT");
                            if (searchUpLeft(r,c,p)) { return p; }
                        }
                    } else {
                        if (currCol == 3) {
                            System.out.println("SEARCH DOWN LEFT");
                            if (searchDownLeft(r,c,p)) { return p; }
                            System.out.println("SEARCH DOWN RIGHT");
                            if (searchDownRight(r,c,p)) { return p; }
                        }
                        else if (currCol < 3) {
                            System.out.println("SEARCH DOWN RIGHT");
                            if (searchDownRight(r,c,p)) { return p; }
                            System.out.println("SEARCH DOWN LEFT");
                            if (searchDownLeft(r,c,p)) { return p; }
                        }
                    }
                } else { continue; } // skip iteration if button does not match specified player
            }
        }

        return 0; // return 0 means no winning move has been found
    }

    // private helper methods for scanForWin method
    private boolean searchUp(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r-i][c];
            if (curr.getColor() != p) {
                return false;
            }
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
        return true;
    }
    private boolean searchLeft(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r][c-i];
            if (curr.getColor() != p) {
                return false;
            }
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
        return true;
    }
    private boolean searchUpLeft(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r-i][c-i];
            if (curr.getColor() != p) {
                return false;
            }
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
        return true;
    }
    private boolean searchDownLeft(int r, int c, int p) {
        for (int i = 1; i < 4; i++) {
            GameButton curr = board[r+i][c-i];
            if (curr.getColor() != p) {
                return false;
            }
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
                    this.setStyle("-fx-background-color: green; -fx-border-size: 20; -fx-border-color: black;");
                }
                else if (theme == 1) {
                    this.setStyle("-fx-background-color: lightskyblue; -fx-border-size: 20; -fx-border-color: black;");
                } else {
                    this.setStyle("-fx-background-color: purple; -fx-border-size: 20; -fx-border-color: black;");
                }

            }
            else if (color == 2) {
                if (theme == 0) {
                    this.setStyle("-fx-background-color: red; -fx-border-size: 20; -fx-border-color: black;");
                }
                else if (theme == 1) {
                    this.setStyle("-fx-background-color: darkblue; -fx-border-size: 20; -fx-border-color: black;");
                } else {
                    this.setStyle("-fx-background-color: orange; -fx-border-size: 20; -fx-border-color: black;");
                }
            }

            // enable the button above the clicked button
            if (this.row != 0) {
                board[this.row-1][this.col].setDisable(false);
            }

            clickEvent();
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

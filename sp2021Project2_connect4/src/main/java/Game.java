import javafx.scene.layout.GridPane;

import java.util.Stack;

public class Game {

    private int currentTurn; // value is either 1 or 2 to represent player 1 or 2's turn to move
    private Stack<GameButton> moveList;
    private GridPane gameGrid;
    private GameButton[][] board;

    public Game() {
        currentTurn = 1;
        moveList = new Stack<GameButton>();
        board = new GameButton[6][7];
        gameGrid = new GridPane();
        this.initGrid();
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int t) {
        currentTurn = t;
    }

    // methods to push/pop stack
    public void push(GameButton button) {
        moveList.push(button);
        // will need to call a private method to check for a winning move

    }

    public GameButton pop() {
       return moveList.pop();
    }

    // will search the whole board to find four in a row
    private boolean scanForWin() {
        return false;
    }

    public GridPane getGrid() {
        return gameGrid;
    }

    // private method creates fills grid with GameButtons
    private void initGrid() {
        gameGrid.setStyle("-fx-background-color: darkGrey");
        gameGrid.setMaxSize(800, 500);

        // fill GridPane with GameButton instances
        // disarm() each button so they cannot be clicked
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                board[r][c] = new GameButton();
                board[r][c].setStyle("-fx-background-color: silver; -fx-border-size: 20; -fx-border-color: black;");
                board[r][c].setDisable(true);
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

}

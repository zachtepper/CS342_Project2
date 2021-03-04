import java.util.Stack;

public class Game {

    private Stack<GameButton> moveList;

    public Game() {
        moveList = new Stack<GameButton>();
    }

    // methods to push/pop stack
    public void push(GameButton button) {
        moveList.push(button);
    }

    public GameButton pop() {
       return moveList.pop();
    }

}

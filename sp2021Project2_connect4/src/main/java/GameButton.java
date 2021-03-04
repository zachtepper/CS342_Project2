import javafx.scene.control.Button;

// feel free to edit this etc. this was just outline kinda?
public class GameButton extends Button {
	
	// pos variable??
	private int row;
	private int col;
	private boolean clicked;
	
	GameButton(int row, int col) {
		this.row = row;
		this.col = col;
		clicked = false;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public void setPos(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void click() {
		clicked = true;
	}
	//
}

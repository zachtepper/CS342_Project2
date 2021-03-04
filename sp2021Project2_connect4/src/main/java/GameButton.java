import javafx.scene.control.Button;

public class GameButton extends Button {
	
	private boolean clicked;
	private int color;
	
	GameButton() {
		color = 0;
		clicked = false;
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public int getColor() {
		return color;
	}
	
	public void click() {
		clicked = true;
	}
	
	public void setColor(int c) {
		color = c;
	}
}

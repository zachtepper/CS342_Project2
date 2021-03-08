import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Stack;

class MyTest {
	Game g;
	Game.GameButton[][] b;
	Game.GameButton[][] b2;

	// fill a game board with a winning move
	@BeforeAll
	static void setUp() {}

	// initialize an instance of Game before each test case
	@BeforeEach
	void init() {
		g = new Game();
		b = g.getBoard();
	}

	@Test
	void gameConstructorTest() {
		assertEquals(1, g.getCurrentTurn());
		assertEquals(0, g.getTheme());

	}

	@Test
	void clickUnclickTest() {
		Stack<Game.GameButton> s;
		s = g.getButtonStack();
		Game.GameButton b1 = b[5][0];
		assertTrue(s.isEmpty());
		b1.click();
		assertTrue(b1.isClicked());
		assertFalse(s.isEmpty());
		g.pop();
		assertFalse(b1.isClicked());
		assertTrue(s.isEmpty());
	}

	// testing GameButtons
	@Test
	void gameButtonBoardTest() {
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				Game.GameButton curr = b[r][c];
				assertNotNull(curr, "GameButton is null");
				assertEquals(0, curr.getColor(), "incorrect color");
				assertEquals(r, curr.getRow(), "incorrect row");
				assertEquals(c, curr.getCol(), "incorrect column");
			}
		}
	}

	// make sure all buttons are disabled at the start except for bottom row
	@Test
	void isDisabledExceptLastRowTest() {
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 7; c++) {
				Game.GameButton curr = b[r][c];
				assertTrue(curr.isDisable());
				assertFalse(curr.isClicked());
			}
		}
		for (int c = 0; c < 7; c++) {
			Game.GameButton curr2 = b[5][c];
			assertFalse(curr2.isDisable());
			assertFalse(curr2.isClicked());
		}
	}

}

package gameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utils.LoadSave;

import static utils.HelpMethods.pointVsRect;

public class Menu extends State implements StateMethods {
	private MenuButton[] buttons = new MenuButton[3]; 
	private BufferedImage backgroundImg;
	private int xBgSize, yBgSize, xBgPos, yBgPos;
	
	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackGround();
	}
	
	private void loadButtons() {
		buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int)(175 * Game.SCALE), 0, GameState.GAME_ACTIVE);
		buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int)(245 * Game.SCALE), 1, GameState.OPTIONS);
		buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int)(315 * Game.SCALE), 2, GameState.QUIT);
	}
	
	private void loadBackGround() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
		xBgSize = (int)(backgroundImg.getWidth() * Game.SCALE);
		yBgSize = (int)(backgroundImg.getHeight() * Game.SCALE);
		xBgPos = Game.GAME_WIDTH / 2 - xBgSize / 2;
		yBgPos = (int)(45 * Game.SCALE);
	}
	
	@Override
	public void update() {
		for (MenuButton btn : buttons)
			btn.update();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, xBgPos, yBgPos, xBgSize, yBgSize, null);
		for (MenuButton btn : buttons)
			btn.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MenuButton btn : buttons) {
			if (pointVsRect(e.getX(), e.getY(), btn.getHitbox())) {
				btn.setMousePressed(true);
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuButton btn : buttons) {
			if (pointVsRect(e.getX(), e.getY(), btn.getHitbox()) && btn.getMousePressed()) {
				btn.applyGameState();
				break;
			}
		}
		resetButtonsBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuButton btn : buttons) {
			if (pointVsRect(e.getX(), e.getY(), btn.getHitbox()))
				btn.setMouseOver(true);
			else
				btn.setMouseOver(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void windowFocusLost() {
		
	}
	
	private void resetButtonsBools() {
		for (MenuButton btn : buttons) {
			btn.resetBools();
		}
	}
}

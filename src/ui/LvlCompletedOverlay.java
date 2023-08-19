package ui;

import static utils.HelpMethods.pointVsRect;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gameStates.GameActive;
import gameStates.GameState;
import utils.LoadSave;
import utils.Constants.UI.LvlCompletedOverlayConst;
import utils.Constants.UI.UrmButtonEnum;
import main.Game;

public class LvlCompletedOverlay implements Overlay{
	private BufferedImage background;
	private UrmButton menuButton, nextButton;
	private int xBgPos, yBgPos, xBgPosForDraw, yBgPosForDraw, xBgSize, yBgSize;
	private GameActive gameActive;
	
	public LvlCompletedOverlay(GameActive gameActive) {
		this.gameActive = gameActive;
		loadBackground();
		createUrmButtons();
	}
	
	private void loadBackground() {
		background = LoadSave.GetSpriteAtlas(LoadSave.LVL_COMPLETED_BACKGROUND_IMG);
		xBgSize = LvlCompletedOverlayConst.BACKGROUND_XSIZE;
		yBgSize = LvlCompletedOverlayConst.BACKGROUND_YSIZE;
		xBgPos = Game.GAME_WIDTH / 2;
		xBgPosForDraw = xBgPos - xBgSize / 2;
		yBgPos = LvlCompletedOverlayConst.BACKGROUND_YPOS;
		yBgPosForDraw = yBgPos - yBgSize / 2;
	}
	
	private void createUrmButtons() {
		menuButton = new UrmButton(LvlCompletedOverlayConst.MENU_BUTTON_XPOS, LvlCompletedOverlayConst.MENU_BUTTON_YPOS, LvlCompletedOverlayConst.URM_BUTTON_SIZE, LvlCompletedOverlayConst.URM_BUTTON_SIZE, UrmButtonEnum.HOME);
		nextButton = new UrmButton(LvlCompletedOverlayConst.NEXT_BUTTON_XPOS, LvlCompletedOverlayConst.NEXT_BUTTON_YPOS, LvlCompletedOverlayConst.URM_BUTTON_SIZE, LvlCompletedOverlayConst.URM_BUTTON_SIZE, UrmButtonEnum.RESUME);
	}
	
	public void update() {
		menuButton.update();
		nextButton.update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(background, xBgPosForDraw, yBgPosForDraw, xBgSize, yBgSize, null);
		menuButton.draw(g);
		nextButton.draw(g);
	}

	public void mousePressed(MouseEvent e) {
		if (pointVsRect(e.getX(), e.getY(), menuButton.getHitbox())) {
			menuButton.setMousePressed(true);
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), nextButton.getHitbox())) {
			nextButton.setMousePressed(true);
			return;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (pointVsRect(e.getX(), e.getY(), menuButton.getHitbox()) && menuButton.getMousePressed()) {
			GameState.currentState = GameState.MENU;
			menuButton.setMousePressed(false);
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), nextButton.getHitbox()) && nextButton.getMousePressed()) {
			gameActive.setLvlCompleted(false);
			gameActive.nextLvl();
			nextButton.setMousePressed(false);
			return;
		}
		resetButtonsMousePressed();

	}

	public void mouseMoved(MouseEvent e) {
		if (pointVsRect(e.getX(), e.getY(), menuButton.getHitbox()))
			menuButton.setMouseOver(true);
		else
			menuButton.setMouseOver(false);
		
		if (pointVsRect(e.getX(), e.getY(), nextButton.getHitbox()))
			nextButton.setMouseOver(true);
		else
			nextButton.setMouseOver(false);
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void resetButtonsMousePressed() {
		menuButton.setMousePressed(false);
		nextButton.setMousePressed(false);
	}
}

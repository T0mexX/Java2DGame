package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import utils.Constants.UI.PauseOverlayConst;
import utils.LoadSave;
import static utils.HelpMethods.pointVsRect;


public class PauseOverlay {
	private BufferedImage background;
	private int xBgPos, yBgPos, xBgSize, yBgSize;
	private SoundButton musicButton, sfxButton;
	
	public PauseOverlay() {
		loadBackground();
		createButtons();
	}
	
	private void loadBackground() {
		background = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND_IMG);
		xBgSize = PauseOverlayConst.BACKGROUND_XSIZE;
		yBgSize = PauseOverlayConst.BACKGROUND_YSIZE;
		xBgPos = Game.GAME_WIDTH / 2 - xBgSize / 2;
		yBgPos = PauseOverlayConst.BACKGROUND_YPOS;

	}
	
	private void createButtons() {
		musicButton = new SoundButton(PauseOverlayConst.MUSIC_BUTTON_XPOS, PauseOverlayConst.MUSIC_BUTTON_YPOS, PauseOverlayConst.SOUND_BUTTON_SIZE, PauseOverlayConst.SOUND_BUTTON_SIZE);
		sfxButton = new SoundButton(PauseOverlayConst.SFX_BUTTON_XPOS, PauseOverlayConst.SFX_BUTTON_YPOS, PauseOverlayConst.SOUND_BUTTON_SIZE, PauseOverlayConst.SOUND_BUTTON_SIZE);
	}
	
	public void update() {
		musicButton.update();
		sfxButton.update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(background, xBgPos, yBgPos, xBgSize, yBgSize, null);
		musicButton.draw(g);
		sfxButton.draw(g);
	}
	
	public void mousePressed(MouseEvent e) {
		if (pointVsRect(e.getX(), e.getY(), musicButton.getHitbox())) {
			musicButton.setMousePressed(true);
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), sfxButton.getHitbox())) {
			sfxButton.setMousePressed(true);
			return;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (pointVsRect(e.getX(), e.getY(), musicButton.getHitbox()) && musicButton.getMousePressed()) {
			musicButton.toggleMuted();
			musicButton.resetBools();
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), sfxButton.getHitbox()) && sfxButton.getMousePressed()) {
			sfxButton.toggleMuted();
			sfxButton.resetBools();
			return;
		}
		resetButtonsBools();
	}
	
	public void mouseMoved(MouseEvent e) {
		if (pointVsRect(e.getX(), e.getY(), musicButton.getHitbox()))
			musicButton.setMouseOver(true);
		else
			musicButton.setMouseOver(false);
		
		if (pointVsRect(e.getX(), e.getY(), sfxButton.getHitbox()))
			sfxButton.setMouseOver(true);
		else 
			sfxButton.setMouseOver(false);
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void resetButtonsBools() {
		musicButton.resetBools();
		sfxButton.resetBools();
	}
}

package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gameStates.GameActive;
import gameStates.GameState;
import main.Game;
import utils.Constants.UI.PauseOverlayConst;
import utils.Constants.UI.UrmButtonEnum;
import utils.LoadSave;
import static utils.HelpMethods.pointVsRect;


public class PauseOverlay {
	private BufferedImage background;
	private int xBgPos, yBgPos, xBgSize, yBgSize;
	private SoundButton musicButton, sfxButton;
	private UrmButton resumeButton, replayButton, menuButton;
	private VolumeBar volumeBar;
	private GameActive gameActive;
	
	public PauseOverlay(GameActive gameActive) {
		this.gameActive = gameActive;
		loadBackground();
		createSoundButtons();
		createUrmButtons();
		createVolumeBar();
	}
	
	private void loadBackground() {
		background = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND_IMG);
		xBgSize = PauseOverlayConst.BACKGROUND_XSIZE;
		yBgSize = PauseOverlayConst.BACKGROUND_YSIZE;
		xBgPos = Game.GAME_WIDTH / 2 - xBgSize / 2;
		yBgPos = PauseOverlayConst.BACKGROUND_YPOS;

	}
	
	private void createSoundButtons() {
		musicButton = new SoundButton(PauseOverlayConst.MUSIC_BUTTON_XPOS, PauseOverlayConst.MUSIC_BUTTON_YPOS, PauseOverlayConst.SOUND_BUTTON_SIZE, PauseOverlayConst.SOUND_BUTTON_SIZE);
		sfxButton = new SoundButton(PauseOverlayConst.SFX_BUTTON_XPOS, PauseOverlayConst.SFX_BUTTON_YPOS, PauseOverlayConst.SOUND_BUTTON_SIZE, PauseOverlayConst.SOUND_BUTTON_SIZE);
	}
	
	private void createUrmButtons() {
		resumeButton = new UrmButton(PauseOverlayConst.RESUME_BUTTON_XPOS, PauseOverlayConst.RESUME_BUTTON_YPOS, PauseOverlayConst.URM_BUTTON_SIZE, PauseOverlayConst.URM_BUTTON_SIZE, UrmButtonEnum.RESUME);
		replayButton = new UrmButton(PauseOverlayConst.REPLAY_BUTTON_XPOS, PauseOverlayConst.REPLAY_BUTTON_YPOS, PauseOverlayConst.URM_BUTTON_SIZE, PauseOverlayConst.URM_BUTTON_SIZE, UrmButtonEnum.RETURN);
		menuButton = new UrmButton(PauseOverlayConst.MENU_BUTTON_XPOS, PauseOverlayConst.MENU_BUTTON_YPOS, PauseOverlayConst.URM_BUTTON_SIZE, PauseOverlayConst.URM_BUTTON_SIZE, UrmButtonEnum.HOME);
	}
	
	private void createVolumeBar() {
		volumeBar = new VolumeBar(PauseOverlayConst.VOLUME_BAR_XPOS, PauseOverlayConst.VOLUME_BAR_YPOS, PauseOverlayConst.VOLUME_BAR_XSIZE, PauseOverlayConst.VOLUME_BAR_YSIZE, 0.5f);
	}
	
	public void update() {
		musicButton.update();
		sfxButton.update();
		
		resumeButton.update();
		replayButton.update();
		menuButton.update();
		
		volumeBar.update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(background, xBgPos, yBgPos, xBgSize, yBgSize, null);
		musicButton.draw(g);
		sfxButton.draw(g);
		
		resumeButton.draw(g);
		replayButton.draw(g);
		menuButton.draw(g);
		
		volumeBar.draw(g);
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
		if (pointVsRect(e.getX(), e.getY(), resumeButton.getHitbox())) {
			resumeButton.setMousePressed(true);
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), replayButton.getHitbox())) {
			replayButton.setMousePressed(true);
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), menuButton.getHitbox())) {
			menuButton.setMousePressed(true);
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), volumeBar.getHitbox())) {
			volumeBar.setMousePressed(true);
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
		if (pointVsRect(e.getX(), e.getY(), resumeButton.getHitbox()) && resumeButton.getMousePressed()) {
			gameActive.setPaused(false);;
			resumeButton.resetBools();
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), replayButton.getHitbox()) && replayButton.getMousePressed()) {
			System.out.println("REPLAY");
			replayButton.resetBools();
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), menuButton.getHitbox()) && menuButton.getMousePressed()) {
			GameState.currentState = GameState.MENU;
			menuButton.resetBools();
			return;
		}
		if (pointVsRect(e.getX(), e.getY(), volumeBar.getHitbox()) && volumeBar.getMousePressed()) {
			volumeBar.resetBools();
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
		
		if (pointVsRect(e.getX(), e.getY(), resumeButton.getHitbox()))
			resumeButton.setMouseOver(true);
		else 
			resumeButton.setMouseOver(false);
		
		if (pointVsRect(e.getX(), e.getY(), replayButton.getHitbox()))
			replayButton.setMouseOver(true);
		else 
			replayButton.setMouseOver(false);
		
		if (pointVsRect(e.getX(), e.getY(), menuButton.getHitbox()))
			menuButton.setMouseOver(true);
		else 
			menuButton.setMouseOver(false);
		if (pointVsRect(e.getX(), e.getY(), volumeBar.getHitbox()))
			volumeBar.setMouseOver(true);
		else 
			volumeBar.setMouseOver(false);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (volumeBar.getMousePressed())
			volumeBar.setVolumePercentage(e.getX());
	}
	
	public void resetButtonsBools() {
		musicButton.resetBools();
		sfxButton.resetBools();
		resumeButton.resetBools();
		replayButton.resetBools();
		menuButton.resetBools();
		volumeBar.resetBools();
	}
}

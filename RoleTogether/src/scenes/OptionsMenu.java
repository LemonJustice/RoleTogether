package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import display.Assets;
import main.Game;

public class OptionsMenu {

	public boolean active = true;
	private boolean transDone;
	private int timesRun = 0;
	private Color darken = new Color(0, 0, 10, 160);
	
	private Game game;
	private MainMenu mainMenu;

	public OptionsMenu(Game game, MainMenu mainMenu) {
		this.game = game;
		this.mainMenu = mainMenu;
		//transition time
	}

	public void tick() {
		if(!active)
			return;
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			active = false;
			//transition time
			mainMenu.active = true;
		}
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		g.drawImage(Assets.optionsTrans3, 0, 0, null);
		g.setColor(darken);
		g.fillRect(0, 0, 1280, 720);
		//Present scrolls as a way to customize sound and character appearance
	}
}

package entities;

import java.awt.Color;
import java.awt.Graphics;

import abstracts.OverworldEntity;
import main.Game;
import management.KeyManager;

public class Player extends OverworldEntity{
	
	float x;
	float y;
	float gridWidth = 10;
	float gridHeight = 10;
	private Color blue = new Color(0, 0, 255, 255);
	
	Game game;
	KeyManager keyManager;

	public Player(float x, float y, Game game, KeyManager keyManager) {
		super(x, y, game);
		this.x = x;
		this.y = y;
		this.game = game;
		this.keyManager = keyManager;
		
	}

	@Override
	public void update() {
		if(game.onMenu == true)
			return;
		if(keyManager.up) {
			y -= gridHeight;
		}else if(keyManager.down) {
			y += gridHeight;
		}else if(keyManager.left) {
			x -= gridWidth;
		}else if(keyManager.right) {
			x += gridWidth;
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(game.onMenu == true)
			return;
		//placeholder sprite for now
		g.setColor(blue);
		g.fillRect((int)x,(int)y, 25, 25);
		//Might be a bit ambitious, but a limited character creator would be neat,,,
		
	}

	

}

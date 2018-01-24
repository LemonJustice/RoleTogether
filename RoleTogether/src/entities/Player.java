package entities;

import java.awt.Color;
import java.awt.Graphics;

import abstracts.OverworldEntity;
import main.Game;
import management.KeyManager;
import networking.Info;

public class Player extends OverworldEntity{
	
	float x;
	float y;
	float gridWidth = 25;
	float gridHeight = 25;
	float pixPer = (float)5;
	private Color blue = new Color(0, 0, 255, 255);
	private Info playerInfo;
	private boolean movingUp;
	private boolean movingDown;
	private boolean movingLeft;
	private boolean movingRight;
	private float UpPos, DownPos, LeftPos, RightPos;
	
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
			movingUp = true;
			UpPos = y - gridHeight;
		}else if(keyManager.down) {
			movingDown = true;
			DownPos = y + gridHeight;
		}else if(keyManager.left) {
			movingLeft = true;
			LeftPos = x - gridWidth;
		}else if(keyManager.right) {
			movingRight = true;
			RightPos = x + gridWidth;
		}
		if(movingUp) {
			y-=pixPer;
			if(y < UpPos)
				y = UpPos;
				movingUp = false;
		}
		if(movingDown) {
			y+=pixPer;
			if(y > DownPos)
				y = DownPos;
			movingDown = false;
		}
		if(movingLeft) {
			x-=pixPer;
			if(x < LeftPos)
				x = LeftPos;
			movingLeft = false;
		}
		if(movingRight) {
			x+=pixPer;
			if(x > RightPos)
				x = RightPos;
			movingRight = false;
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		if(game.onMenu == true)
			return;
		//placeholder sprite for now
		g.setColor(blue);
		g.fillRect((int)x,(int)y, (int)gridHeight, (int)gridWidth);
		//Might be a bit ambitious, but a limited character creator would be neat,,,
		
	}
	
	public Info getInfo() {
		playerInfo = new Info(x, y);
		return playerInfo;
	}

	

}

package abstracts;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

//Sprites in the Overworld(Top-down perspective)
public abstract class OverworldEntity {

	protected float x, y;
	protected boolean hostile;
	protected BufferedImage sprite;
	
	public OverworldEntity(float x, float y, Game game){
		this.x = x;
		this.y = y;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void walkPattern();
	
}

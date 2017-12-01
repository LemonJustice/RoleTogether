package abstracts;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

//Sprites in the Overworld(Top-down perspective)
public abstract class OverworldEntity {

	protected float x, y;
	protected boolean hostile;  //Determines if bumping into them will cause a battle
	protected BufferedImage sprite;
	
	public OverworldEntity(float x, float y, Game game){
		this.x = x;
		this.y = y;
	}

	public abstract void update();
	
	public abstract void render(Graphics g);
	
}

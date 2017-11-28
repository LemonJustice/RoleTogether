package abstracts;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

//Enemies within Battle
public abstract class BattleEnemy {

	protected int str;
	protected int intl;
	protected int def;
	protected int res;
	protected int spd;
	protected int expGiven;
	protected int FormPos;
	protected BufferedImage sprite;
	
	public BattleEnemy(Game game) {
		
	}
	
	public abstract void render(Graphics g);
	
	public abstract void update();
	
	public abstract void AI();
	
}

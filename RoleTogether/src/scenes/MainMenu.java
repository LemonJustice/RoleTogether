package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import main.Game;

public class MainMenu {
	public boolean active = false; 
	private Game game;
	private int button = 1;// 1 = client, 2 = server
	private boolean creatingServer = false;
	private boolean connected = false;
	private Font menuFont = new Font("menuFont", Font.PLAIN, 30);
	private Color menuColor = new Color(255, 0, 0, 255);

	public MainMenu(Game game){
	this.game = game;
	active = true;
	}
	
	public void  tick(){
	if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))
		active = !active;
		if(!active)
			return;
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)||game.getKeyManager().keyJustPressed(KeyEvent.VK_S) && button < 2) {
			button --;
		}else if(game.getKeyManager().keyJustPressed(KeyEvent.VK_UP)||game.getKeyManager().keyJustPressed(KeyEvent.VK_W) && button > 1) {
			button ++;
		}
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			creatingServer = true;
			if(button == 1) {
				//Call Client

			}else if(button == 2) {
				//Call Server

			}
		active = false;
		}
	}
	
	public void render(Graphics g){
		if(!active)
			return;
		if(!creatingServer) {
			//render pre-network menu
			g.setColor(menuColor);
			g.setFont(menuFont);
			g.drawString("You're in the Main Menu. IT WORKS!", 340, 200);
		}else if(creatingServer) {
			//render network menu
			if(!connected) {
				g.setColor(menuColor);
				g.setFont(menuFont);
				g.drawString("Connecting...", 30, 30);
			}
		}
	}
	
	public boolean getActive() {
		return active;
	}
}

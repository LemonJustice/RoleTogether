package scenes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import display.Assets;
import main.Game;
import networking.Client;
import networking.Info;
import networking.Server;

public class MainMenu {
	public boolean active = false; 
	private int button = 1;// 1 = client, 2 = server, 3 = options
	private boolean creatingServer = false;
	private boolean connected = false;
	private boolean isClient;
	private boolean referenceFound = false;
	private boolean isOptions = false;
	private Graphics2D g2;
	
	private Font menuFont = new Font("menuFont", Font.PLAIN, 30);
	private Font titleFont = new Font("menuFont", Font.PLAIN, 70);
	private Color titleColor = new Color(60, 27, 0, 255);
	private Color textColor = new Color(0, 27, 55, 255);

	
	private Game game;
	private Info hostPlayer = null; 
	private Info clientPlayer = null;
	public Server server = null;
	public Client client = null;

	public MainMenu(Game game){
	this.game = game;
	active = true;
	}
	
	public void  tick(){
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))
		active = !active;
		if(!active) //It only run when Game tells it to
			return;
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)||game.getKeyManager().keyJustPressed(KeyEvent.VK_D)) { //both down and s work
			if(button < 3) //It cant go above 3
			button += 1;
		}else if(game.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)||game.getKeyManager().keyJustPressed(KeyEvent.VK_A)) { //both up and w work
			if(button > 1) //It cant go below 1
			button -= 1;
		}
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) { //maybe do another menu for ip and host
		active = false;
		}
	}
	
	public void render(Graphics g){
		if(!active)
			return;
			renBaseMain(g); 
	}
	
	public void renBaseMain(Graphics g) {
		if(button == 1) 
			g.drawImage(Assets.clientSelect, 0, 0, null);
		if(button == 2) 
			g.drawImage(Assets.serverSelect, 0, 0, null);
		if(button == 3) 
			g.drawImage(Assets.helpSelect, 0, 0, null);
	}
	
	public boolean getActive() {
		return active;
	}
	
	public int getButton() {
		return button;
	}
}

package entities;

import java.awt.Color;
import java.awt.Graphics;

import networking.Client;
import networking.Info;
import networking.Server;
import scenes.MainMenu;

public class FriendPlayer {
	private float x= 100;
	private float y = 100;
	private Server server = null;
	private Client client = null;
	private Info myInfo;
	private Info info;
	
	private Color red = new Color(255, 0 ,0, 255);
	
	public FriendPlayer( Info info, MainMenu mainMenu) {
		this.info = info;
		x = info.getX();
		y = info.getY();
		if(mainMenu.isClient()) {
			client = mainMenu.getClient();
		}else {
			server = mainMenu.getServer();
		}
	}

	public void update() {
		if(client != null)
			myInfo = client.getHost();
		if(server != null)
			myInfo = server.getClient();
		x = myInfo.getX(); // extracts values from information class
		y = myInfo.getY();
	}

	public void render(Graphics g) {
		g.setColor(red); // the other player is red for now
		g.fillRect((int)x, (int)y, 25, 25);
		System.out.println("drawn");
	}

}

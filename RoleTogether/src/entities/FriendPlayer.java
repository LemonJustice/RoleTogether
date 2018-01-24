package entities;

import java.awt.Color;
import java.awt.Graphics;

import networking.Client;
import networking.Info;
import networking.Server;
import scenes.ClientMenu;
import scenes.MainMenu;
import scenes.ServerMenu;

public class FriendPlayer {
	private float x= 100;
	private float y = 100;
	private Server server = null;
	private Client client = null;
	private Info myInfo;
	private Info info;
	int gridHeight = 25;
	int gridWidth = 25;
	
	private Color red = new Color(255, 0 ,0, 255);
	
	public FriendPlayer( Info info, ClientMenu client, ServerMenu server) {
		this.info = info;
		x = info.getX();
		y = info.getY();
		if(client != null) {
			this.client = client.getClient();
		}else {
			this.server = server.getServer();
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
		g.fillRect((int)x, (int)y, gridHeight, gridWidth);
	}

}

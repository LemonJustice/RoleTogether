package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import main.Game;
import networking.Client;
import networking.Info;
import networking.Server;

public class MainMenu {
	public boolean active = false; 
	private Game game;
	private int button = 1;// 1 = client, 2 = server
	private boolean creatingServer = false;
	private boolean connected = false;
	private Font menuFont = new Font("menuFont", Font.PLAIN, 30);
	private Color menuColor = new Color(255, 0, 0, 255);
	private Color selectClr = new Color(255, 0, 255, 255);
	private boolean isClient;
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
		if(!active)
			return;
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)||game.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
			if(button <= 1) 
			button += 1;
		}else if(game.getKeyManager().keyJustPressed(KeyEvent.VK_UP)||game.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
			if(button >= 2)
			button -= 1;
		}
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			creatingServer = true;
			if(button == 1) {
				client = new Client(game);
				isClient = true;
				while(!connected) {
					connected = client.isConnected();
				}
				hostPlayer = client.getHost();
			}else if(button == 2) {
				server = new Server(game);
				isClient = false;
				while(!connected) {
					connected = server.isConnected();
				}
				clientPlayer = server.getClient();
				}
		if(creatingServer) {
			while(clientPlayer == null && hostPlayer == null) {
				System.out.println("Still null!!");
				if(button == 1)
					hostPlayer = client.getHost();
				if(button == 2)
					clientPlayer = server.getClient();		
			}
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
			g.drawString("You're in the Main Menu. IT WORKS!", 340, 100);
			g.drawString("Client", 500, 270);
			g.drawString("Server", 500, 340);
			g.setColor(selectClr);
			if(button == 1) 
				g.fillRect(470, 255, 10, 10);
			if(button == 2) 
				g.fillRect(470, 325, 10, 10);
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
	
	public Info otherPlayer() {
		if(hostPlayer == null && clientPlayer == null) {
			System.out.println("They are both null!!");
			return null;
		}
		if(isClient) {
			return client.getHost();
		}else{
			return server.getClient();
		}
	
	}
	public Client getClient(){
		return client;
	}
	public Server getServer() {
		return server;
	}
	public boolean isClient() {
		return isClient;
	}
}

package scenes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import main.Game;
import networking.Client;
import networking.Info;
import networking.Server;

public class MainMenu {
	public boolean active = false; 
	private int button = 1;// 1 = client, 2 = server
	private boolean creatingServer = false;
	private boolean connected = false;
	private boolean isClient;
	private boolean referenceFound = false;
	private Graphics2D g2;
	
	private Font menuFont = new Font("menuFont", Font.PLAIN, 30);
	private Font titleFont = new Font("menuFont", Font.PLAIN, 50);
	private Color textColor = new Color(255, 255, 255, 255);
	private Color selectClr = new Color(255, 255, 255, 255);
	private Color boxColor = new Color(0, 0, 40, 255);
	
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
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)||game.getKeyManager().keyJustPressed(KeyEvent.VK_S)) { //both down and s work
			if(button <= 1) //It cant go below 1
			button += 1;
		}else if(game.getKeyManager().keyJustPressed(KeyEvent.VK_UP)||game.getKeyManager().keyJustPressed(KeyEvent.VK_W)) { //both up and w work
			if(button >= 2) //It cant go above 2
			button -= 1;
		}
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			creatingServer = true;
			if(button == 1) { //client option
				client = new Client(game);
				isClient = true;
				while(!connected) {
					connected = client.isConnected();
				}
				hostPlayer = client.getHost();
			}else if(button == 2) { //server option
				server = new Server(game);
				isClient = false;
				while(!connected) {
					connected = server.isConnected();
				}
				clientPlayer = server.getClient();
				}
		if(creatingServer) {
			while(clientPlayer == null && hostPlayer == null) { //We need the reference for later
				System.out.println("connecting");
				if(button == 1)
					hostPlayer = client.getHost();
				if(button == 2)
					clientPlayer = server.getClient();		
			}
			referenceFound = true;
		}
		active = false;
		}
	}
	
	public void render(Graphics g){
		if(!active)
			return;
		if(!creatingServer) {
			//render pre-network menu
			g2 = (Graphics2D)g;
			g.setColor(boxColor);
			g.setFont(titleFont);
			g.fillRect(0, 550, 450, 200); //box with options within it
			g.setColor(textColor);
			g2.setStroke(new BasicStroke(5));
			g2.drawRect(0, 550, 450, 200);//Border to the previous box
			g.drawString("RoleTogether", 50, 600);
			g.setFont(menuFont);
			g.drawString("Client", 80, 650);
			g.drawString("Server", 80, 690);
			g.setColor(selectClr);
			if(button == 1) 
				g.fillRect(40, 635, 10, 10);
			if(button == 2) 
				g.fillRect(40, 675, 10, 10);
		}else if(creatingServer) {
			//render network menu
			if(!referenceFound) {
				g.setColor(textColor);
				g.setFont(menuFont);
				g.drawString("Connecting...", 30, 30);
			}
		}
	}
	
	public boolean getActive() {
		return active;
	}
	
	public Info otherPlayer() {
		if(hostPlayer == null && clientPlayer == null)
			return null;
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

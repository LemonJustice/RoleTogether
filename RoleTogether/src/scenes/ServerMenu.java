package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import display.Assets;
import main.Game;
import networking.Client;
import networking.Info;
import networking.Server;

public class ServerMenu {

	public boolean active;
	public int button;// 1 = Port , 2 = Create
	public static boolean connected = false;
	
	private Game game;
	private MainMenu mainMenu;
	public Server server;
	public Info clientPlayer = null;

	public ServerMenu(Game game, MainMenu mainMenu) {
		this.game = game;
		this.mainMenu = mainMenu;
		button = 1;
	}
	
	public void tick() {
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			active = false;
			//transition time
			mainMenu.active = true;
		}
		
		if(!active)
			return;
		
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)||game.getKeyManager().keyJustPressed(KeyEvent.VK_S)) { //both down and s work
			if(button < 2) //It cant go above 2
			button += 1;
		}else if(game.getKeyManager().keyJustPressed(KeyEvent.VK_UP)||game.getKeyManager().keyJustPressed(KeyEvent.VK_W)) { //both up and w work
			if(button > 1) //It cant go below 1
			button -= 1;
		}
		if(button == 2) {
			if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
				active = false;
				server = new Server(game, 1000);
				clientPlayer = server.getClient();
				while(clientPlayer == null) {
					clientPlayer = server.getClient();
				}
				connected = true;
			}
		}
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		if(button == 1) 
			g.drawImage(Assets.serverMenuPort, 0, 0, null);
		if (button == 2) 
			g.drawImage(Assets.serverMenuCreate, 0, 0, null);
	}  
	public Info getClient() {
		return clientPlayer;
	}
	public Server getServer() {
		return server;
	}
	public static boolean getConnect() {
		return connected;
	}
}

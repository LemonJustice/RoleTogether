package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import display.Assets;
import main.Game;
import networking.Client;
import networking.Info;

public class ClientMenu {

	public boolean active;
	private int button;// 1 = IP, 2 = Port, 3 = Search
	public static boolean connected = false;
	
	private Game game;
	private MainMenu mainMenu;
	public Client client;
	public Info hostPlayer = null;

	public ClientMenu(Game game, MainMenu mainMenu) {
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
			if(button < 3) //It cant go above 3
			button += 1;
		}else if(game.getKeyManager().keyJustPressed(KeyEvent.VK_UP)||game.getKeyManager().keyJustPressed(KeyEvent.VK_W)) { //both up and w work
			if(button > 1) //It cant go below 1
			button -= 1;
		}
		if(button == 3) {
			if(game.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
				active = false;
				client = new Client(game, "127.0.0.1", 1000);
					hostPlayer = client.getHost();
					while(hostPlayer == null) {
						hostPlayer = client.getHost();
						System.out.println("difficulties");
					}
					connected = true;
					active = false;
			}
		}
	}
	
	
	public void render(Graphics g) {
		if(active == false)
			return;
		if(button == 1) 
			g.drawImage(Assets.clientMenuIP, 0, 0, null);
		if (button == 2) 
			g.drawImage(Assets.clientMenuPort, 0, 0, null);
		if(button == 3) 
			g.drawImage(Assets.clientMenuSearch, 0, 0, null);
	}
	public Info getHost() {
		return hostPlayer;
	}
	public Client getClient() {
		return client;
	}
	public static boolean getConnect() {
		return connected;
	}
}

package main;

//Classes I made
import display.Display;
import entities.FriendPlayer;
import entities.Player;
import management.KeyManager;
import networking.Client;
import networking.Info;
import networking.Server;
import scenes.ClientMenu;
import scenes.MainMenu;
import scenes.OptionsMenu;
import scenes.ServerMenu;
import display.Assets;

//Actual java classes
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
	
	//Regular Class variables
	int width;
	int height;
	String title;
	private Graphics g;
	private BufferStrategy buffer;
	public Boolean running = false;
	Thread thread;
	public int menuButton = 0;
	public boolean onMenu = false;
	private Info playerInfo;
	boolean firstTry = true;

	
	//Class references
	Display display;
	Assets assets;
	KeyManager keyManager;
	Player player;
	MainMenu mainMenu;
	FriendPlayer friendPlayer = null;
	Client client = null;
	Server server = null;
	OptionsMenu optionsMenu = null;
	ClientMenu clientMenu = null;
	ServerMenu serverMenu = null;
	

	//Constructor
	public Game(String title2, int width2, int height2) {
		this.width = width2;
		this.height = height2;
		this.title = title2;
	}

	//Run once to create instances of needed classes
	private void init() {
		assets = new Assets();
		assets.init();
		keyManager = new KeyManager();
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		player = new Player(100,100, this, keyManager);
		mainMenu = new MainMenu(this);
		onMenu = true;
	
		
	}

	//Keeps variable up to date.  Calls other classes' update functions
	private void update() {
		//Call update functions here
		keyManager.tick();
		player.update();
		mainMenu.tick();
		menuButton = mainMenu.getButton();
		if(!mainMenu.getActive()) {
			if(menuButton == 1) {//Client
		 			if(clientMenu == null) {
		 					clientMenu = new ClientMenu(this, mainMenu);
		 				}
		 				if(!clientMenu.getConnect()) {
		 					clientMenu.active = true;
		 				}
		 			}
		 			 if(menuButton == 2) {//Server
		 				if(serverMenu == null) {
		 					serverMenu = new ServerMenu(this, mainMenu);
		 				}
		 				if(!serverMenu.getConnect()) {
		 					serverMenu.active = true;
		 				}
		 			 }
		 			 if(menuButton == 3) {//Options
		 				if(optionsMenu == null) {
		 					optionsMenu = new OptionsMenu(this, mainMenu);
		 				}
		 				if(!serverMenu.getConnect() && !clientMenu.getConnect()) {
		 					optionsMenu.active = true;
		 				}
		 			 }
		if(clientMenu != null) {
			clientMenu.tick();
			if(clientMenu.connected) {
				client = clientMenu.getClient();
				if(friendPlayer == null) {
					friendPlayer = new FriendPlayer(client.getHost(),clientMenu, null);
					onMenu = false;
					}
				}
			}
		if(serverMenu != null) {
			serverMenu.tick();
			if(serverMenu.connected) {
				server = serverMenu.getServer();
				if(friendPlayer == null) {
					friendPlayer = new FriendPlayer(server.getClient(), null, serverMenu);
					onMenu = false;
					}
				}
			}
		if(optionsMenu != null)
			optionsMenu.tick();
		}
	
		
		if(friendPlayer != null) 
			friendPlayer.update();
		
		 //Maybe a pause menu where the onMenu makes more sense
		
		
		//Camera functions will be needed soon
		//Calling update stops here
		
	}
	
	//Draws the stuff on screen.  Calls other classes' render functions
	private void render() {
		buffer = display.getCanvas().getBufferStrategy();
		if(buffer == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//Call render functions here
		player.render(g);
		mainMenu.render(g);
		if(onMenu) {
		if(clientMenu != null)
			clientMenu.render(g);
		if(serverMenu != null)
			serverMenu.render(g);
		if(optionsMenu != null)
			optionsMenu.render(g);
		}
		
		if(friendPlayer != null) {
			friendPlayer.render(g);
		}
		
		
		//Calling render stops here
		buffer.show();
		g.dispose();
	}
	
	//The Game loop that unifies framerate and calls update, render, and init
	@Override
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				delta--;
			}
		}
		stop();
		
	}

	// allows for run() to be ran constantly and independently from other code 
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	//Should rest at the bottom of the class. Makes sure that the program stops runnig when needed
	public synchronized void stop(){
		if(!running)
			return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public Info getPlayerInfo() {
		playerInfo = player.getInfo();
		return playerInfo;
	}
	
	
}

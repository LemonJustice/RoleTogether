package main;

//Classes I made
import display.Display;
import entities.FriendPlayer;
import entities.Player;
import management.KeyManager;
import networking.Client;
import networking.Info;
import networking.Server;
import scenes.MainMenu;
import display.Assets;

//Actual java classes
import java.awt.*;
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
	public boolean onMenu = false;
	private Info playerInfo;

	
	//Class references
	Display display;
	Assets assets;
	KeyManager keyManager;
	Player player;
	MainMenu mainMenu;
	FriendPlayer friendPlayer = null;
	Client client = null;
	Server server = null;
	

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
		onMenu = mainMenu.getActive();
		if(mainMenu.getClient() != null) {
			client = mainMenu.getClient();
			System.out.println("Client:" + mainMenu.otherPlayer().XtoString() + " " +mainMenu.otherPlayer().YtoString());
			if(friendPlayer == null)
				friendPlayer = new FriendPlayer(mainMenu.otherPlayer(), mainMenu);
		}
		if(mainMenu.getServer() != null) {
			server = mainMenu.getServer();
			System.out.println("Server:" + mainMenu.otherPlayer().XtoString() + " " + mainMenu.otherPlayer().YtoString());
			if(friendPlayer == null)
				friendPlayer = new FriendPlayer(mainMenu.otherPlayer(), mainMenu);
		}
		if(friendPlayer != null) {
			friendPlayer.update();
		}
		
		
		//Camera functions will be needed soon
		//Calling update stops here
	}
	
	//Draws the stuff on screen.  Calls other classes' update functions
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

	//Makes sure that it is running and whatnot
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

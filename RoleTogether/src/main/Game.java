package main;

//Classes I made
import display.Display;
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
	Boolean running = false;
	Thread thread;
	
	//Class references
	Display display;
	Assets assets;
	
	//Constructor
	public Game(String title2, int width2, int height2) {
		this.width = width2;
		this.height = height2;
		this.title = title2;
	}

	//Run once to create instances of needed classes
	private void init() {
		display = new Display(title, width, height);
		assets.init();
	}

	//Keeps variable up to date.  Calls other classes' update functions
	private void update() {
		//Call update functions here
		
		
		
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
		//Call render functions here
		
		
		
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
	
	//Should rest at the very bottom of the class. Makes sure that the program stops runnig when needed
	public synchronized void stop(){
		if(!running)
			return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}

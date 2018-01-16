package networking;

import java.io.*;
import java.net.*;

import main.Game;

public class Client implements Runnable {
	private boolean running = false;
	private Thread thread;
	private ObjectOutputStream OS;
	private ObjectInputStream IS;
	private OutputStream out;
	private InputStream in;
	public Info hostPlayer;
	private Info clientPlayer;
	private Socket client;
	private Game game;
	private boolean isConnected;
	

	
	public Client(Game game) {
		this.game = game;
		clientPlayer = game.getPlayerInfo();
		try {
			client = new Socket("localHost", 1000); 
			isConnected = true;
			out = client.getOutputStream();
			OS = new ObjectOutputStream(out); // stream that contains information sent by server 
			in = client.getInputStream();
			IS = new ObjectInputStream(in); // stream that transmits data to server
			start();
		} catch (IOException e) {
			e.printStackTrace();
			stop();
		}
	}

	@Override
	public void run() {
		while(game.running) {
		try {
			clientPlayer = game.getPlayerInfo(); // updates local player before sending it
			//IS and OS swap positions in Server and Client because the class waits for it to be read before moving on
			hostPlayer =(Info)IS.readObject(); // deserializes the host player's information
			OS.writeObject(clientPlayer); // sends local player information
			OS.flush(); // clears the stream for better optimization
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			stop();
		}
		}
		
	}
	
	// allows for run() to be ran constantly and independently from other code 
	public void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start(); // actually executes run() method
	}
	
	public void stop() {
		if(!running)
			return;
		try {
			client.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public Info getHost() {
		return hostPlayer;
	}
}

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
	private boolean first = true;
	


	public Client(Game game, String IP, int port) {
		this.game = game;
		clientPlayer = game.getPlayerInfo();
			start();
	}

	@Override
	public void run() {
		while(game.running) {
		try {
			if(first) {
				first = false;
				client = new Socket("127.0.0.1", 1000); 
				isConnected = true;
				out = client.getOutputStream();
				OS = new ObjectOutputStream(out); // stream that contains information sent by server 
				in = client.getInputStream();
				IS = new ObjectInputStream(in); // stream that transmits data to server
			}
			clientPlayer = game.getPlayerInfo(); // updates local player before sending it
			//IS and OS swap positions in Server and Client because the class waits for it to be read before moving on
			hostPlayer =(Info)IS.readObject(); // deserializes the host player's information
			System.out.println("Received");
			OS.writeObject(clientPlayer); // sends local player information
			System.out.println("Send");
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

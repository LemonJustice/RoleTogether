package networking;
import java.io.*;
import java.net.*;

import main.Game;

public class Server implements Runnable{
	private boolean running = false;
	private Thread thread;
	private ObjectOutputStream OS;
	private ObjectInputStream IS;
	private ServerSocket serverSocket;
	public Info clientPlayer;
	private Info hostPlayer;
	private Game game;
	private boolean isConnected = false;
	
	public Server(Game game) {
		this.game = game;
		hostPlayer = game.getPlayerInfo();
		try {
			serverSocket = new ServerSocket(1000); //makes new server on port 1000
			Socket server = serverSocket.accept(); //accepts the clients request to connect
			isConnected = true;
			IS = new ObjectInputStream(server.getInputStream()); //Stream that contains information sent from client
			OS = new ObjectOutputStream(server.getOutputStream()); //Stream that transmits data to client
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
			hostPlayer = game.getPlayerInfo(); // updates local player info before sending it
			//IS and OS swap positions in Server and Client because the class waits for it to be read before moving on
			OS.writeObject(hostPlayer); //sends local player information
			clientPlayer = (Info)IS.readObject(); // deserializes the clients information class
			OS.flush(); // empties the stream so it can run better
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
		thread.start(); // actually calls the run() method
	}
	
	public void stop() {
		if(!running)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public Info getClient() {
		return clientPlayer;
	}
}

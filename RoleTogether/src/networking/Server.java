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
			serverSocket = new ServerSocket(1000);
			Socket server = serverSocket.accept();
			isConnected = true;
			IS = new ObjectInputStream(server.getInputStream());
			OS = new ObjectOutputStream(server.getOutputStream());
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
			hostPlayer = game.getPlayerInfo();
			Serialize.serialization("ServerInfo.ser", hostPlayer);
			OS.writeObject(hostPlayer);
			clientPlayer = (Info)IS.readObject();
			Serialize.deserialize("ClientInfo.ser");
			OS.flush();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			stop();
		}
		}
	}
	
	public void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
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

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
			OS = new ObjectOutputStream(out);
			in = client.getInputStream();
			IS = new ObjectInputStream(in);
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
			clientPlayer = game.getPlayerInfo();
			hostPlayer =(Info)IS.readObject();
			Serialize.deserialize("ServerInfo.ser");
			Serialize.serialization("ClientInfo.ser",clientPlayer);
			OS.writeObject(clientPlayer);
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

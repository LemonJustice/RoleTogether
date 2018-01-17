package display;

import java.awt.image.BufferedImage;

import display.ImageLoader;

public class Assets {
	
	//Add images to the "res" folder and initiate a variable on the line below as a BufferedImage
	
	public static BufferedImage player, mainBackground, clientSelect, serverSelect, helpSelect, optionsTrans3;
	public static BufferedImage clientMenuIP, clientMenuPort, clientMenuSearch, serverMenuIP, serverMenuPort, serverMenuCreate;
	
	public void init() {
		
		// exampleImage = ImageLoader.loadImage("/textures/exampleImage.png");
		
		//player = ImageLoader.loadImage("/textues/player.png");
		clientSelect = ImageLoader.loadImage("/textures/BackgroundClient.png");
		serverSelect = ImageLoader.loadImage("/textures/BackgroundServer.png");
		helpSelect = ImageLoader.loadImage("/textures/BackgroundHelp.png");
		optionsTrans3 = ImageLoader.loadImage("/textures/OptionTrans3.png");
		clientMenuIP = ImageLoader.loadImage("/textures/ClientTrans3IP.png");
		clientMenuPort = ImageLoader.loadImage("/textures/ClientTrans3Port.png");
		clientMenuSearch = ImageLoader.loadImage("/textures/ClientTrans3Search.png");
		serverMenuPort = ImageLoader.loadImage("/textures/ServerTrans3Port.png");
		serverMenuCreate = ImageLoader.loadImage("/textures/ServerTrans3Create.png");
	}
}

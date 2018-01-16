package display;

import java.awt.image.BufferedImage;

import display.ImageLoader;

public class Assets {
	
	//Add images to the "res" folder and initiate a variable on the line below as a BufferedImage
	
	public static BufferedImage player, mainBackground, clientSelect, serverSelect, helpSelect;
	
	public void init() {
		
		// exampleImage = ImageLoader.loadImage("/textures/exampleImage.png");
		
		//player = ImageLoader.loadImage("/textues/player.png");
		mainBackground = ImageLoader.loadImage("/textures/Background.png");
		clientSelect = ImageLoader.loadImage("/textures/BackgroundClient.png");
		serverSelect = ImageLoader.loadImage("/textures/BackgroundServer.png");
		helpSelect = ImageLoader.loadImage("/textures/BackgroundHelp.png");
	}
}

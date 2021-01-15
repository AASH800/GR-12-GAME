package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mightyMoth.handlers.ObjectHandler;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.supers.Animation;
import mightyMoth.supers.GameObject;

public class Coin extends GameObject{
	
	private Animation animation;
	
	public Coin(int x, int y, int width, int height, String color) {
		super(x, y, width, height);
		
		this.velX = 3;
		
		BufferedImage[] images = new BufferedImage[3];
		
		for (int i = 0; i < images.length; i++) {
			images[i] = GraphicsLoader.loadGraphics(color + i + ".png");
		}
		
		animation = new Animation(this, 150, true, images);
		animation.start();
		
		//ObjectHandler.addObject(this);
	}

	@Override
	public void tick() {
		x -= velX;
		animation.tick();
		
		//if(x + width < 0) {
			//ObjectHandler.removeObject(this);
		//}
	}

	@Override
	public void render(Graphics g) {
		animation.render(g);
		
	}

}

package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mightyMoth.handlers.LampHandler;
import mightyMoth.handlers.ObjectHandler;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.supers.Animation;
import mightyMoth.supers.GameObject;

public class GreenCoin extends GameObject{
	
	private Animation animation;
	public static int now;
	
	public GreenCoin(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.velX = 3;
		
		BufferedImage[] images = new BufferedImage[3];
		
		for (int i = 0; i < images.length; i++) {
			images[i] = GraphicsLoader.loadGraphics("blue" + i + ".png");
		}
		
		animation = new Animation(this, 150, true, images);
		animation.start();
	}

	@Override
	public void tick() {
		x -= velX;
		animation.tick();
		
		if(x + width + 250 < 0) {
			ObjectHandler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		animation.render(g);	
	}
	
	public static void spawnCoin() {
		if(now == 2) {
			GreenCoin blueCoin = new GreenCoin(250, LampHandler.heightTop/2 + 15, 78, 100);
			ObjectHandler.addObject(blueCoin);
			now ++;
			
		} else {
			now ++;
		}
	}
}

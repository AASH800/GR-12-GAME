package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import mightyMoth.handlers.LampHandler;
import mightyMoth.handlers.ObjectHandler;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.supers.Animation;
import mightyMoth.supers.GameObject;

public class GreenCoin extends GameObject{
	
	private Animation animation;
	private static Random random = new Random();
	
	public static int num;
	public static int meep;
	
	public GreenCoin(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.velX = 3;
		
		BufferedImage[] images = new BufferedImage[3];
		
		for (int i = 0; i < images.length; i++) {
			images[i] = GraphicsLoader.loadGraphics("green" + i + ".png");
		}
		
		animation = new Animation(this, 155, true, images);
		animation.start();
	}

	@Override
	public void tick() {
		x -= velX;
		animation.tick();
		
		if(x + width < 0) {
			ObjectHandler.removeObject(this);
			
			if (Moth.greenGrav) {
				Moth.n *= -1;
				GreenCoin greenCoin = new GreenCoin(525, LampHandler.heightTop + LampHandler.spacing/2, 25, 35);
				ObjectHandler.addObject(greenCoin); 
				Moth.greenGrav = false;
			}
		
		}
	}

	@Override
	public void render(Graphics g) {
		//g.drawRect(x, y, width, height);
		animation.render(g);
	}
	
	public static void spawnCoin() {
		meep = random.nextInt();
		
		if(meep % 10 == 0) {
			GreenCoin greenCoin = new GreenCoin(525, LampHandler.heightTop + LampHandler.spacing/2, 25, 35);
			ObjectHandler.addObject(greenCoin);
		} 
	}
}

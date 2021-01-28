package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import mightyMoth.handlers.ObjectHandler;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.main.Game;
import mightyMoth.supers.Animation;
import mightyMoth.supers.GameObject;

public class BlueCoin extends GameObject{
	
	private Animation animation;
	private static Random random = new Random();
	
	public static int groundSize = 180;
	public static int area = Game.HEIGHT - groundSize;
	public static int num;
	
	public BlueCoin(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.velX = 3;
		
		BufferedImage[] images = new BufferedImage[3];
		
		for (int i = 0; i < images.length; i++) {
			images[i] = GraphicsLoader.loadGraphics("blue" + i + ".png");
		}
		
		animation = new Animation(this, 155, true, images);
		animation.start();
	}

	@Override
	public void tick() {
		x -= velX;
		animation.tick();
		
		if(x + width  < 0) {
			ObjectHandler.removeObject(this);
			 if(Moth.blueFive) {
				 Game.score += 5;
				 Moth.blueFive = false;
			 }
		}
	}

	@Override
	public void render(Graphics g) {
		//g.drawRect(x, y, width, height);
		animation.render(g);
	}
	
	public static void spawnCoin() {
		num = random.nextInt(580 - 80) + 80;
		
		if(num%10 == 0) {
			BlueCoin blueCoin = new BlueCoin(705, num, 25, 35);
			ObjectHandler.addObject(blueCoin);
		} 
	}
}
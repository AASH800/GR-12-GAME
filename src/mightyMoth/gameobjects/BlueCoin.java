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
	//private BufferedImage blueCoin;
	
	public static int groundSize = 180;
	public static int area = Game.HEIGHT - groundSize;
	public static int minSize = 200;
	public static int maxSize = area - minSize;
	public static int num;
	
	public BlueCoin(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.velX = 3;
		
		BufferedImage[] images = new BufferedImage[3];
		
		for (int i = 0; i < images.length; i++) {
			images[i] = GraphicsLoader.loadGraphics("blue" + i + ".png");
		}
		
		animation = new Animation(this, 150, true, images);
		animation.displaceX = -25;
		animation.displaceY = -25;
		animation.start();
	}

	@Override
	public void tick() {
		x -= velX;
		animation.tick();
		
		if(x + width + 350  < 0 && Lamp.n == 1) {
			ObjectHandler.removeObject(this);
			 if(Moth.blueFive) {
				 Game.score += 5;
				 Moth.blueFive = false;
			 }
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawRect(x, y, width, height);
		//g.fillRect(x, y, width, height);
		animation.render(g);
	}
	
	public static void spawnCoin() {
		num = random.nextInt(350 - 40) + 40;
		
		//if(num%2 == 0) {
			BlueCoin blueCoin = new BlueCoin(250, num/2, 50, 50);
			ObjectHandler.addObject(blueCoin);
		//} 
	}
}
package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mightyMoth.enums.LampType;
import mightyMoth.handlers.LampHandler;
import mightyMoth.handlers.ObjectHandler;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.main.Game;
import mightyMoth.supers.Animation;
import mightyMoth.supers.GameObject;

public class Coin extends GameObject{
	
	private Animation animation;
	public static int now;
	public static String color;
	
	public Coin(int x, int y, int width, int height, String color) {
		super(x, y, width, height);
		
		this.velX = 3;
		this.color = color;
		
		BufferedImage[] images = new BufferedImage[3];
		
		for (int i = 0; i < images.length; i++) {
			images[i] = GraphicsLoader.loadGraphics(color + i + ".png");
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
			
			if(color == "blue") {
				Game.score += 5;
			}
			if(color == "green") {
				Game.score += 5;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		animation.render(g);	
	}
	
	public static void spawnCoin() {
		if(now == 5) {
			Coin blueCoin = new Coin(250, LampHandler.heightTop/2 + 15, 78, 100, "blue");
			ObjectHandler.addObject(blueCoin);
			now ++;
			
		} else if (now == 10){
			Coin greenCoin = new Coin(250, LampHandler.heightTop/2 + 15, 78, 100, "green");
			ObjectHandler.addObject(greenCoin);
			now = 0;
			
		} else {
			now ++;
		}
	}
}

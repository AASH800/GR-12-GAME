package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.main.Game;
import mightyMoth.supers.*;
import mightyMoth.handlers.*;

public class Moth extends GameObject{
	
	Animation animation;
	
	public float gravity;
	public float maxSpeed;
	
	public Moth(int x, int y, int width, int height) {
		super(x, y, width, height);

		gravity = 0.3f;
		maxSpeed = 12f;
		
		BufferedImage[] images = new BufferedImage[3];
		
		for (int i = 0; i < images.length; i++) {
			images[i] = GraphicsLoader.loadGraphics("moth" + i + ".png");
		}
		
		animation = new Animation(this, 150, true, images);
		animation.start();
		
		ObjectHandler.addObject(this);
	}

	@Override
	public void tick() {
		velY += gravity;
		y += velY;
		
		if(velY >= maxSpeed) {
			velY = maxSpeed;
		}
		
		if(y + height > Game.HEIGHT - 170) {
			y = Game.HEIGHT - 170 - height;
			setVelY(0);
		}
		
		if(y < 0) {
			y = 0;
			setVelY(0);
		}
		
		GameObject temp = null;
		
		for(int i = 0; i < ObjectHandler.list.size(); i++) {
			temp = ObjectHandler.list.get(i);
			
			if(temp instanceof Lamp) {
				if (this.getBounds().intersects(temp.getBounds()) ) {
					Game.gameover = true;
				}
			}
		}
		
		animation.tick();
	}

	@Override
	public void render(Graphics g) {
		animation.render(g);
	}
}

package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.main.Game;
import mightyMoth.supers.*;
import mightyMoth.handlers.*;

public class Moth extends GameObject{
	
	private Animation animation;
	
	public float gravity;
	public float maxSpeed;
	public float n;
	public Rectangle rect;
	
	public Moth(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		n = 1;
		gravity = n * 0.3f;
		maxSpeed = n * 12f;
		
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
		
		if(Game.moth.gravity == -0.3f) {
			if(velY <= maxSpeed) {
				velY = maxSpeed;
			}
		}else {
			if(velY >= maxSpeed) {
				velY = maxSpeed;
			}
		}
		
		if(y + height > Game.HEIGHT - 190) {
			y = Game.HEIGHT - 190 - height;
			setVelY(0);
		}
		
		if(y < 0) {
			y = 0;
			setVelY(0);
		}
		
		GameObject temp = null;
		rect = new Rectangle(this.x + 40, this.y + 50, this.width - 5, this.height - 3);
		
		for(int i = 0; i < ObjectHandler.list.size(); i++) {
			temp = ObjectHandler.list.get(i);
			
			if(temp instanceof Lamp) {
				if (rect.getBounds().intersects(temp.getBounds())) {
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

package mightyMoth.gameobjects;

import java.awt.Color;
import java.awt.Font;
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
	public static float n = 1;
	public static boolean blueFive = false;
	public static boolean greenGrav = false;
	public Rectangle rect;
	
	public Moth(int x, int y, int width, int height) {
		super(x, y, width, height);
		
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
		
		gravity = n * 0.3f;
		maxSpeed = n * 12f;
		
		if(Game.moth.gravity < 0) {
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
		
		for(int i = 0; i < ObjectHandler.list.size(); i++) {
			temp = ObjectHandler.list.get(i);
			
			if(temp instanceof Lamp) {
				if (this.getBounds().intersects(temp.getBounds())) {
					n = 1;
					greenGrav = false;
					Game.gameover = true;
				}
			}
			
			if(temp instanceof GreenCoin) {
				if (this.getBounds().intersects(temp.getBounds())) {
					greenGrav = true;
				}
			}
			
			if(temp instanceof BlueCoin) {
				if (this.getBounds().intersects(temp.getBounds())) {
					blueFive = true;	
				}
			}
		}
		
		animation.tick();
	}
	

	@Override
	public void render(Graphics g) {
		//g.drawRect(x, y, width, height);

		if (blueFive) {
			g.setFont( new Font("Arial", Font.BOLD, 70));
			g.setColor(Color.CYAN);
			g.drawString("+5", Game.WIDTH / 2 + 50,  280);	
		}
		
		if (greenGrav) {
			g.setFont( new Font("Arial", Font.BOLD, 60));
			g.setColor(Color.YELLOW);
			int textWidth = g.getFontMetrics().stringWidth("NO GRAVITY");
			g.drawString("NO GRAVITY", Game.WIDTH / 2 - textWidth/2,  280);	
		}
		
		animation.render(g);
	}
	
}

package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.main.Game;

public class Ground {
	
	private BufferedImage image;
	private int x1, x2;
	
	private float velX;
	
	public Ground() {
		x1 = 0;
		x2 = Game.WIDTH;
		
		velX = 3;
		
		image = GraphicsLoader.loadGraphics("ground.png");
	}
	
	public void tick() {
		x1 -= velX;
		x2 -= velX;
		
		if(x1 + Game.WIDTH < 0) {
			x1 = Game.WIDTH;
		}
		
		if(x2 + Game.WIDTH < 0) {
			x2 = Game.WIDTH;
		}	
	}
	
	public void render(Graphics g) {
		g.drawImage(image, x1, Game.HEIGHT - 180, null);
		g.drawImage(image, x2, Game.HEIGHT - 180, null);
	}
}

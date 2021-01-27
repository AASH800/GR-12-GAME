package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.main.Game;

public class Ground {
	
	private BufferedImage image;
	private int x1, x2;
	public static int n = 1;
	public static float velX = n * -3;
	
	public Ground() {
		x1 = 0;
		x2 = n * Game.WIDTH;
				
		image = GraphicsLoader.loadGraphics("ground.png");
	}
	
	public void tick() {
		x1 += velX;
		x2 += velX;
		
		if (n == -1) {
			if(x1 - Game.WIDTH > -1) {
				x1 = Game.WIDTH;
			}
			
			if(x2 - Game.WIDTH > -1) {
				x2 = Game.WIDTH;
			}
		} else {
		
			if(x1 + Game.WIDTH < 0) {
				x1 = Game.WIDTH;
			}
			
			if(x2 + Game.WIDTH < 0) {
				x2 = Game.WIDTH;
			}
		}	
	}
	
	public void render(Graphics g) {
		g.drawImage(image, x1, Game.HEIGHT - 180, null);
		g.drawImage(image, x2, Game.HEIGHT - 180, null);
	}
}

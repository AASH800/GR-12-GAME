package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mightyMoth.enums.LampType;
import mightyMoth.handlers.ObjectHandler;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.main.Game;
import mightyMoth.supers.GameObject;

public class Lamp extends GameObject {
	LampType type;
	
	private BufferedImage lampBlock;
	private BufferedImage lamp;
	public static int n = 1;
	
	public Lamp(int x, int y, int width, int height, LampType type) {
		super(x, y, width, height);
		
		this.type = type;
		this.velX = n * 3;
		
		lamp = GraphicsLoader.loadGraphics("lamp.png");
		
		if(type == LampType.BOTTOM) {
			lampBlock = GraphicsLoader.loadGraphics("lampbottom.png");
		} else if(type == LampType.TOP) {
			lampBlock = GraphicsLoader.loadGraphics("lamptop.png");
		}
	}
	
	@Override
	public void tick() {
		x -= velX;
		
		if(x + width < 0 && Lamp.n == 1) {
			ObjectHandler.removeObject(this);
			
			if(type == LampType.TOP) {
				Game.score += 1;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(type == LampType.BOTTOM) {
			g.drawImage(lamp, x, y, 72, height, null);
			g.drawImage(lampBlock, x-3, y-5, null);
		} else if(type == LampType.TOP) {
			g.drawImage(lamp, x, y, 72, height, null);
			g.drawImage(lampBlock, x-3, y + height - 36, null);
		}
	}
}

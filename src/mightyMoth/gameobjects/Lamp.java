package mightyMoth.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mightyMoth.enums.LampType;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.supers.GameObject;

public class Lamp extends GameObject {
	LampType type;
	
	BufferedImage lampBlock;
	BufferedImage lamp;
	
	public Lamp(int x, int y, int width, int height, LampType type) {
		super(x, y, width, height);
		
		this.type = type;
		this.velX = 3;
		
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
	}
	
	@Override
	public void render(Graphics g) {
		if(type == LampType.BOTTOM) {
			g.drawImage(lamp, x, y, 83, height, null);
			g.drawImage(lampBlock, x, y - 5, null);
		} else if(type == LampType.TOP) {
			g.drawImage(lamp, x, y, 83, height, null);
			g.drawImage(lampBlock, x, y + height - 45, null);
		}
	}
}

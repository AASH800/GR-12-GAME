package mightyMoth.supers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	
	private int x;
	private int y;
	private int currentImage;
	
	private long delay;
	private long startTime;
	
	private boolean loop;
	private boolean running;
	
	
	private GameObject target;
	private BufferedImage[] images;
	
	public Animation(GameObject target, long delay, boolean loop, BufferedImage[] images) {
		this.x = target.getX();
		this.y = target.getY();
		this.currentImage = 0;
		this.delay = delay;
		this.startTime = 0;
		this.loop = loop;
		this.setTarget(target);
		this.images = images;
	}
	
	public void render(Graphics g) {
		
		if(target == null) {
			g.drawImage(images[currentImage], x, y, null);
		}else if(delay == 155){
			g.drawImage(images[currentImage], target.x - 25, target.y - 28, null);
		}else {
			g.drawImage(images[currentImage], target.x, target.y, null);
		}
	}
	
	public void tick() {
		long pastTime = (System.nanoTime() - startTime) / 1000000;
		
		if(pastTime >= delay && running) {
			currentImage++;
			startTime = System.nanoTime();
		}
		
		if(currentImage == images.length){
			currentImage = 0;
			
			if(!loop) {
				stop();
			}
		}
	}
	
	public void start() {
		this.running = true;
		this.currentImage = 0;
		this.startTime = 0;
	}
	
	public void stop() {
		this.running = false;
		this.currentImage = 0;
		this.startTime = 0;
	}

	public GameObject getTarget() {
		return target;
	}
	
	public void setTarget(GameObject target) {
		this.target = target;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

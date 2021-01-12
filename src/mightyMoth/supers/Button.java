package mightyMoth.supers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Button {
	
	public int x;
	public int y;
	public int width;
	public int height;
	
	public boolean pressed;
	
	private BufferedImage image;
	
	public Button(int x, int y, int width, int height, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}
	
	public static boolean checkCollision(int mouseX, int mouseY, Button btn) {
		if (mouseX >= btn.x && mouseX <= btn.x + btn.width && mouseY >= btn.y && mouseY <= btn.y + btn.height) {
			return true;
		}else {
			return false;
		}
	}
	
	public void render(Graphics g) {
		if(pressed) {
			g.drawImage(image, x + 1, y + 1, width - 2, height -1, null);
		} else {
			g.drawImage(image, x, y, null);
		}
	}
}

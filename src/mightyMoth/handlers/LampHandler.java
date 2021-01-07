package mightyMoth.handlers;

import java.util.Random;

import mightyMoth.enums.LampType;
import mightyMoth.gameobjects.Lamp;
import mightyMoth.main.Game;

public class LampHandler {
	
	private static Random random = new Random();
	
	public static int groundSize = 200;
	public static int area = Game.HEIGHT - groundSize;
	public static int spacing = 300;
	public static int minSize = 40;
	public static int maxSize = area - spacing - minSize;
	
	public static void spawnLamp() {
		int heightTop = random.nextInt(maxSize) + 1;
		 
		while(heightTop < minSize) {
			heightTop = random.nextInt(maxSize) + 1;
		}
		
		int heightBottom = area - spacing - heightTop;
		
		Lamp lampTop = new Lamp(500, 0, 78, heightTop, LampType.TOP);
		Lamp lampBottom = new Lamp(500, heightTop + spacing, 78, heightBottom, LampType.BOTTOM);
		
		ObjectHandler.addObject(lampTop);
		ObjectHandler.addObject(lampBottom);
	}
			
}

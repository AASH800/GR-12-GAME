package mightyMoth.handlers;

import java.util.Random;

import mightyMoth.enums.LampType;
import mightyMoth.gameobjects.Lamp;
import mightyMoth.gameobjects.Coin;
import mightyMoth.main.Game;

public class LampHandler {
	
	private static Random random = new Random();
	
	public static int groundSize = 180;
	public static int area = Game.HEIGHT - groundSize;
	public static int spacing = 200;
	public static int minSize = 40;
	public static int maxSize = area - spacing - minSize;
	public static int delay = 1;
	public static int now;
	public static int heightTop;
	public static int heightBottom;
	
	public static void spawnLamp() {
		heightTop = random.nextInt(maxSize);
		 
		while(heightTop < minSize) {
			heightTop = random.nextInt(maxSize);
		}
		
		heightBottom = area - spacing - heightTop;
		
		Lamp lampTop = new Lamp(500, 0, 78, heightTop, LampType.TOP);
		Lamp lampBottom = new Lamp(500, heightTop + spacing, 78, heightBottom, LampType.BOTTOM);
		Coin blueCoin = new Coin(250, heightTop, 78, 100, "blue");
		
		ObjectHandler.addObject(lampTop);
		ObjectHandler.addObject(blueCoin);
		ObjectHandler.addObject(lampBottom);
	}
	
	public static void tick() {
		if(now < delay) {
			now ++;
		} else {
			spawnLamp();
			//CoinHandler.spawnCoin();
			now = 0;
		}
	}		
}

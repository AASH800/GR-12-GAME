package mightyMoth.handlers;

import mightyMoth.enums.LampType;
import mightyMoth.gameobjects.Coin;
import mightyMoth.gameobjects.Lamp;

public class CoinHandler {
	
	public static int delay = 1;
	public static int now;
	
	public static void spawnCoin() {
				
		Coin blueCoin = new Coin(250, LampHandler.heightTop, 78, 100, "blue");
		Coin greenCoin = new Coin(250, 0, 78, 100, "green");
		
		ObjectHandler.addObject(blueCoin);
		ObjectHandler.addObject(greenCoin);
	}
	
	public static void tick() {
		if(now < delay) {
			now ++;
		} else {
			spawnCoin();
			now = 0;
		}
	}
}

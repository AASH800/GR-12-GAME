package mightyMoth.handlers;

import mightyMoth.gameobjects.Ground;
import mightyMoth.gameobjects.Lamp;
import mightyMoth.gameobjects.Moth;

public class Senarios {
	
	public static void blueGravity() {
		Moth.n = -1;
			
	}
	
	public static void greenDirection() {
		Ground.velX = 3;
		Lamp.n = -1;
	}    
}

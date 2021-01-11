package mightyMoth.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mightyMoth.main.Game;

public class KeyHandler implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Game.moth.setVelY(-5);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_E) {
			LampHandler.spawnLamp();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}

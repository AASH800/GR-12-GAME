package mightyMoth.handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mightyMoth.main.Game;
import mightyMoth.supers.Button;

public class MouseHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(Button.checkCollision(e.getX(), e.getY(), Game.easyButton)) {
			if(Game.gameover) {
				Game.easyButton.pressed = true;
				ObjectHandler.list.clear();
				ObjectHandler.addObject(Game.moth);
				Game.gameover = false;
				Game.easyButton.pressed = false;
				Game.score = 0;
				LampHandler.spacing = 300;
			}
		}
		
		if(Button.checkCollision(e.getX(), e.getY(), Game.mediumButton)) {
			if(Game.gameover) {
				Game.mediumButton.pressed = true;
				ObjectHandler.list.clear();
				ObjectHandler.addObject(Game.moth);
				Game.gameover = false;
				Game.mediumButton.pressed = false;
				Game.score = 0;
				LampHandler.spacing = 220;
			}
		}
		
		if(Button.checkCollision(e.getX(), e.getY(), Game.hardButton)) {
			if(Game.gameover) {
				Game.hardButton.pressed = true;
				ObjectHandler.list.clear();
				ObjectHandler.addObject(Game.moth);
				Game.gameover = false;
				Game.hardButton.pressed = false;
				Game.score = 0;
				LampHandler.spacing = 150;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}

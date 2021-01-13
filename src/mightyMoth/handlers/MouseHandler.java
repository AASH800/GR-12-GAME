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
		if(Button.checkCollision(e.getX(), e.getY(), Game.startButton)) {
			if(Game.gameover) {
				Game.startButton.pressed = true;
				ObjectHandler.list.clear();
				ObjectHandler.addObject(Game.moth);
				Game.gameover = false;
				Game.startButton.pressed = false;
				Game.score = 0;
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

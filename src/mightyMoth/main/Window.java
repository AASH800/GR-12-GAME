package mightyMoth.main;

import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window(int width, int height, String title, Game game) {
		
		try {
			game.serverSocket = new ServerSocket(9999);
		}catch(IOException e) {
			System.out.println("Something in german I think it means theres a mistake");
			System.exit(0);
		}
		
		setTitle(title);
		pack();
		setSize(width, height);
		
		setLocationRelativeTo(null);
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
		
		add(game);
		game.start();
	}

}

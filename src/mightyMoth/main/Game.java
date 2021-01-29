package mightyMoth.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.ServerSocket;

import mightyMoth.gameobjects.*;
import mightyMoth.handlers.*;
import mightyMoth.loaders.GraphicsLoader;
import mightyMoth.supers.Button;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 432;
	public static final int HEIGHT = 790;

	public static int score;
	
	public boolean running;

	public static boolean gameover = true;

	public static Moth moth;
	public static Ground ground;
	public static BufferedImage background;
	public static BufferedImage scoreboard;
	public static Button easyButton;
	public static Button mediumButton;
	public static Button hardButton;

	Thread thread;
	ServerSocket serverSocket;

	public static void main(String[] args) {
		new Window(WIDTH, HEIGHT, "MightyMoth", new Game());
	}

	public synchronized void start() {
		running = true;
		thread = new Thread();
		thread.start();
		run();
	}

	public void init() {
		addKeyListener(new KeyHandler());
		addMouseListener(new MouseHandler());
		scoreboard = GraphicsLoader.loadGraphics("scoreboard.png");
		background = GraphicsLoader.loadGraphics("background.png");
		ground = new Ground();
		moth = new Moth(40, 50, 36, 48);
		easyButton = new Button(27, 540, 112, 88, GraphicsLoader.loadGraphics("easy.png"));
		mediumButton = new Button(152, 540, 112, 88, GraphicsLoader.loadGraphics("meduim.png"));
		hardButton = new Button(278, 540, 112, 88, GraphicsLoader.loadGraphics("hard.png"));
	}

	public void tick() {
		if (!gameover) {
			ObjectHandler.tick();
			ground.tick();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(background, 0, 0, null);
		ground.render(g);

		ObjectHandler.render(g);

		if (gameover) {
			g.drawImage(scoreboard, 62, 100, null);
			ScoreHandler.render(g);
			Game.easyButton.render(g);
			Game.mediumButton.render(g);
			Game.hardButton.render(g);
		}

		if (!gameover) {
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.setColor(Color.WHITE);

			String s = Integer.toString(score);
			int textWidth = g.getFontMetrics().stringWidth(s);

			g.drawString(s, Game.WIDTH / 2 - textWidth / 2 - 10, 50);
		}

		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		init();
		this.requestFocus();

		long pastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - pastTime) / ns;
			pastTime = now;

			while (delta > 0) {
				tick();
				updates++;

				render();
				frames++;

				delta--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("*FPS: " + frames + " | TICKS: " + updates);
				LampHandler.tick();
				updates = 0;
				frames = 0;
			}
		}
	}
}

package mightyMoth.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.net.ServerSocket;

import mightyMoth.gameobjects.Moth;
import mightyMoth.handlers.ObjectHandler;

public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 436;
	public static final int HEIGHT = 760;
	
	public boolean running;
	
	public static Moth moth;
	
	Thread thread;
	ServerSocket serverSocket;
	
	public static void main(String[] args){
		new Window(WIDTH, HEIGHT, "MightyMoth", new Game());
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread();
		thread.start();
		run();
	}
	
	public void init() {
		moth = new Moth(50, 50, 36, 60);
	}
	
	public void tick() {
		ObjectHandler.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs ==null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		ObjectHandler.render(g);
		
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
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - pastTime) / ns;
			pastTime = now;
			
			while(delta > 0) {
				tick();
				updates++;
				
				render();
				frames++;
				
				delta--;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("*FPS: " + frames + " | TICKS: " + updates);
				updates =0;
				frames = 0;
			}
		}
		
	}

}

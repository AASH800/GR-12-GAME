package mightyMoth.main;

import java.awt.Canvas;
import java.net.ServerSocket;

public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 432;
	public static final int HEIGHT = 768;
	
	public boolean running;
	
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
		
	}
	
	public void tick() {
		
	}

	public void render() {
	
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

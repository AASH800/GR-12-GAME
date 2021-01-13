package mightyMoth.handlers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import mightyMoth.main.Game;

public class ScoreHandler {
	
	public static FileInputStream txt;
	public static FileWriter high;
	public static Scanner scanner;
	
	public static String bestScore() {
		try{
            txt = new FileInputStream("bestScore.txt");
            scanner = new Scanner(txt); 
            
            if(scanner.nextInt() < Game.score) {
            	high = new FileWriter("bestScore.txt");
            	high.write(Integer.toString(Game.score));
            	high.close();
            }
            
            return usingBufferedReader("bestScore.txt");
            
        } catch(Exception e) {System.out.println("umm, check bestScore and change it so all it hold is 0");}
		
		return "0";
	}
	
	private static String usingBufferedReader(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {e.printStackTrace();}
        
        return contentBuilder.toString();
    }
		
	public static void render(Graphics g) {
		
		g.setFont( new Font("Arial", Font.BOLD, 70));
		g.setColor(Color.WHITE);
		String s = Integer.toString(Game.score);
		int textWidth = g.getFontMetrics().stringWidth(s);
		g.drawString(s, Game.WIDTH / 2 - textWidth / 2 - 10,  280);
		
		g.setFont( new Font("Arial", Font.BOLD, 70));
		g.setColor(Color.WHITE);
		String s2 = bestScore();
		int textWidth2 = g.getFontMetrics().stringWidth(s2);
		g.drawString(s2, Game.WIDTH / 2 - textWidth2 / 2 - 10,  450);
	}
}

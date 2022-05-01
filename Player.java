package game;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class Player {
	/**
	 * Player class, ami majd a fájlbaírásnál lesz kifejezetten hasznos.
	 */
	private String name;
	private int score;
	
	public Player(String name)
	{
		this.name=name;
		this.score=GamePanel.score;
	}
	
	
	
	
}

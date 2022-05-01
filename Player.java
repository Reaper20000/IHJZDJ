package game;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;



public class Player {
	/**
	 * Player class, ami majd a fájlbaírásnál lesz kifejezetten hasznos.
	 */
	private String name;
	private int score;
	
	public Player()
	{
		this.name="asd";
		this.score=GamePanel.score;
	}
	
	public void fileIn() throws IOException
	{
		
		BufferedWriter writer= new BufferedWriter(new FileWriter("ladder.txt", true));
		String a=name +";"+score;
		writer.append(a);
		writer.newLine();
		writer.close();
		
		
	}
	public void fileOut() throws IOException
	{
		BufferedReader read= new BufferedReader(new FileReader("ladder.txt"));
		
		Map<Integer, String> map= new HashMap<Integer, String>();
		
		
	}
	
	
}

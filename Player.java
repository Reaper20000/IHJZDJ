package game;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;


public class Player {
	/**
	 * Player class, ami majd a f�jlba�r�sn�l lesz kifejezetten hasznos.
	 * Itt tároljuk a player nevét és pontszámát.
	 */
	String name;
	int score;
	
	public Player(String name, int score)
	{
		this.setName(name);
		this.score=score;
	}
	public Player()
	{
		this.setName("");
		this.score=0;
	}
	
	public String toString()
	{
		return name + " " + score;
	}
	
	public static void fileIn(Player pl) throws IOException
	{
		
		BufferedWriter writer= new BufferedWriter(new FileWriter("ladder.txt", true));
		String a=pl.toString();
		writer.append(a);
		writer.newLine();
		writer.close();
		
		
	}
	
	public void fileread(File file) throws IOException
	{
		Scanner sc= new Scanner(file);
		Map<Integer, String> map= new HashMap<Integer, String>();
		while(sc.hasNext())
		{
			String[] line=sc.nextLine().split(" ");
			score=Integer.parseInt(line[1]);
			name=line[0];
			
			map.put(score, name);
		}
		Map<Integer, String> sortedmap= new TreeMap<Integer, String>(map); //A TreeMap.ben alapból rendezve vannak key szerint az elemek
		sortedMap(sortedmap); 
	}
	
	public <K, V> void sortedMap(Map<K, V> map)
	{
		/**
		 * A map kiírására szolgáló függvény
		 */
		System.out.println("Név \t Pontszám");
		for(Map.Entry<K, V> entry : map.entrySet())
		{
			System.out.println(entry.getValue() + " \t "  + entry.getKey());
		}
			
	}
	public int setScore(int score)
	{
		return this.score=score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
}

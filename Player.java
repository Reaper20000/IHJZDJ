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
		/*
		 * Ebben a konstruktorban adjuk meg a player nevét meg pontszámát
		 */
		this.setName(name);
		this.setScore(score);
	}
	public Player()
	{
		this.setName("");
		this.setScore(0);
	}
	
	public String toString()
	{
		/*
		 * A nevet meg a pontszámot stringgé alakítjuk és a ez a két változó egy szóközzel lesz elválasztva.
		 */
		return name + " " + score;
	}
	
	public static void fileIn(Player pl) throws IOException
	{
		/*
		 * Mewghívjuk a BufferedWriter-t de igazából akármelyik másik writert használhatnánk. Megadjuk neki hogy melyik fájlba kell írnia és azt is hogy ne felülírja hanem hogy bővítse.
		 * Ezután a player-t stringgé alakítjuk és hozzáfűzzük a fájlhoz. és bezárjuk a writer-t
		 */
		BufferedWriter writer= new BufferedWriter(new FileWriter("ladder.txt", true));
		String a=pl.toString();
		writer.append(a);
		writer.newLine();
		writer.close();
		
		
	}
	
	public void fileread(File file) throws IOException
	{
		/*
		 * Ő a fájlból olvasásért felelős. Létrehozunk egy scanner ami majd kapni fog egy fájlnevet és abból fog olvasni. 
		 * És akkor még van egy while ciklusunk ami addig fut ameddig van mit beolvasnia
		 * Egy String tömbbe olvasgatjuk be az adott elemeket amiket a " " mentén tudunk splitelni.
		 * A pontszámokat integer-ré kell alakítanunk de a névhez nem kell hozzányúlni. Ezt eltároljuk egy mapben ami értelemszerűen Integer és String pársokat tud tárolni
		 * 
		 * De ezzel még nincs vége mert nincsenek sorrendben. Szükségünk lesz egy TreeMap-re ami azért is jó nekünk mert ő kulcsok szerint tudja rendezni az elemeket.
		 * És akkor ezzel meg is van a rangsorunk.
		 */
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
		 * A map kiírására szolgáló függvény. A korábban említett rangsort valahogy ki is kell írnunk és erre van ez a függvény.
		 * Először nevet (ami az érték) majd a pontszámot írjuk ki (ami a kulcs)
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
	public int getScore()
	{
		return score;
	}
		
}

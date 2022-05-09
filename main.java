package game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class main 
{
	static String name="";
	public static String menu(String name) throws NameTooLongException, NameCannotContainException
	{ 
		
		//Játékos nevének eltárolása
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter your name:");
		name=(sc.nextLine());
		if(name.contains("!") || name.contains("?") || name.contains(".") || name.contains(",") || name.contains(";") || name.contains(":")) 
			throw new NameCannotContainException();
		if(name.length()>4)
			throw new NameTooLongException();
		else
			return name;
	}
	
	public static void main(String[] args) 
	{
		/*
		 * Main metódus ahol egy parancssoros menüvel várjuk a játékosokat. 
		 * Itt Le lehet kérni a rangsort, meg el lehet indítani a játékot. Rangsor lekérése során elindítjuk a fájlból olvasás függvényünket (fileOut Lásd: Player.java)
		 * Illetve ha úgy dont az illetõ hogy inkább csak játszani szeretne akkor a Start szócska beírásával ezt meg is teheti miután emgadta a nevét. Ez a player Class name tulajdonságában lesz eltárolva
		 */
		//Balog Dániel AKA Sasa
		//Neptun: IHJZDJ
		//Menu opciók kiírása
		System.out.println("Snake game");
		System.out.println("Start");
		System.out.println("Ladder");
		System.out.println("Exit");
		
		Player pl= new Player();
		Scanner sc = new Scanner(System.in);
		String a;
		boolean nevmegfelel=false;
		//Parancssoros menü
		while(true)
		{
			
			a = sc.nextLine();
			if(a.equals("Exit"))
				break;
			
			if(a.equals("Start"))
			{
				//menu függvény hívása
				try {
					name = menu(name);
				} catch (NameTooLongException | NameCannotContainException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
					break;
				}
				GameWindow window= new GameWindow(); // létrehozzuk az ablakot
			}
			if(a.equals("Ladder"))
			{
				//fájlbaírás függvény hívása
				
				File file= new File("ladder.txt");
				try {
					pl.fileread(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

}

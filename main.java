package game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class main 
{
	static String name="";
	public static String menu(String name)
	{ 
		
		//Játékos nevének eltárolása
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter your name:");
		name=(sc.nextLine());
		return name;
	}
	
	public static void main(String[] args) 
	{
		/*
		 * Main metódus ahol egy parancssoros menüvel várjuk a játékosokat. 
		 * Itt Le lehet kérni a rangsort, meg el lehet indítani a játékot
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
		//Parancssoros menü
		while(true)
		{
			
			a = sc.nextLine();
			if(a.equals("Exit"))
				break;
			
			if(a.equals("Start"))
			{
				name = menu(name);
				GameWindow window= new GameWindow(); // létrehozzuk az ablakot
			}
			if(a.equals("Ladder"))
			{
				
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

package game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class main 
{
	static String name="";
	public static String menu(String name)
	{ 
		
		//J�t�kos nev�nek elt�rol�sa
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter your name:");
		name=(sc.nextLine());
		return name;
	}
	
	public static void main(String[] args) 
	{
		/*
		 * Main met�dus ahol egy parancssoros men�vel v�rjuk a j�t�kosokat. 
		 * Itt Le lehet k�rni a rangsort, meg el lehet ind�tani a j�t�kot
		 */
		//Balog D�niel AKA Sasa
		//Neptun: IHJZDJ
		//Menu opci�k ki�r�sa
		System.out.println("Snake game");
		System.out.println("Start");
		System.out.println("Ladder");
		System.out.println("Exit");
		
		Player pl= new Player();
		Scanner sc = new Scanner(System.in);
		String a;
		//Parancssoros men�
		while(true)
		{
			
			a = sc.nextLine();
			if(a.equals("Exit"))
				break;
			
			if(a.equals("Start"))
			{
				name = menu(name);
				GameWindow window= new GameWindow(); // l�trehozzuk az ablakot
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

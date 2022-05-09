package game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class main 
{
	static String name="";
	public static String menu(String name) throws NameTooLongException, NameCannotContainException
	{ 
		
		//J�t�kos nev�nek elt�rol�sa
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
		 * Main met�dus ahol egy parancssoros men�vel v�rjuk a j�t�kosokat. 
		 * Itt Le lehet k�rni a rangsort, meg el lehet ind�tani a j�t�kot. Rangsor lek�r�se sor�n elind�tjuk a f�jlb�l olvas�s f�ggv�ny�nket (fileOut L�sd: Player.java)
		 * Illetve ha �gy dont az illet� hogy ink�bb csak j�tszani szeretne akkor a Start sz�cska be�r�s�val ezt meg is teheti miut�n emgadta a nev�t. Ez a player Class name tulajdons�g�ban lesz elt�rolva
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
		boolean nevmegfelel=false;
		//Parancssoros men�
		while(true)
		{
			
			a = sc.nextLine();
			if(a.equals("Exit"))
				break;
			
			if(a.equals("Start"))
			{
				//menu f�ggv�ny h�v�sa
				try {
					name = menu(name);
				} catch (NameTooLongException | NameCannotContainException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
					break;
				}
				GameWindow window= new GameWindow(); // l�trehozzuk az ablakot
			}
			if(a.equals("Ladder"))
			{
				//f�jlba�r�s f�ggv�ny h�v�sa
				
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

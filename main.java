package game;

import java.io.IOException;

public class main 
{
	
	public static void main(String[] args) 
	{
		//Balog D�niel AKA Sasa
		//Neptun: IHJZDJ
		// TODO Auto-generated method stub
		Player pl= new Player();
		
		GameWindow window= new GameWindow(); // l�trehozzuk az ablakot
		try {
			pl.fileIn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

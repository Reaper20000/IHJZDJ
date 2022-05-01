package game;

import java.io.IOException;

public class main 
{
	
	public static void main(String[] args) 
	{
		//Balog Dániel AKA Sasa
		//Neptun: IHJZDJ
		// TODO Auto-generated method stub
		Player pl= new Player();
		
		GameWindow window= new GameWindow(); // létrehozzuk az ablakot
		try {
			pl.fileIn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

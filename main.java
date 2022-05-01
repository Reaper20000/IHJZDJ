package game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class main 
{
	public static void fileIn() throws IOException
	{
		String str="asd";
		BufferedWriter writer= new BufferedWriter(new FileWriter("ladder.txt"));
	
		writer.write(str);
		
		writer.close();
		
		
	}
	public static void main(String[] args) 
	{
		//Balog Dániel AKA Sasa
		//Neptun: IHJZDJ
		// TODO Auto-generated method stub
		try {
			fileIn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameWindow window= new GameWindow(); // létrehozzuk az ablakot
		
	}

}

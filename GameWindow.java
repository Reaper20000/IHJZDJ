package game;

import javax.swing.JFrame;

public class GameWindow extends JFrame
{	
	//Balog Dániel AKA Sasa
	//Neptun: IHJZDJ
	//Ez a class az ablak tulajdonságait állítja be mint a címe, átméretezhetõ-e stb stb
	GameWindow() 
	{
		/**
		 * Játékablak paramétereinek beállítása
		 */
		GamePanel panel= new GamePanel();
		
		this.add(panel);
		this.setTitle("Snake nagyházi"); //Ablak címe
		this.setResizable(false); //Nem újraméretezhetõ
		this.setVisible(true); //Az adott komponens látható
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Bezáródik az ablak ha kilépünk
		this.pack();
		
	}
	
}

package game;

import javax.swing.JFrame;

public class GameWindow extends JFrame
{	
	//Balog D�niel AKA Sasa
	//Neptun: IHJZDJ
	//Ez a class az ablak tulajdons�gait �ll�tja be mint a c�me, �tm�retezhet�-e stb stb
	GameWindow() 
	{
		/**
		 * J�t�kablak param�tereinek be�ll�t�sa
		 */
		GamePanel panel= new GamePanel();
		
		this.add(panel);
		this.setTitle("Snake nagyh�zi"); //Ablak c�me
		this.setResizable(false); //Nem �jram�retezhet�
		this.setVisible(true); //Az adott komponens l�that�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Bez�r�dik az ablak ha kil�p�nk
		this.pack();
		
	}
	
}

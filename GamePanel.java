package game;

import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

import game.Player;
import game.main;
public class GamePanel extends JPanel implements ActionListener
{	
	//Balog Dániel AKA Sasa
	//Neptun: IHJZDJ
	
	
	static int width=800; //ablaksz�less�g
	static int height=800; //ablakmagass�g
	static int delay= 150; //Tickspeed (mennyi id� ut�n l�ptet a j�t�k)
	static int unit = 25; //egyseg mérete (mekkora egy kocka)
	static int game_units=unitNum(height, width, unit); //mennyi kockát tudunk besűríteni a képbe
	
	int x[] = new int[game_units];
	int y[] = new int[game_units];
	
	int body= 5; //A kígyó mérete
	
	
	char direction = 'D';
	static int score; //Pontszám
	
	int foodX; 
	int foodY;
	int antifoodX;
	int antifoodY;
	//Az alma x �s y koordinátái
	boolean running= false;
	Timer timer;
	Random random;
	
	public int getScore() 
	{
		return this.score;
	}
	
	public GamePanel()
	{
		/**
		 * GamePanel konstruktora
		 */
		
		random= new Random();
		this.setPreferredSize(new Dimension(width,height)); 
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new Control());

		start();
		
	}
	
	public void start() 
	{
		/**
		 * Játék indítása ami azzal jár hogy generálunk egy ételt egy véletlenszerű koordinátára a játék futását igazra állítjuk
		 * az időzítőt meg elindítjuk.
		 */
		
		foodGen();
		running= true;
		timer= new Timer(delay,this);
		timer.start();
	}
	//Tesztelhető cuccok
	public static int unitNum(int height, int width, int unit)
	{
		/*
		 * Kockák sűrűségének kiszámolása
		 */
		return (height*width)/unit;
	}
	
	public static int foodXcoord(int width, int unit)
	{
		/**
		 * Kaja X koordinátájának kiszámolása
		 */
		return (int)width/unit;
	}
	public static int foodYcoord(int height, int unit)
	{
		/**
		 * Kaja Y koordinátájának kiszámolása
		 */
		return (int)height/unit;
	}
	//vége
	public void antifoodGen()
	{
		/**
		 * Anti-Étel koordinátáinak létrehozása a pálya egy random pontján
		 */
		antifoodX=random.nextInt(foodXcoord(width, unit))*unit;
		antifoodY=random.nextInt(foodYcoord(height, unit))*unit;
	}
	public void foodGen()
	{
		/**
		 * Étel létrehozása a pálya egy random pontján
		 */
		foodX=random.nextInt(foodXcoord(width, unit))*unit;
		foodY=random.nextInt(foodYcoord(height, unit))*unit;
	}
	public static void gameOver(Graphics g)
	{
		/**
		 * Amikor a játék véget ér akkor piros szöveggel kiírjuk a képernyő közepére hogy game over! 
		 */
		g.setColor(Color.red);
		g.setFont(new Font("Times New Roman", Font.BOLD,60));
		g.drawString("Game Over", 200, 400);
		
		
		
	}
	
	public void move() 
	/**
	*Ez a függvény teszi lehetővé hogy az adott irányba mozogjona  kígyónk
	*/
	
	{
		for(int i=body; i>0; i--)
		{
			x[i]=x[i-1];
			y[i]=y[i-1];
			
		}
		switch(direction) {
		case 'U': //Felfelé
			y[0] = y[0] - unit;
			break;
		case 'D': //Lefelé
			y[0] = y[0] + unit;
			break;
		case 'L': //Balra 
			x[0] = x[0] - unit;
			break;
		case 'R': //Jobbra
			x[0] = x[0] + unit;
			break;
		}
		
		
	}
		
		
	
	public void draw(Graphics g)
	{
		/**
		 * Ezzel a függvénnyel rajzoljuk meg a pályán random megjelenő ételeket magát a kígyót, a pontszámot és a game over feliratot is
		 * A pontszám is külön meg van rajzolva és folyamatosan frissítjük amikor egy étel fel van véve. A pontszám a képernyő jobb sarkában látható 
		 */
		if (running && body>0)
		{
			int i=0;
			//Grid hogy lássuk mekkorák a különböző egységek méretei a képernyőn
			while(i<height/unit)
			{
				i++;
				g.drawLine(i*unit, 0, i*unit, height);
				g.drawLine(0, i*unit, width, i*unit);

			}
			g.setColor(Color.red);
			g.fillOval(foodX, foodY, unit, unit);
			g.setColor(Color.red);
			g.fillOval(antifoodX, antifoodY, unit, unit);
			
			
			
			for(int j= 0; j<body; j++)
			{
				if(j==0) //A kígyó feje
				{
					g.setColor(Color.green);
					g.fillRect(x[j], y[j], unit, unit);
				}
				else
				{ //Ez pedig a teste
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[j], y[j], unit, unit);
				}
			}
			g.setColor(Color.white);
			g.setFont(new Font("Elephant",Font.BOLD, 20));
			g.drawString("Score:"+score, 10, 20);
		}
		
		else
		{
			gameOver(g);
			
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		/**
		 *Minden egyes történés során, mozgatjuk a snake-et, és vizsgáljuk hogy van-e megevett kaja illetve nekiütköztünk-e valaminek, 
		 */
		if(running)
		{
			move();
			checkFood();
			checkCollisions();
		}
		repaint();
	}
	private void checkCollisions() 
	{
		/**
		 * Külön esetekre bontva megvizsgáljuk hogy nekiütköztünk-e valaminek.
		 */
		//ha a fej összeütközött a testtel
		for(int i=body; i>0; i--)
		{
			if((x[0]==x[i]) && (y[0]==y[i]))
				running = false;
		}
		//ha a fej összeütközött valamelyik olda  llal a pályán
		//jobb oldal
		if(running==false) //ha alapb�l  nem fut a játék a timer is megáll. 
			timer.stop();
			
		
		if(x[0] > width-unit)
		{
			running =false;
		}
		//bal oldal
		if(x[0] < 0)
		{
			running =false;
		}
		//alsó oldal
		if(y[0] > height-unit)
		{
			running =false;
		}
		//felső oldal
		if(y[0] < 0)
		{ 
			running =false;
		}
		
	}
	
	
	
	
	public void checkFood() 
	{
		// TODO Auto-generated method stub
		/**
		 * Növeljük a test hosszát eggyel ugyanígy a pontszámot eggyel és új ételt generálunk a pálya egy random pontján
		 */
		if((x[0]==foodX)  && (y[0]==foodY) )
		{
			body++;
			score++;
			foodGen();
			antifoodGen();
		}
		/**
		 * Ha a másik fajta ételt szedjük fel akkor viszont a test hossza és a pontszám az csökken és ezután mind a kettő ételből egy másikat generálunk
		 */
		if((x[0]==antifoodX)  && (y[0]==antifoodY))
		{
			body--;
			score--;
			foodGen();
			antifoodGen();
		}
	}
	
	
	public class Control extends KeyAdapter
	{
		/**
		 * Ez a függvény pedig hasonlóan a move függvényhez szintén az irányításért felelős viszont iktt különa  direction-t állítjuk csak.
		 * Míg a move függvényben azt hogy a terst minden része előbb utóbb abba az adott irányba haladjon.
		 */
		public void keyPressed(KeyEvent e)
		{
			char k = e.getKeyChar();

			switch(k) 
			{
			case 'a':
				if(direction=='R')
				{
					direction='R'; //Mivel nem akarjuk hogy a felhasználó 180 fokos fordulatot tudnjon tenni és önmagába forduljon a kígyóval így kizártuk ezt a lehetőséget
				}else {
					direction='L';
				}
					
				break;
				
			case 'd':
				if(direction=='L')
				{
					direction='L'; 
				}else {
					direction='R';
				}
				break;
				
			case 'w':
				if(direction=='D')
				{
					direction='D';
				}else {
					direction='U';
				}
				break;
				
			case 's':
				if(direction=='U')
				{
					direction='U'; 
				}else {
					direction='D';
				}
				break;
			
			case 'l':
				Player pl= new Player(main.name, score);
				
				try {
					Player.fileIn(pl);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
			}
		}
	}

}

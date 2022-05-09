package game;


import java.util.Random;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

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
	
	int body= 4; //A kígyó mérete
	
	
	char direction = 'D';
	static int score; //Pontszám
	
	int foodX; 
	int foodY;
	int antifoodX;
	int antifoodY;
	//Az alma x �s y koordinátái
	boolean running= false;
	Timer timer;
	static Random random;
	
	
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
		 * Kockák sűrűségének kiszámolására szolgáló függvény (magassag*szelesseg)/egyseg
		 */
		return (height*width)/unit;
	}
	
	public static int foodXcoord(int width, int unit)
	{
		/**
		 * Kaja X koordinátájának kiszámolása (szelesseg/egyseg)*egyseg
		 */
		return width/unit;
	}
	public static int foodYcoord(int height, int unit)
	{
		/**
		 * Kaja Y koordinátájának kiszámolása (magassag/egyseg)*egyseg
		 */
		return height/unit;
	}
	
	
	public void antifoodGen()
	{
		/**
		 * Anti-Étel koordinátáinak létrehozása a pálya egy random pontján. Meghivjuk a foodXcoord és foodYcoord metodust ezzel 
		 * letrhozva a az etel x es y koordinatajat  
		 */
		antifoodX=random.nextInt(foodXcoord(width, unit))*unit;
		antifoodY=random.nextInt(foodYcoord(height, unit))*unit;
	}
	public void foodGen()
	{
		/**
		 * Étel létrehozása a pálya egy random pontján
		 * Ugyanúgy mint az előző metódusnál létrehozzuk a koordinátákat. Itt is kaját generálunk csak annyi különbséggel hogy 
		 * míg ez a kaja növeli a testünk hosszát és a pontunk számát addig a másik meg csökkenti. De ez egy másik emtódusban van beállítva
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
	*Ez a függvény teszi lehetővé hogy az adott irányba mozogjon a kígyónk. Illetve hogy a testünk a fejünk után mozogojon. Az irányok külön
	*esetekre vannak bontva.
	*
	*Az ciklus ami a függvényben található arra szolgál hogy a kígyó fejét kövesse a teste. A test legutolsó kockájától (tehát közvetlenül a fej előttről indulunk)
	*és egészen a legelsőig menve toljuk el az x illetve az y koordinátákat a mozgásnak megfelelően.
	*Erre szolgál a switch() is hogy ez a kanyarodásnál is működjön. Ellenkező esetben kanyarodásnál nem fordulna velünk együtt a test.
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
		 * Létrehoztunk egy rácsot is a pályán tesztelési szempontok miatt. Ezáltal jobban látszanak az egységek méretei a pályán és nem kell a sötétben tapogatóznunk
		 * 
		 * Folyamatosan vizsgáljuk azt is a játék során hogy fut-e (running) és hogy a testünk hossza elérte-e
		 *  a nullát mert ebben az esetben is game over feliratot kell dobnunk és a játéknak vége van
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
			
			
			/*
			 * Itt rajzoljuk meg a kígyó fejét illetve testét is. A fejét onnan ismerhetjük fel hogy zöld színű míg a teste folyamatosan váltja a színét.
			 * Azt még fontos megemlíteni itt is 2 esetre van szétbontva a ciklusunk. Abban az esetben rajzoljuk újra a fejet ha a az a j[0] eleme 
			 * (azért nem i[0] mert az i-t már használtuk egy korábbi ciklusban eggyel feljebb)
			 */
			for(int j= 0; j<body; j++)
			{
				if(j==0) //A kígyó feje
				{
					g.setColor(Color.green);
					g.fillRect(x[j], y[j], unit, unit);
				}
				else
			/*
			 * Itt meg a testét rajzoljuk újra ami nagyon hasonlóan működik mint a fej résznél
			 */
				{ //Ez pedig a teste
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[j], y[j], unit, unit);
				}
			}
			g.setColor(Color.white);
			g.setFont(new Font("Elephant",Font.BOLD, 20));
			g.drawString("Score:"+score, 10, 20);
		}
		//Ha nem fut a játék akkor game over
		else
		{
			gameOver(g);
			
		}
	}
	public void paintComponent(Graphics g)
	{
		/*
		 * Itt az adott komponenseket rajzoljuk meg a Graphics interface segítségével.
		 */
		super.paintComponent(g);
		draw(g);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		/**
		 * Ugyebár az actionPerformed akkor fut le ha valami "történik" úgymond. És ez jó is nekünk emrt így tudunk folyamatosan repaintelni
		 * és vizsgálni hogy fut-e a program és ennek megfelelően cselekedni. TEhát mozgatjuk a kígyot, csekkoljuk hogy nekimentünk-.e valaminek
		 * és azt hogy vettünk-e fel valamilyen jellegű ételt.
		 * 
		 * Ennek a függvénynek köszönhetően működik a játék is mert ha nem mozognánk nem történne semmi.
		 * 
		 *Minden egyes történés során, mozgatjuk a snake-et, és vizsgáljuk hogy van-e megevett kaja illetve nekiütköztünk-e valaminek, 
		 *Ezek mellett meg ugye mozgatjuk a kígyónkat is amennyiben fut a játék.
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
		 * Külön esetekre bontva megvizsgáljuk hogy nekiütköztünk-e valaminek. Külön kell vizsgálni a fejnek a testtel való ütközését illetve a 
		 * a különböző oldalakat ami a felső alsó jobb és bal oldal.
		 * 
		 */
		//ha a fej összeütközött a testtel tehát ugyanarra a koordinátára érnek akkor a játéknak vége van
		for(int i=body; i>0; i--)
		{
			if((x[0]==x[i]) && (y[0]==y[i]))
				running = false;
		}
		//ha a fej összeütközött valamelyik oldallal a pályán
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
		 * Növeljük a test hosszát eggyel ugyanígy a pontszámot eggyel és új ételt generálunk a pálya egy random pontján amennyiben elértük az étel koordinátáját
		 * 
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
		 * Ez a függvény pedig hasonlóan a move függvényhez szintén az irányításért felelős viszont itt külön a direction-t állítjuk csak.
		 * Itt fogadjuk be mint input az adott gomb lenyomását amilehet a W A S vagy a D. És ezek alapján határozzuk emg hogy a player melyik irányba tud fordulni.
		 * Értelemszerűen a player nem fordulhat saját magába és ezt meg is próbáltuk megelőzni
		 * 
		 * Az a gomb lenyomására ha az irány alapból jobb oldali (R = mint right) akkor az irányt nem piszkáljuk.
		 * ellenkező esetben meg balra fordítjuk.
		 * 
		 * És hasonlóan a többi esetben is de ezeket nem részletezném mert a logika tök ugyanaz.
		 * Amit még fontos kiemelni hogy az l gomb megnyomására  a játék végén el tudjuk menteni egy fájlba. amit meg is 
		 * tudunk nézni majd a menüben hogy hanyadikok vagyunk a ranglétrán
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
				
			case 'w': //
				if(direction=='D')
				{
					direction='D';
				}else {
					direction='U';
				}
				break;
				
			case 's': //s megnyomása
				if(direction=='U')
				{
					direction='U'; 
				}else {
					direction='D';
				}
				break;
			
			case 'l':
				Player pl= new Player(main.name, score); //fájlbaír
				
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

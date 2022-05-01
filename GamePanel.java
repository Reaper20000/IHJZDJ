package game;

import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener
{	

	//Balog Dániel AKA Sasa
	//Neptun: IHJZDJ
	static final int width=800; //ablaksz�less�g
	static final int height=800; //ablakmagass�g
	static final int delay= 150; //Tickspeed (mennyi id� ut�n l�ptet a j�t�k)
	static final int unit = 25; //egyseg mérete (mekkora egy kocka)
	static final int game_units=(height*width)/unit; //mennyi kockát tudunk besűríteni a képbe
	
	final int x[] = new int[game_units];
	final int y[] = new int[game_units];
	
	int body= 4; //A kígyó mérete
	
	
	char direction = 'D';
	int score; //Pontszám
	
	int foodX; 
	int foodY;
	//Az alma x �s y koordinátái
	boolean running= false;
	Timer timer;
	Random random;
	
	public GamePanel()
	{
		random= new Random();
		this.setPreferredSize(new Dimension(width,height)); 
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new Control());
		
		start();
		
	}
	public void start() 
	{
		foodGen();
		running= true;
		timer= new Timer(delay,this);
		timer.start();
	}
	public void foodGen()
	{
		foodX=random.nextInt((int)width/unit)*unit;
		foodY=random.nextInt((int)height/unit)*unit;
	}
	public void gameOver(Graphics g)
	{
		g.setColor(Color.red);
		g.setFont(new Font("Cartoon Bones", Font.BOLD,60));
		g.drawString("Game Over", 200, 400);
		
		
	}
	public void move() //Ez a függvény teszi lehetővé hogy mozogjon a kígyónk tickenként
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
		if (running)
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
	public void actionPerformed(ActionEvent e) {
		if(running)
		{
			move();
			checkFood();
			checkCollisions();
		}
		repaint();
			
		
	}
	private void checkCollisions() {
		//ha a fej összeütközött a testtel
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
		if(x[0] > height-unit)
		{
			running =false;
		}
		//felső oldal
		if(y[0] < 0)
		{
			running =false;
		}
		
	}
	public void checkFood() {
		// TODO Auto-generated method stub
		if((x[0]==foodX)  && (y[0]==foodY))
		{
			body++;
			score++;
			foodGen();
		}
		
	}
	public class Control extends KeyAdapter
	{
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
				
			}
		}
	}

}

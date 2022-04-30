package game;

import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener
{	

	//Balog D�niel AKA Sasa
	//Neptun: IHJZDJ
	static final int width=800; //ablaksz�less�g
	static final int height=800; //ablakmagass�g
	static final int delay= 150; //Tickspeed (mennyi id� ut�n l�ptet a j�t�k)
	static final int unit = 25; //egys�gm�rete (mekkora egy kocka)
	static final int game_units=(height*width)/unit; //mennyi kock�t tudunk bes�r�teni a k�pbe
	
	final int x[] = new int[game_units];
	final int y[] = new int[game_units];
	
	int body= 4; //A k�gy� m�rete
	
	
	char direction = 'D';
	int score; //Pontsz�m
	
	int foodX; 
	int foodY;
	//Az alma x �s y koordin�t�i;
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
		
	}
	public void move() //Ez a f�ggv�ny teszi lehet�v� hogy mozogjon a k�gy�nk tickenk�nt
	{
		for(int i=body; i>0; i--)
		{
			x[i]=x[i-1];
			y[i]=y[i-1];
			
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - unit;
			break;
		case 'D':
			y[0] = y[0] + unit;
			break;
		case 'L':
			x[0] = x[0] - unit;
			break;
		case 'R':
			x[0] = x[0] + unit;
			break;
		}
		
		/*
		if(direction=='U') //Felfel�		
			y[0]= y[0] - unit;
		

		if(direction=='L') //Balra
			x[0]= x[0] - unit;
		
		if(direction=='R') //Jobbra
			x[0]= x[0] + unit;
		
		if(direction=='D') //Lefel� 
			y[0]= y[0] + unit;	
			*/
	}
		
		
	
	public void draw(Graphics g)
	{
		int i=0;
		//Grid hogy l�ssuk mekkor�k a k�l�nb�z� egys�gek m�retei a k�perny�n
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
			if(j==0) //A k�gy� feje
			{
				g.setColor(Color.green);
				g.fillRect(x[j], y[j], unit, unit);
			}
			else
			{
				g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g.fillRect(x[j], y[j], unit, unit);
			}
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
		//ha a fej �ssze�tk�z�tt a fejjel
		for(int i=body; i>0; i--)
		{
			if((x[0]==x[i]) && (y[0]==y[i]))
				running = false;
		}
		//ha a fej �ssze�tk�z�tt valamelyik oldallal
		//jobb oldal
		if(running==false) //ha alapb�l nem fut a j�t�k akkor a timer meg�ll
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
		//als� oldal
		if(x[0] > height-unit)
		{
			running =false;
		}
		//fels� oldal
		if(y[0] < 0)
		{
			running =false;
		}
		
	}
	public void checkFood() {
		// TODO Auto-generated method stub
		
	}
	public class Control extends KeyAdapter
	{
		public void buttonPressed(KeyEvent e)
		{
			/*if(a.getKeyCode()==KeyEvent.VK_RIGHT) //Balra fordul�s
			{
				if(direction!='L')
				{
					direction='R'; //Mivel nem akarjuk hogy a felhaszn�l� 180 fokos fordulatot tudnjon tenni �s �nmag�ba forduljon a k�gy�val �gy kiz�rtuk ezt a lehet�s�get
				}
			}
			if(a.getKeyCode()==KeyEvent.VK_LEFT) //Balra fordul�s
			{
				if(direction!='R')
				{
					direction='L'; //Mivel nem akarjuk hogy a felhaszn�l� 180 fokos fordulatot tudnjon tenni �s �nmag�ba forduljon a k�gy�val �gy kiz�rtuk ezt a lehet�s�get
				}
			}
			if(a.getKeyCode()==KeyEvent.VK_UP) //Balra fordul�s
			{
				if(direction!='D')
				{
					direction='U'; //Mivel nem akarjuk hogy a felhaszn�l� 180 fokos fordulatot tudnjon tenni �s �nmag�ba forduljon a k�gy�val �gy kiz�rtuk ezt a lehet�s�get
				}
			}
			if(a.getKeyCode()==KeyEvent.VK_DOWN) //Balra fordul�s
			{
				if(direction!='U')
				{
					direction='D'; //Mivel nem akarjuk hogy a felhaszn�l� 180 fokos fordulatot tudnjon tenni �s �nmag�ba forduljon a k�gy�val �gy kiz�rtuk ezt a lehet�s�get
				}
			}
			*/
			switch(e.getKeyChar()) 
			{
			case KeyEvent.VK_LEFT:
				if(direction!='D')
				{
					direction='A'; //Mivel nem akarjuk hogy a felhaszn�l� 180 fokos fordulatot tudnjon tenni �s �nmag�ba forduljon a k�gy�val �gy kiz�rtuk ezt a lehet�s�get
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				if(direction!='A')
				{
					direction='D'; 
				}
				break;
				
			case KeyEvent.VK_UP:
				if(direction!='W')
				{
					direction='S';
				}
				break;
				
			case KeyEvent.VK_DOWN:
				if(direction!='S')
				{
					direction='W'; 
				}
				break;
				
			}
		}
	}

}

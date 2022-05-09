package game;

import static org.junit.Assert.*;

import org.junit.Test;



public class TestSnake 
{
	
	
	
	@Test
	public void toStringTest() 
	{
		//toString tesztel�se
		String exp="Sasa 10";
		Player pl= new Player();
		pl.setName("Sasa");
		pl.setScore(10);
		assertEquals(exp, pl.toString());
		
	}
	@Test
	public void getScoreTest()
	{
		//getScore tesztel�se
		int scoreexp=10;
		
		Player pl=new Player("name",10);
		
		assertEquals(scoreexp, pl.getScore());
	}
	
	@Test
	public void setScoreTest()
	{
		Player pl = new Player("asd",10);
		Player plexp = new Player("asd2",20);
		
		pl.setScore(20);
		assertEquals(plexp.getScore(),pl.getScore());
	}
	@Test
	public void getNameTest()
	{
		//getName tesztel�se
		String name="name";
		
		Player pl=new Player("name",10);
		
		assertEquals(name, pl.getName());
	}
	@Test
	public void setNameTest()
	{
		Player pl = new Player("asd",10);
		Player plexp = new Player("asd2",20);
		
		pl.setName("asd2");
		assertEquals(plexp.getName(),pl.getName());
	}
	@Test
	public void unitNumTest()
	{
		//UnitNum tesztel�se
		int exp = (int)(800*800)/25;
		
		assertEquals(exp, GamePanel.unitNum(800, 800, 25));
	}
	
	@Test 
	public void foodXcoordTest()
	{
		//foodXcoord tesztel�se
		// 100/25
		int exp= 4;

		assertSame(exp,GamePanel.foodXcoord(100,25));
	}
	@Test
	public void foodYcoordTest()
	{
		//foodYcoord tesztel�se
		int exp= 4;
		GamePanel.foodYcoord(100,25);
		
		assertSame(exp, GamePanel.foodYcoord(100,25));
	}
	@Test (expected=NameTooLongException.class)
	public void menutoolongTest() throws NameTooLongException, NameCannotContainException
	{
		String name ="alma";
		main.menu(name);
		
	}
	@Test (expected=NameCannotContainException.class)
	public void Testnamecontainsmenu() throws NameCannotContainException, NameTooLongException
	{
		String name ="alma";
		main.menu(name);
		
	}
}

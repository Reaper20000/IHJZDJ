package game;
import game.Player;
public class NameTooLongException extends Exception
{
	protected Player pl;
	
	public String getMessage() 
	{
		return "A n�v 4 karaktern�l ne lehet hosszabb";
	}
	
}

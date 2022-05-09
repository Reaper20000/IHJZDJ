package game;
import game.Player;
public class NameTooLongException extends Exception
{
	protected Player pl;
	
	public String getMessage() 
	{
		return "A név 4 karakternél ne lehet hosszabb";
	}
	
}

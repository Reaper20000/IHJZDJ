package game;
import game.Player;
public class NameCannotContainException  extends Exception
{
	protected Player pl;
	public String getMessage()
	{
		return "A n�v nem tartalmazhat �r�sjeleket";
	}
}

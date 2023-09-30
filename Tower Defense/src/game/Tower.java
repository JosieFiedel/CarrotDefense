/**
 * Tower superclass--Does not contain any methods since each method in the subclasses is individually
 * unique.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

public abstract class Tower implements Animatable 
{	
	// Fields
	protected GameState state;
	protected int x, y;

	/**
	 * Constructor--Primarily acts as an initializer.
	 * @param state
	 */
	public Tower(GameState state, int x, int y)
	{
		this.state = state;
		this.x = x;
		this.y = y;
	}
}
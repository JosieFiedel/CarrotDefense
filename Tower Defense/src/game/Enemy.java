/**
 * Enemy superclass of EnemyBunny.java, EnemyArmyBunny.java, and EnemyFastBunny.java--contains methods
 * of instruction for returning the Enemy object's position. 
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Point;

public abstract class Enemy implements Animatable
{
	// Fields
	protected GameState state;
	protected double position;
	
	/**
	 * Constructor--sets the position to the given double parameter.
	 * @param p (position of the Enemy as a double)
	 */
	public Enemy (double p, GameState state)
	{
		this.state = state;
		position = p;
	}
	
	/**
	 * Accessor--Returns the position of the Enemy object as a percentage down the path.
	 * @return position 
	 */
	public double getBunnyPos()
	{
		return position;
	}
	
	/**
	 * Uses the resource loader to access the position of the given Enemy object and returns that
	 * position.
	 * @return p (position of the Enemy as a point).
	 */
	public Point getPosition()
	{
		Point p = ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
		return p;
	}
}
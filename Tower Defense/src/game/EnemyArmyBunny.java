/**
 * Subclass of the Enemy.java class--controls the EnemyArmyBunny object. 
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemyArmyBunny extends Enemy
{
	// Field
	private double speed;
	
	/**
	 * Constructor--Primarily acts as an initializer.
	 * @param p (position of the EnemyArmyBunny)
	 * @param state
	 */
	public EnemyArmyBunny(double p, GameState state)
	{
		super(p, state);
		speed = .035; // Travels half the speed of the EnemyBunny object.
	}
	
	/**
	 * Updates the position of the EnemyArmyBunny depending on the elapsed time.
	 */
	public void update(double timeElapsed) 
	{
		position = position + speed * timeElapsed;
		// Decrease 2 lives and remove the EnemyArmyBunny object if it reaches the end of the path.
		if(position > 1)
		{
			state.setLives(-2);
			state.removeGameObject(this);
		}
	}

	/**
	 * Draws the EnemyArmyBunny object using two images to simulate movement depending on the path
	 * position.
	 */
	public void draw(Graphics g) 
	{
    	Point p = getPosition();
    	double posRounded = position*100;
    	
	    if((int)posRounded % 2 == 0)
	       	g.drawImage(ResourceLoader.getLoader().getImage("armyBunny.png"), p.x-30, p.y-30, null);
	    else
	       	g.drawImage(ResourceLoader.getLoader().getImage("movingArmyBunny.png"), p.x-31, p.y-34, null);
	}
}
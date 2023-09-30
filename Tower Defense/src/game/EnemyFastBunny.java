/**
 * Subclass of the Enemy.java class--controls the EnemyFastBunny object. 
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemyFastBunny extends Enemy
{
	// Field
	private double speed;
	
	/**
	 * Constructor--Primarily acts as an initializer.
	 * @param p (position of the EnemyBunny)
	 * @param state
	 */
	public EnemyFastBunny(double p, GameState state)
	{
		super(p, state);
		speed = .1;	// Travels .03 pixels faster than the EnemyBunny object. 
	}
	
	/**
	 * Updates the position of the EnemyBunny depending on the elapsed time.
	 * @param timeElapsed
	 */
	public void update(double timeElapsed) 
	{
		position = position + speed * timeElapsed;
		// Decrease 1 life and remove the EnemyFastBunny object if it reaches the end of the path.
		if(position > 1)
		{
			state.setLives(-1);
			state.removeGameObject(this);
		}		
	}

	/**
	 * Draws the EnemyFastBunny object using two images to simulate movement, depending on the path
	 * position.
	 */
	public void draw(Graphics g) 
	{
    	Point p = getPosition();
    	double posRounded = position*100;
    	
    	if((int)posRounded % 2 == 0)
    		g.drawImage(ResourceLoader.getLoader().getImage("fastBunny.png"), p.x-30, p.y-30, null);
    	else
    		g.drawImage(ResourceLoader.getLoader().getImage("movingFastBunny.png"), p.x-30, p.y-30, null);
	}
}

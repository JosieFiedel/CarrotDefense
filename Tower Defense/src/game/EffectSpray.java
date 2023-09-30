/**
 * Subclass of the Effect.java class--Draws the EffectSpray object and updates its position.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EffectSpray extends Effect
{
	// Field
	private double age; // How long in seconds this object has existed. 
	
	/**
	 * Constructor--Primarily acts as an initializer. 
	 * @param state(GameState object)
	 * @param origin(tower point)
	 * @param dest(destination point)
	 */
	public EffectSpray(GameState state, Point origin, Point dest)
	{
		super(state, origin, dest);
		age = 0;
	}
	
	/**
	 * Updates the position of the EffectSpray Object depending on the amount of time elapsed.
	 * @parameter timeElapsed
	 */
	public void update(double timeElapsed) 
	{
		age+= timeElapsed;
		// Remove the object when it has existed for more than 1 second. 
		if (age > 1)
		{
			state.removeGameObject(this);
			return;
		}
		// Change the coordinates of the spray depending on distance and elapsed time.
		x += dx * timeElapsed;
		y += dy * timeElapsed;
		
		// Hit the enemies.
		enemiesHit(x, y);
	}

	/**
	 * Draws the EffectSpray object.
	 */
	public void draw(Graphics g) 
	{	
    	g.drawImage(ResourceLoader.getLoader().getImage("sprayEffect.png"), (int) x-10, (int) y-9, null);
	}
}
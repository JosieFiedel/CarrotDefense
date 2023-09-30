/**
 * Subclass of the Effect.java class--Draws the EffectPoison object and updates its position.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EffectPoison extends Effect
{	
	/**
	 * Constructor--Primarily acts as an initializer. 
	 * @param state(GameState object)
	 * @param origin(tower point)
	 * @param dest(destination point)
	 */
	public EffectPoison(GameState state, Point origin, Point dest)
	{
		super(state, origin, dest);
	}
	
	/**
	 * Updates the position of the EffectPoison Object depending on the amount of time elapsed.
	 * @parameter timeElapsed
	 */
	public void update(double timeElapsed) 
	{
		// Change the coordinates of the spray depending on distance and elapsed time. 
		x += dx * timeElapsed;
		y += dy * timeElapsed;
		
		// Hit the enemies.
		enemiesHit(x, y);
	}

	/**
	 * Draws the EffectPoison object.
	 */
	public void draw(Graphics g) 
	{
    	g.drawImage(ResourceLoader.getLoader().getImage("poisonEffect.png"), (int) x-10, (int) y-9, null);
	}
}
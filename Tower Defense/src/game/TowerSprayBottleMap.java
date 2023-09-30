/**
 * Subclass of the Tower.java class--Represents the TowerSprayBottle object on the map. Updates 
 * and draws relating to this object.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerSprayBottleMap extends Tower 
{
	// Fields
	private double timeSinceLastShot;

	/**
	 * Constructor--changes the current object variables given parameters.
	 * @param state
	 * @param x
	 * @param y
	 */
	public TowerSprayBottleMap(GameState state, int x, int y)
	{
		super(state, x, y);
		timeSinceLastShot = 0;
	}
	
	/**
	 * Updates the TowerSprayBottle object on the map. When an Enemy object is nearby, an EffectSpray
	 * object is created and travels toward the Enemy object.
	 */
	@Override
	public void update(double elapsedTime) 
	{
		// See if enough time has elapsed to fire again (1 second). 
		timeSinceLastShot += elapsedTime;
		if(timeSinceLastShot < 1)
			return;
		
		// Find the nearest enemy within range. 
		Point towerPoint = new Point(x+10,y+10);
		
		Enemy e = state.findNearestEnemy(towerPoint);
		
		if (e == null)
			return;
		if (towerPoint.distance(e.getPosition()) > 100)
			return; // Enemy is too far away.
			
		EffectSpray s = new EffectSpray(state, towerPoint, e.getPosition());
		state.addGameObject(s);

		timeSinceLastShot = 0;
	}

	/**
	 * Draws the TowerSprayBottle object on the map using 2 images to give the illusion of animation. 
	 * The firing image occurs when the timeSinceLastShot is less than .5 seconds. Otherwise, the 
	 * default image is drawn.
	 */
	@Override
	public void draw(Graphics g) 
	{
		if(timeSinceLastShot < .5)
			g.drawImage(ResourceLoader.getLoader().getImage("sprayBottleSpraying.png"), x-3, y-3, null);
		else
			g.drawImage(ResourceLoader.getLoader().getImage("sprayBottle.png"), x, y, null);
	}
}

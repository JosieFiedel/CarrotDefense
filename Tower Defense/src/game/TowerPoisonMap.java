/**
 * Subclass of the Tower.java class--Represents the TowerPoison object on the map. Updates 
 * and draws relating to this object.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerPoisonMap extends Tower 
{
	// Field
	private double timeSinceLastShot;

	/**
	 * Constructor--changes the current object variables given parameters.
	 * @param state
	 * @param x
	 * @param y
	 */
	public TowerPoisonMap(GameState state, int x, int y)
	{
		super(state, x, y);		
		timeSinceLastShot = 0;
	}
	
	/**
	 * Updates the TowerPoison object on the map. When an Enemy object is nearby, four EffectPoison
	 * objects are created, traveling in 4 different directions across the screen.
	 */
	@Override
	public void update(double elapsedTime) 
	{
		// See if enough time has elapsed to fire again (2 seconds). 
		timeSinceLastShot += elapsedTime;
		if(timeSinceLastShot < 2)
			return;
			
		Point towerPoint = new Point(x+30,y+30);
		// Find the nearest Enemy object to the Tower object within range. 	
		Enemy e = state.findNearestEnemy(towerPoint);
			
		if (e == null)
			return;
		if (towerPoint.distance(e.getPosition()) > 100)
			return; // Enemy is too far away.
		
		// Four EffectPoison objects are created, traveling in opposite directions.
		EffectPoison s = new EffectPoison(state, towerPoint, new Point(x+600,y+000));
		EffectPoison t = new EffectPoison(state, towerPoint, new Point(x-600,y+000));
		EffectPoison u = new EffectPoison(state, towerPoint, new Point(x+000,y+600));
		EffectPoison v = new EffectPoison(state, towerPoint, new Point(x+000,y-600));
		
		state.addGameObject(s);
		state.addGameObject(t);
		state.addGameObject(u);
		state.addGameObject(v);
		
		timeSinceLastShot = 0;
	}

	/**
	 * Draws the TowerPoison object on the map using 2 images to give the illusion of animation. The
	 * firing image occurs when the timeSinceLastShot is less than .5 seconds. Otherwise, the default
	 * image is drawn.
	 */
	@Override
	public void draw(Graphics g) 
	{
		if(timeSinceLastShot < .5)
			g.drawImage(ResourceLoader.getLoader().getImage("poisonEffectImage.png"), x+2, y-5, null);
		else
			g.drawImage(ResourceLoader.getLoader().getImage("poison.png"), x, y, null);
	}
}
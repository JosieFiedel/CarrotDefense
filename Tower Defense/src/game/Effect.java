/**
 * Superclass of EffectPoison.java and EffectSpray.java--contains methods of instruction for if the
 * Effect object is in range of an Enemy object. Simulates the enemy being hit by a projectile, being 
 * eliminated from the game, and increasing the credits and carrots saved.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Point;

public abstract class Effect implements Animatable
{
	// Fields
	protected GameState state;
	protected double x,y; // The draw location of the spray.
	protected double dx, dy; // Distance from the tower to the destination.
	
	/**
	 * Constructor--Primarily acts as an initializer.
	 * @param state
	 */
	public Effect(GameState state, Point origin, Point dest)
	{
		this.state = state;
		x = origin.x;
		y = origin.y;
		dx = dest.x - origin.x;
		dy = dest.y - origin.y;
	}

	/**
	 * When the Effect object is within range of the enemy (30 pixels), both objects are removed, the
	 * credits are increased and the "Carrots Saved" is increased. 
	 * @param x (x coord of the Effect object)
	 * @param y (y coord of the Effect object)
	 */
	public void enemiesHit(double x, double y)
	{
		Point p = new Point((int)x,(int)y);
		
		// If the Effect object travels out of the play screen, remove it. 
		if(p.x < 0 || p.x > 600 || p.y < 0 || p.y > 600)
			state.removeGameObject(this);
		
		Enemy e = state.findNearestEnemy(p);
		
		// If the enemy is not null and the distance between objects is less than 30 pixels...
		if(e != null && e.getPosition().distance(p) < 30)
		{
			state.removeGameObject(e);
			state.removeGameObject(this);
			
			// If the enemy is an EnemyBunny or an EnemyFast Bunny object, increase the credits by $10
			// and number of carrots saved by 1.
			if(e instanceof EnemyBunny || e instanceof EnemyFastBunny)
			{
				state.setCredits(10);
				state.setCarrotsSaved(1);
			}
			
			// If the enemy is an EnemyArmyBunny, add a new EnemyBunny object in place of the removed
			// EnemyArmyBunny object.
			else if(e instanceof EnemyArmyBunny)
			{
				double pos = e.getBunnyPos();	// Gets the position of the enemy.
				state.addGameObject(new EnemyBunny(pos, state));
			}
		}
	}
}
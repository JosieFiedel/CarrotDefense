/**
 * Subclass of the Tower.java class--Represents the TowerSprayBottle object while moving it. Updates and 
 * draws relating to this object.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerSprayBottleMoving extends Tower 
{
	/**
	 * Constructor that changes the current object variables given parameters.
	 * @param state
	 */
	public TowerSprayBottleMoving(GameState state, int x, int y)
	{
		super(state, x, y);
	}
	
	/**
	 * Updates the TowerSprayBottle object while moving it. Either places the tower or reimburses credits
	 * depending on where the user clicks the mouse.
	 */
	public void update(double elapsedTime) 
	{
		Path p = ResourceLoader.getLoader().getPath("path.txt");
		Point q = new Point(state.getMouseX(), state.getMouseY()-25);
		Point r = new Point(state.getMouseX(), state.getMouseY()+25);
		
		if (state.isMouseClicked() && p.distanceToNearestPathNode(q) > 45 && 
				p.distanceToNearestPathNode(r) > 45)
		{
			state.removeGameObject(this);
			
			// If the mouse is clicked on the playfield, put it in the game.
			if (state.getMouseX() < 600 && state.getMouseY() < 600)
				state.addGameObject(new TowerSprayBottleMap(state, state.getMouseX() - 35, state.getMouseY() - 50));
			
			// If the mouse is clicked on the menu, reimburse the credits used to buy the tower.
			else if (state.getMouseX() > 600)
				state.setCredits(50);
		}
	}

	/**
	 * Draws the TowerSprayBottle object while moving it.
	 */
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(ResourceLoader.getLoader().getImage("sprayBottle.png"), state.getMouseX() - 35, state.getMouseY() - 50, null);
	}

}

/**
 * Subclass of the Tower.java class--Represents the TowerPoison object while moving it. Updates and 
 * draws relating to this object.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerPoisonMoving extends Tower 
{
	/**
	 * Constructor--changes the current object variables given parameters.
	 */
	public TowerPoisonMoving(GameState state, int x, int y)
	{
		super(state, x, y);
	}
	
	/**
	 * Updates the TowerPoison object while moving it. Either places the tower or reimburses credits
	 * depending on where the user clicks the mouse.
	 */
	@Override
	public void update(double elapsedTime) 
	{
		Path p = ResourceLoader.getLoader().getPath("path.txt");
		Point q = new Point(state.getMouseX(), state.getMouseY());
		
		if (state.isMouseClicked() && p.distanceToNearestPathNode(q) > 45)
		{
			state.removeGameObject(this);
			
			// If the mouse is clicked on the playfield, put it in the game.
			if (state.getMouseX() < 600 && state.getMouseY() < 600)
				state.addGameObject(new TowerPoisonMap(state, state.getMouseX() - 30, state.getMouseY() - 34));
			
			// If the mouse is clicked on the menu, reimburse the credits used to buy the tower.
			else if (state.getMouseX() > 600)
				state.setCredits(150);
		}
	}

	/**
	 * Draws the tower while moving it.
	 */
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(ResourceLoader.getLoader().getImage("poison.png"), state.getMouseX() - 30, state.getMouseY() - 34, null);
	}
}
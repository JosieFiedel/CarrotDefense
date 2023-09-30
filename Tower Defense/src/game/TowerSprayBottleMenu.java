/**
 * Subclass of the Tower.java class--Represents the TowerSprayBottle object on the menu. Updates and 
 * draws relating to this object.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public class TowerSprayBottleMenu extends Tower 
{
	/**
	 * Constructor--changes the current object variables given parameters.
	 * @param state
	 * @param x
	 * @param y
	 */
	public TowerSprayBottleMenu(GameState state, int x, int y)
	{
		super(state, x, y);
	}
	
	/**
	 * Updates the TowerSprayBottle object in the menu. Picks up the tower and removes credits for
	 * picking upthe tower. Only allows for the tower to be picked up if enough credits are held and if a
	 * certain area is clicked.
	 */
	@Override
	public void update(double elapsedTime) 
	{
		// Only allow a tower to be picked up if the player has enough credits. (50 credits)
		int cost = 50;
		if(state.getCredits() >= cost)
		{
			if (state.getMouseX() >= x && state.getMouseX() < x + 55 &&
				state.getMouseY() >= y && state.getMouseY() < y + 100 &&
				state.isMouseClicked() && state.getGameStatus() == 1)
			{
				// Pick up the tower and remove the correct number of credits.
				state.addGameObject(new TowerSprayBottleMoving(state, x, y));
				state.setCredits(-cost);
			}
		}
	}

	/**
	 * Draws the TowerSprayBottle object on the menu.
	 */
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(ResourceLoader.getLoader().getImage("sprayBottle.png"), x, y, null);
	}
}
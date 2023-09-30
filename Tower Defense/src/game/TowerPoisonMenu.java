/**
 * Subclass of the Tower.java class--Represents the TowerPoison object on the menu. Updates and draws 
 * relating to this object.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public class TowerPoisonMenu extends Tower 
{
	/**
	 * Constructor--changes the current object variables given parameters.
	 * @param state
	 * @param x
	 * @param y
	 */
	public TowerPoisonMenu(GameState state, int x, int y)
	{
		super(state, x, y);
	}
	
	/**
	 * Updates the TowerPoison object in the menu. Picks up the tower and removes credits for picking up
	 * the tower. Only allows for the tower to be picked up if enough credits are held and if a certain
	 * area is clicked.
	 */
	@Override
	public void update(double elapsedTime) 
	{
		// Only allow a tower to be picked up if the player has enough credits. (150 credits)
		int cost = 150;
		if(state.getCredits() >= cost)
		{
			if (state.getMouseX() >= x && state.getMouseX() < x + 60 &&
				state.getMouseY() >= y && state.getMouseY() < y + 68 &&
				state.isMouseClicked() && state.getGameStatus() == 1)
			{
				// Pick up the tower and remove the correct number of credits.
				state.addGameObject(new TowerPoisonMoving(state, x, y));
				state.setCredits(-cost);
			}
		}
	}

	/**
	 * Draws the TowerPoison object on the menu.
	 */
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(ResourceLoader.getLoader().getImage("poison.png"), x, y, null);
	}
}
/**
 * The StartingScreen.java class draws the starting screen and is in charge of changing the gameStatus
 * to be 1 (the play status).
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public class StartingScreen implements Animatable 
{
	// Field
	private GameState state;
	
	/**
	 * Constructor--Primarily acts as an initializer.
	 * @param state
	 */
	public StartingScreen(GameState state)
	{
		this.state = state;
	}
	
	/**
	 * Changes the gameStatus to 1 if a mouse click is registered in a restricted area. If the gameStatus
	 * is 1, the starting screen visual is removed. 
	 * @param elapsedTime
	 */
	@Override
	public void update(double elapsedTime) 
	{
		if(state.getMouseX() >= 335 && state.getMouseX() <= 563 &&  
			state.getMouseY() >= 230 && state.getMouseY() <= 289 &&
			state.isMouseClicked())
		{
			state.setGameStatus(1);
		}
		if(state.getGameStatus() == 1)
			state.removeGameObject(this);
	}

	/**
	 * Draws the starting screen.
	 * @param Graphics g
	 */
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(ResourceLoader.getLoader().getImage("gameStart.jpg"), 0, 0, null);
	}
}
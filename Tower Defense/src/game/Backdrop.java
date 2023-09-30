/**
 * This class represents the backdrop object. The backdrop is loaded.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public class Backdrop implements Animatable 
{
	/**
	 * Unused since the backdrop does not need to be updated.
	 */
	public void update(double timeElapsed) 
	{
		// Backdrop does not need to be updated.
	}

	/**
	 * Draws the backdrop.
	 */
	public void draw(Graphics g) 
	{
		// Draw the backdrop.
    	g.drawImage(ResourceLoader.getLoader().getImage("carrotPath.jpg"), 0, 0, null);
	}
}
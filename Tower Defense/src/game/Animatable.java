/**
 * The Animatable interface allows for abstraction to be achieved. 
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;

public interface Animatable 
{
	public void update(double elapsedTime);
	public void draw(Graphics g);
}

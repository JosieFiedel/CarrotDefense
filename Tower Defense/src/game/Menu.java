/**
 * The Menu class styles the menu section of the game. TowerMenu objects are represented on the menu,
 * along with the credits, carrots saved, and lives.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu implements Animatable 
{
	// Fields
	private GameState state;
	private boolean objectsAdded;
	
	/**
	 * Constructor that changes the current object variables given parameters.
	 * @param state
	 */
	public Menu(GameState state)
	{
		this.state = state;
		this.objectsAdded = false;
	}
	
	/**
	 * Adds Tower objects to the game menu.
	 */
	@Override
	public void update(double elapsedTime) 
	{
		// Is this the first update? If so, add the towers on the menu.
		if(!objectsAdded)
		{
			state.addGameObject(new TowerSprayBottleMenu(state, 650, 365));
			state.addGameObject(new TowerPoisonMenu(state, 785, 393));
			objectsAdded = true;
		}
	}

	/**
	 * Draws the menu with the lives, carrots saved, credits, and other text.
	 */
	@Override
	public void draw(Graphics g) 
	{
		// Draw the GameMenu background.
		g.drawImage(ResourceLoader.getLoader().getImage("menu.jpg"), 600, 0, null);
		
		// Draw the lives.
		g.setColor(Color.BLACK);
		int lives = state.getLives();
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Lives: " + lives, 610, 32);
		
		// Draw the credits.
		int credits = state.getCredits();
		g.drawString("Credits: $"+ credits, 610, 313);
		
		// Draw the Carrots Saved.
		int carrotsSaved = state.getCarrotsSaved();
		g.drawString("Carrots Saved: " + carrotsSaved, 610, 77);
		
		// Draw the other remaining text.
		g.drawString("$50", 665, 522);
		g.drawString("$150", 790, 522);
		g.setFont(new Font("arial",Font.BOLD, 15));
		g.drawString("Spray Bottle", 637, 569);
		g.drawString("Poison", 790, 569);
		
		//Draw the instructions.
		g.drawString("Instructions: Buy and place towers", 610, 125);
		g.drawString("to fend off the rabbits and save the", 610, 150);
		g.drawString("carrot garden!", 610, 175);
	}
}
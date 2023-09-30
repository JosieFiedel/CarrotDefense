/**
 * A GameState object represents the current 'state' of the game.  This
 * includes things like score, tower positions, etc., but also includes
 * smaller details like mouse location and mouse click information.  Also, 
 * this object will hold a List of all the things that move, update, or 
 * interact with the screen. 
 * 
 * Everything that is unique about a single game's session is here.
 * If you were to save everything stored here to a file, and then
 * reload it later, the game would be in exactly the same 'state' as
 * it was before.
 * 
 * There is exactly one GameState object for the entire game.  (It's
 * purpose is to hold the data that changes as the game changes.)
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameState 
{
	// Fields
	private List<Animatable> gameObjects;
	private List<Animatable> objectsToRemove;
	private List<Animatable> objectsToAdd;
	
	private int gameStatus;	
	private int lives;
	private int credits;
	private int carrotsSaved;
	
	private int mouseX, mouseY;
	private boolean mouseClicked;
	
	/**
	 * Constructor--initializes the field values and creates a new array list to hold the 
	 * animatable game objects.
	 */
	public GameState()
	{
		gameObjects = new ArrayList<Animatable>();
		objectsToRemove = new ArrayList<Animatable>();
		objectsToAdd = new ArrayList<Animatable>();
		
		gameStatus = 0; // The game starts on the starting screen (0,1,2).
		lives = 10; // Start with 10 lives. Decrease when enemies reach the end.
		credits = 100; // Increases with enemy kills, decreases with buying towers.
		carrotsSaved = 0; // Increases by 1 when an enemy is eliminated. 
		
		mouseX = mouseY = 0;
		mouseClicked = false;
	}
	
	/**
	 * Mutator--Adds Animatable objects from the objectsToAdd list.
	 * @param a
	 */
	public void addGameObject(Animatable a)
	{
		objectsToAdd.add(a);
	}
	
	/**
	 * Mutator--Adds Animatable objects to the objectsToRemove list.
	 * @param a
	 */
	public void removeGameObject(Animatable a)
	{
		objectsToRemove.add(a);
	}
	
	/**
	 * Accessor--Accesses and returns the game status (0 == starting, 1 == playing, 2 == ending).
	 * @return gameStatus
	 */
	public int getGameStatus()
	{
		return gameStatus;
	}
	
	/**
	 * Mutator--Changes the game status (0 == starting, 1 == playing, 2 == ending).
	 * @param status
	 */
	public void setGameStatus(int status)
	{
		this.gameStatus = status;
	}
	
	/**
	 * Accessor--Accesses and returns the current number of lives.
	 * @return lives
	 */
	public int getLives()
	{
		return lives;
	}
	
	/**
	 * Mutator--Changes the current number of lives by a parameter integer amount.
	 * @param lives
	 */
	public void setLives(int lives)
	{
		this.lives+=lives;
	}
	
	/**
	 * Accessor--Accesses and returns the current number of carrots saved. 
	 * @return carrotsSaved
	 */
	public int getCarrotsSaved()
	{
		return carrotsSaved;
	}
	
	/**
	 * Mutator--Changes the number of carrots saved by a parameter integer amount. 
	 * @param carrotsSaved
	 */
	public void setCarrotsSaved(int carrotsSaved) 
	{
		this.carrotsSaved+=carrotsSaved;
	}
	
	/**
	 * Accessor--Accesses and returns the current number of credits. 
	 * @return credits
	 */
	public int getCredits()
	{
		return credits;
	}
	
	/**
	 * Mutator--Changes the current number of credits by a parameter integer amount.
	 * @param credits
	 */
	public void setCredits(int credits)
	{
		this.credits+=credits;
	}
	
	/**
	 * Mutator--Sets the mouse location to given parameter values representing (x,y) coordinates.
	 * @param x
	 * @param y
	 */
	public void setMouseLocation(int x, int y)
	{
		mouseX = x;
		mouseY = y;
	}
	
	/**
	 * Accessor--Accesses and returns the current mouse position in X Coordinates.
	 * @return mouseX
	 */
	public int getMouseX()
	{
		return mouseX;
	}
	
	/**
	 * Accessor--Accesses and returns the current mouse position in Y coordinates.
	 * @return mouseY
	 */
	public int getMouseY()
	{
		return mouseY;
	}
	
	/**
	 * Accessor--Accesses and returns a boolean value representing the mouse state (returns true 
	 * if the mouse is clicked).
	 * @return mouseClicked
	 */
	public boolean isMouseClicked()
	{
		return mouseClicked;
	}
	
	/**
	 * Mutator--Changes the mouseClicked value to be false. Called after a mouse click.
	 */
	public void consumeMouseClick()
	{
		mouseClicked = false;
	}
	
	/**
	 * Mutator--Changes the mouseClicked value to be true.
	 */
	public void setMouseClicked()
	{
		mouseClicked = true;
	}
	
	/**
	 * Every Animatable 'a' in gameObjects is updated when called. Objects are removed or added to the
	 * objectsToRemove and objectsToAdd array lists. The lists are then cleared.
	 */
	public void updateAll(double elapsedTime)
	{
		if(gameStatus != 2) // If the game status is not 2 (if the game is not over)...
		{
			for(Animatable a : gameObjects)
				a.update(elapsedTime);
			
			// Remove.
			gameObjects.removeAll(objectsToRemove);
			objectsToRemove.clear();
			
			// Add.
			gameObjects.addAll(objectsToAdd);
			objectsToAdd.clear();
		}
	}
	
	/**
	 * Every Animatable 'a' in gameObjects is drawn when called. 
	 * @param g
	 */
	public void drawAll(Graphics g)
	{
		for(Animatable a : gameObjects)
			a.draw(g);		
		
		// If the number of lives reaches zero, the game is over.
		int lives = getLives();
		if (lives == 0)
		{
			// End the game (draw the game over screen).
			setGameStatus(2);
			g.drawImage(ResourceLoader.getLoader().getImage("gameOver.png"), 0, 0, null);
		}
	}
	
	/**
	 * Finds and returns the enemy nearest to the specified point. 
	 * @return closest (a reference to the enemy nearest to the point, or null if none.)
	 */
	public Enemy findNearestEnemy(Point p)
	{
		Enemy closest = null;
		for (Animatable a : gameObjects)
		{
			if (a instanceof Enemy)
			{
				Enemy e = (Enemy) a;

				if (closest == null)
					closest = e;
				else
				{
					Point currentClosestPos = closest.getPosition();
					Point enemyPos = e.getPosition();
					
					// Distance to current closest enemy.
					double disToCurrentClosest = currentClosestPos.distance(p);
					// Distance to enemy in consideration.
					double disToEnemy = enemyPos.distance(p);
					
					if(disToEnemy < disToCurrentClosest)
						closest = e;
				}
			}
		}
		return closest;
	}
}
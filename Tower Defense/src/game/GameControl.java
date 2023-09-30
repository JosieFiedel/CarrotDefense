/**
 * A GameControl object represents all of the logic and control needed to 
 * make the game operate.  The control is responsible for setting up the 
 * animation timer, updating positions, dealing with user actions, etc.
 * 
 * There is exactly one GameControl object for the entire game.  (That's
 * it's job - to control the game.)
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameControl implements Runnable, ActionListener
{
	// Fields
	private GameView view;
	private GameState state;
	
	private long previousTime;
	
	private double totalTime;
	private double nextBunnyTime, nextArmyBunnyTime, nextFastBunnyTime;
	private double bunnyGenTime;
	private double armyBunnyGenTime;
	private double fastBunnyGenTime;
	private int bunnySwitch;
	private int armyBunnySwitch;
	private int fastBunnySwitch;
	
	/**
	 * Constructor--Gets called from the 'main' thread.  Since building a
	 * graphical user interface (GUI) has to happen in the GUI thread, no
	 * initialization is done here.
	 */
    public GameControl ()
    {
    	// Don't add initializations here -- put them in the run function below.
    }
    
    /**
     * Initializes the game and gets it 'running'.  Note that this function should
     * only be called once on any GameControl object, and it must be called
     * from the GUI thread (not the main thread).
     */
    public void run ()
    {        	
    	state = new GameState();
    	view = new GameView(state);
    	
    	// Add Animatable objects.
    	state.addGameObject(new Backdrop());
    	state.addGameObject(new Menu(state));
    	
    	// Add the backgroud music.
    	new SoundPlayer();
    	
    	// Start the animation loop.    	
    	Timer t = new Timer(16, this); 	// Controls the speed of the moving object across the path. 
    	t.start();
    	
    	// Keep track of the start time of the first tick.
    	previousTime = System.nanoTime();
    	
    	// Initialize generation parameters.
    	totalTime = 0.0;
        nextBunnyTime = 1.0;
        nextArmyBunnyTime = 4.0;
        nextFastBunnyTime = 10.0;
        bunnyGenTime = 5;
        armyBunnyGenTime = 10;
        fastBunnyGenTime = 15;
        bunnySwitch = 1;
        armyBunnySwitch = 1;
        fastBunnySwitch = 0;
    }
    
    /**
     * Called whenever an action event happens, and we are listening to that event.
     * The timer automatically sets 'this' up as the listener above.
     */
    public void actionPerformed(ActionEvent e)
    {    	
    	// The following keep track of time. 
    	long currentTime = System.nanoTime();
    	double elapsedTime = (currentTime - previousTime)/1_000_000_000.0;
    	totalTime = totalTime + elapsedTime;
    	previousTime = currentTime;
    	
    	// If GameStatus = 0, the game is in its start phase.
    	if(state.getGameStatus() == 0)
    	{
    		totalTime = 0;
    		state.addGameObject(new StartingScreen(state));
    	}

    	// If GameStatus = 1, the game is in its play phase and enemies are generated. 
    	if(state.getGameStatus() == 1)
    	{	
    		generateEnemies();
    	}
   		
    	// The animation 'loop'--this function gets called repeatedly.
    	// Update the game objects. 
    	state.updateAll(elapsedTime);
    	
    	// Consume the mouse click if nothing else did.
    	state.consumeMouseClick();
    	
    	// Draw the game objects.
    	view.repaint();
    }
    
    /**
     * Generates the enemies systematically if certain conditions are met.
     */
    public void generateEnemies()
    {   
    	// Adds EnemyBunny object every 5 seconds, decreasing the spawn time incrementally.
    	// The switch ensures that one object is created at a time. 
    	if(state.getCarrotsSaved() % 11 == 0)
    		bunnySwitch = 1;
    	if (state.getCarrotsSaved() % 10 == 0 && state.getCarrotsSaved() != 0 && bunnySwitch == 1)
    	{
    		bunnyGenTime = bunnyGenTime/(1.25);
    		if(bunnyGenTime <= .4)
    			bunnyGenTime = .4;
    		bunnySwitch = 0;
    	}
    	if (totalTime > nextBunnyTime)
    	{
    		state.addGameObject(new EnemyBunny(0.0, state));
    		nextBunnyTime += bunnyGenTime;
    	}
    	
    	// Adds the EnemyArmyBunny object every 4 seconds, decreasing the spawn time incrementally. 
    	// The switch ensures that one object is created at a time.
    	if(state.getCarrotsSaved () % 11 == 0)
    		armyBunnySwitch = 1;
    	if(state.getCarrotsSaved() % 10 == 0 && state.getCarrotsSaved() != 0 && armyBunnySwitch == 1)
    	{
    		armyBunnyGenTime = armyBunnyGenTime/(1.25);
    		if(armyBunnyGenTime <= .4)
    			armyBunnyGenTime = .4;
    		armyBunnySwitch = 0;
    	}
    	if(totalTime > nextArmyBunnyTime)
    	{
    		state.addGameObject(new EnemyArmyBunny(0.0, state));
    		nextArmyBunnyTime += armyBunnyGenTime;
    	}
    	
    	// Adds the EnemyFastBunny object every 10 carrots saved. The switch ensures that one object
    	// is created at a time. 
    	if(state.getCarrotsSaved() % 11 == 0)
    		fastBunnySwitch = 1;
    	if(state.getCarrotsSaved() % 10 == 0 && fastBunnySwitch == 1 && state.getCarrotsSaved() != 0)
    	{
    		fastBunnySwitch = 0;
    	}
    	if(totalTime > nextFastBunnyTime)
    	{
    		state.addGameObject(new EnemyFastBunny(0.0, state));
    		nextFastBunnyTime += fastBunnyGenTime;
    	}
    }
}
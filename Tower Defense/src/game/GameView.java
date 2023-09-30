/**
 * A GameView object represents the graphical user interface to
 * the game.  It contains the code for building the JFrame (the
 * game window) and setting up the JPanels and menus.  It also
 * listens for events and sends them to the GameControl object
 * when they arrive.
 * 
 * There is exactly one GameView object for the entire game.  (It's
 * purpose is to handle drawing and events.)
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JPanel implements MouseListener, MouseMotionListener
{
	// This constant is needed to get rid of a warning.
	private static final long serialVersionUID = 1L;
	
	// Field
	private GameState state;
	
	/**
	 * GameView constructor--the 'view' is the GUI (Graphical User Interface) and
	 * this constructor builds a JFrame (window) so the user can see the 'drawing'.
	 * Mouse listeners are added.
	 */
    public GameView (GameState state)
    {
    	this.state = state;

    	// Build the frame.  The frame object represents the application 'window'.
    	JFrame frame = new JFrame ("Tower Defense 2021");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	// Add a drawing area to the frame (a panel).  Note that 'this' object IS the
    	// panel that we need, so we add it.
    	JPanel p = this;
    	frame.setContentPane(p);
    	
    	// Set the size of 'this' panel to match the size of the backdrop.    	
    	Dimension d = new Dimension(900, 600);
    	this.setMinimumSize(d);
    	this.setPreferredSize(d);
    	this.setMaximumSize(d);
    	
    	// Allow the JFrame to layout the window (by 'packing' it) and make it visible.
    	frame.pack();
    	frame.setVisible(true);
    	
    	// This panel can send mouse events to any object that wants to 'listen' to those
    	// events.  
    	this.addMouseListener(this);
    	this.addMouseMotionListener(this);
    }
    
    /**
     * Draws our game.  This function will be called automatically when Java needs to
     * repaint our window.  Use the repaint() function call (on this object) to cause
     * this function to be executed (see actionPerfomed function below).
     * 
     * @param Graphics g the Graphics object to use for drawing
     */
    public void paint (Graphics g)
    {    	
    	// Draw everything.
    	state.drawAll(g);
    }

    /**
     * Calls the setMouseClicked() method.
     */
	public void mousePressed(MouseEvent e) 
	{
		state.setMouseClicked();
	}
	
    public void mouseClicked(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	
	/**
     * This method gets the x and y coordinates of the location that is clicked and dragged.
     */
	public void mouseDragged(MouseEvent e) 
	{
		state.setMouseLocation(e.getX(), e.getY());
	}

	/**
     * This method gets the x and y coordinates of the location that a mouse has entered without
     * any other buttons being pressed.
     */
	public void mouseMoved(MouseEvent e) 
	{
		state.setMouseLocation(e.getX(), e.getY());
	}
}
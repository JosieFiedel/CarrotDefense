/**
 * Path objects represent the path of travel for the adversaries in 
 * the Tower Defense game.  A path is just a series of sequential
 * line segments.  A game object's position on the path is specified
 * by a 'percentage', or amount of the path that has been completed.
 * The start of the path is 0% completed, the end of the path is 100%
 * completed.  (The range is 0.0 to 1.0 as a double.)
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Path 
{
	List<Point> path;	// Point list containing the coordinate values in point objects.

	/**
	 * Constructor--expects to build a path from a list of points.
	 * The points are usually specified in a file (although they could
	 * just be listed in a String).  For convenience, this class expects
	 * a Scanner object that is ready to scan the points from the file
	 * (or String, if debugging). 
	 * 
	 * The constructor uses the Scanner to scan integers.  The format 
	 * of the list of points is:
	 *    # of points (as a single integer, n)
	 *    xcoord0 ycoord0
	 *    xcoord1 ycoord1    
	 *    xcoord2 ycoord2
	 *    ... repeated n times total ...
	 *   
	 * @param s (in a Scanner ready to scan the path description from a File or String)
	 */
	public Path (Scanner s)
	{
		// Create an Array List to store the coordinate pairs.
		path = new ArrayList<Point>();
		
		// The number of coordinates is scanned and stored in variable n. 
		int n = s.nextInt();
		
		// Stores the X and Y coordinates in the list.
		for (int i = 0; i < n; i++)
			path.add(new Point(s.nextInt(), s.nextInt()));
	}
	
    /**
      * Debugging--Draws the current path to the screen (to wherever the graphics object points)
      * using red.
      * 
      * @param g (a graphics object)
      */
	public void drawPath(Graphics g)
	{	
		g.setColor(Color.RED);
		for (int i = 0; i < path.size() - 1; i++)
			g.drawLine(path.get(i).x, path.get(i).y, path.get(i+1).x, path.get(i+1).y);
	}
	
	/** 
	 * Returns the total length of the path. Since the path
	 * is specified using screen coordinates, the length is
	 * in pixel units (by default).
	 * 
	 * @return pathLength (the length of the path)
	 */
	 public double getPathLength ()
	 {
		double pathLength = 0;
		double xLength = 0;
		double yLength = 0;
		for(int i = 0; i < path.size() - 1; i++)
		{
			xLength = path.get(i+1).x - path.get(i).x;
			yLength = path.get(i+1).y - path.get(i).y;
			pathLength = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2)) + pathLength;
		}
		return pathLength;
	 }
	 
	 /**
	  * Given a percentage of distance traveled along the path, this method
	  * returns that location on the path (as a point, or x,y pair).
	  * 
	  * Percentages should only be in [0.0...1.0].  To prevent problems,
	  * this function will return the first point for percentages < 0, and
	  * this function will return the last point for percentages > 1.  
	  * 
	  * @param percentTraveled   percent the distance traveled
	  * @return percentCoords   a Point object containing that x, y location
	  */
	 public Point getPathPosition (double percent)
	 {
		 // Make sure the percentage is in bounds.
		 if (percent < 0.0)
			 percent = 0.0;
		 else if (percent > 1.0)
			 percent = 1.0;
		 
		 // Figure out how many pixels we've traveled down the path.
		 double totalLength = getPathLength();
		 double remainingLength = totalLength * percent;
		 
		 // Identify which segment contains the point of interest.
		 int segment = 0;
		 double segmentLength;  // Initialized below.
		 while (true)  // Loop until we break.
		 {
			 // See if the next segment would be too far.  If so, exit.
			 segmentLength = path.get(segment).distance(path.get(segment+1));
			 if (segmentLength >= remainingLength)
				 break;
			 
			 // Move on to the next segment.  (The length will be set up at
			 // the start of the next loop iteration.)
			 remainingLength -= segmentLength;
			 segment++;
		 }
		 // Use the remaining length to calculate a percentage x along the path.
		 double segPercentage = remainingLength / segmentLength;
		 
		 // Given that you want a point x percentage along a single segment, you
		 // want 100%-x% of the first point's location, and x% of the second.
		 Point p = new Point ((int) ((1-segPercentage)*path.get(segment).x + 
		 (segPercentage)*path.get(segment+1).x), (int) ((1-segPercentage)*path.get(segment).y +
		 (segPercentage)*path.get(segment+1).y));
		     return p;
	 }
	  
	  /**
	   * Returns the distance from the specified point to the nearest point along the path (node point).
	   * @param p
	   * @return shortest
	   */
	  public double distanceToNearestPathNode(Point p)
	  {
		  double shortest = path.get(0).distance(p);
		  for (Point q : path)
		  {
	  		double distance = p.distance(q);
	  		if (distance < shortest)
	  			shortest = distance;
	  	  }
		  return shortest;
	  }
}
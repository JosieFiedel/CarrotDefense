/**
 * The resource loader class is used to help load and build images and paths.
 * If the requested path or image is not stored in the map or path arrays, then
 * the path or image is then stored and loaded in the arrays. If it is already
 * in the arrays, then it is simply loaded. 
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */

package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ResourceLoader 
{
	// Fields
	private HashMap<String, BufferedImage> map;
	private HashMap<String, Path> path;
	static private ResourceLoader instance;
	
	/**
	 * Constructor--builds two HashMap objects to hold the images and paths.
	 */
	private ResourceLoader()
	{
		map = new HashMap<String, BufferedImage>();
		path = new HashMap<String, Path>();
	}

	/**
	 * The getImage method takes in a String representing the filename, and
	 * loads the image tied to the filename from the map. If the image is in
	 * the map, then it is simply returned. If the image is not in the map, then
	 * it is loaded, stored in the map, and returned. An exception is thrown if 
	 * the image cannot be loaded.
	 * 
	 * @param filename
	 * @return image
	 */
	public BufferedImage getImage(String filename)
	{
		BufferedImage image = null;
		try
    	{	
			// If the image is in the map, return it.
	    	if (map.containsKey(filename))
	    	{
	    		BufferedImage storedImage = map.get(filename);
				return storedImage;
	    	}
	    	// If the image is not in the map, store it and return it.
	    	else
	    	{
	    		// Load the image.
	    		ClassLoader loader = this.getClass().getClassLoader();
		    	InputStream is = loader.getResourceAsStream("resources/" + filename);
		    	image = javax.imageio.ImageIO.read(is);
		    	
		    	// Store the image.
		    	map.put(filename, image);
	    	}	
    	}
    	catch (IOException e)
    	{
    		System.out.println("Could not load the image.");
    		System.exit(0);
    	}
		return image;
	}
	
	/**
	 * This getPath method takes in a String parameter representing the filename, and
	 * loads the path tied to the filename from the map. If the path is in the map,
	 * then the path is returned. Otherwise, the path is loaded, stored in the map, and
	 * returned. 
	 * 
	 * @param filename
	 * @return newPath
	 */
	public Path getPath(String filename)
	{
		Path newPath = null;
		// If the path is in the map, return it.
		if (path.containsKey(filename))
		{
			Path storedPath = path.get(filename);
			return storedPath;
		}
		// If the path is not in the map, store it and return it.
		else
		{
			// Load the path.
			ClassLoader loader = this.getClass().getClassLoader();
			Scanner pathScanner = new Scanner(loader.getResourceAsStream("resources/" + filename));
			newPath = new Path(pathScanner);
			
			// Store the path.
			path.put(filename, newPath);
		}
		return newPath;
	}
	
	/**
	 * This static method is used whenever a ResourceLoader object is needed. 
	 * @return instance
	 */
	static public ResourceLoader getLoader()
    {
        if (instance == null)
          instance = new ResourceLoader ();

        return instance;
    }
}
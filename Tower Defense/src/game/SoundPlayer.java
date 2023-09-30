/**
 * The SoundPlayer class is used to load and play the game's audio.
 * 
 * @author Josie Fiedel
 * @version Fall 2021
 */
package game;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class SoundPlayer extends JFrame
{	
	// This constant is needed to get rid of a warning.
	private static final long serialVersionUID = 1L;

	public SoundPlayer()
	{		
	    try 
	    {
	    	// Load the audio.
	    	ClassLoader loader = this.getClass().getClassLoader();
	    	InputStream is = loader.getResourceAsStream("resources/themeSong.wav");
	    	
	         // Open an audio input stream.
	    	AudioInputStream audioIn = AudioSystem.getAudioInputStream(is);
	         // Get a sound clip resource.
	         Clip clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);
	         clip.loop(Clip.LOOP_CONTINUOUSLY);
	         clip.start();
	    } 
	    catch (UnsupportedAudioFileException e) 
	    {
	         e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
	         e.printStackTrace();
	    } 
	    catch (LineUnavailableException e) 
	    {
	         e.printStackTrace();
	    }
	}	
}
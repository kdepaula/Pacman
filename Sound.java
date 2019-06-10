
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound 
{
	

	public static void playSound(String s) 
	{
		InputStream beginning; 
		try 
		{
			File soundFile = new File("sounds/pacman_" + s + ".wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              

	        Clip clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        clip.start();
		} 
		catch (UnsupportedAudioFileException e) 
			{
	         e.printStackTrace();
	      } 
		catch (IOException e) {
	         e.printStackTrace();
	      } 
		catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
}


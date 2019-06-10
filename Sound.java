import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JOptionPane;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound extends Applet 
{
	

	public static void playSound(String s) 
	{
		InputStream beginning; 
		try 
		{
			
			beginning = new FileInputStream(new File("sounds/pacman_" + s + ".wav"));
			AudioStream audios = new AudioStream(beginning);
			AudioPlayer.player.start(audios);
			
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
}


import java.applet.*;
import java.net.*;

public class Sound extends Applet 
{

	public void playSound() 
	{
		try 
		{
			AudioClip clip = Applet.newAudioClip(new URL("file:D:pacman_beginning"));
			clip.play();
		} 
		catch (MalformedURLException murle) 
		{
		}
	}
}


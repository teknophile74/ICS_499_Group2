import java.io.File;
import java.net.URL;

import javax.sound.sampled.*;

public class Audio {

	public Audio(String filePath) throws Exception {
		if(filePath.indexOf("http") != -1)
		{
			try{
				URL url = new URL(filePath);
				Clip clip = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream( url );
				clip.open(ais);
				clip.start();
				//javax.swing.JOptionPane.showMessageDialog(null, "Close to exit!");
			}catch (Exception e)
			{
				throw e;
			}
		}
		else
		{
			try {
				Clip clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(new File(filePath)));
		        clip.start();
		      } catch (Exception e) {
		        throw e;
		      }
		}
	}
} 
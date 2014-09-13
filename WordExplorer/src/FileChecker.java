import java.io.File;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;


public class FileChecker {
	private String filePath;
	
	public FileChecker(String path)
	{
		filePath = path;
	}
	
	public boolean testFilePath()
	{

		if(filePath.indexOf("http") != -1)
		{
			try {
				final URL url = new URL(filePath);
				HttpURLConnection huc = (HttpURLConnection) url.openConnection();
				int responseCode = huc.getResponseCode();
				if (responseCode == 200)
				{
					return true;
				}
			} catch (UnknownHostException uhe) 
			{
				return false;
			} catch (FileNotFoundException fnfe) 
			{
				return false;
			}catch (MalformedURLException murle)
			{
				return false;
			} catch (Exception e) 
			{
				return false;
			}
			return false;
		}
		else
		{
			File f = new File(filePath);
			if(f.exists()) 
			{ 
				return true;
			}
			else
			{
				return false;
			}
		}
	}

}

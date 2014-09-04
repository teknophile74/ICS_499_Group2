


import java.awt.Dimension;

import javax.swing.JApplet;

/**
 * This class acts as the controller between the GUI and the back-end. ALL the
 * other classes should be very loosely coupled and not know who/what is calling
 * them. It is the controllers responsibility to make those calls and control
 * the appropriate responses.
 * 
 * @author Matt
 * 
 */
public class Controller extends JApplet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// The URL of the puzzle config file
	private String URL = null;

	private String puzzleId = null;

	public Controller(String string)
	{
		setURL(string);
	}

	public Controller()
	{
		// TODO Auto-generated constructor stub
	}

	public String getURL()
	{
		return URL;
	}

	public void setURL(String a_url)
	{
		if (a_url.indexOf("=") > -1)
		{
			puzzleId = a_url.substring(a_url.indexOf("=") + 1);
		}
		URL = a_url;
	}

	public String getPuzzleId()
	{
		
		return puzzleId;
	}

	public void setPuzzleId(String an_id)
	{
		puzzleId = an_id;

	}
	
	public void init() {
		/*
		// populates the puzzle collection
		PuzzleCollection pc1 = new PuzzleCollection();
						
		// get the puzzle based on the id
		Puzzle p1 = pc1.getPuzzleByID("004");
		
		setContentPane(new PuzzlePlayerGUI (p1));
		resize(new Dimension(1020, 400));
		*/
		// creating this applet's GUI.
				try
				{
					puzzleId = this.getParameter("id");
					javax.swing.SwingUtilities.invokeAndWait(new Runnable()
					{
						public void run()
						{
							createGUI();
						}
					});
				} catch (Exception e)
				{
					System.err.println("createGUI didn't successfully complete: "
							+ e.toString());
				}
	}
	
	private void createGUI()
	{
		PuzzlePicker pp1 = new PuzzlePicker(getDocumentBase().toString());
		PuzzleCollection collection = new PuzzleCollection();
		Puzzle p1 = collection.getPuzzleByID(pp1.getPuzzleId());
		setContentPane(new PuzzlePlayerGUI (p1));
		setSize(new Dimension(1020, 400));
	}

}

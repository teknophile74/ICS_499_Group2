

/**
 * This class is responsible for processing the URL parameters of the applet and
 * sets the puzzle ID
 * 
 * If there is no puzzle id from the URL, then the ID is set to NULL. in such
 * case, a random puzzle will be presented to the user.
 * 
 * FUTURE: Separation of the logic and responsibility to this class helps in
 * incorporating different ways of selecting the puzzle (based on the date, user
 * id, session id, etc.)
 */
public class PuzzlePicker
{

	private Controller myController = null;
	
	public PuzzlePicker()
	{
		myController = new Controller();
	}

	public PuzzlePicker(String url)
	{
		this();
		myController.setURL(url);
	}

	public PuzzlePicker(Controller controller)
	{
		myController = controller;
	}

	public void setPuzzleId(String an_id)
	{
		myController.setPuzzleId(an_id);
	}

	public String getPuzzleId()
	{
		return myController.getPuzzleId();
	}

}

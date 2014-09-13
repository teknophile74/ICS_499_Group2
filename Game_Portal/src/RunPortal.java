/*
 * Import statement for needed object access
 */
import java.util.ArrayList;

public class RunPortal {

	/**
	 * @author Aaron Burke
	 * RunAssign4 class to start and run the user interface
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Create and populate array of Person and Course objects
		 */
		ArrayList<Games> games = new ArrayList<Games>();
		ArrayList<Language> languages = new ArrayList<Language>();
		games.add(new Games(1, "FourPicsOneWord", "Four Pics - One Word", "Directory Path Location"));
		games.add(new Games(1, "WordSearchPuzzle", "Word Search Puzzle", "Directory Path Location"));
		games.add(new Games(1, "WordExplorer", "Word Explorer", "Directory Path Location"));
		languages.add(new Language(1,"en-us", "English - Uninted States"));
		languages.add(new Language(2,"tel", "Telugu"));
		languages.add(new Language(3,"ce", "Chechen"));
		
		/*
		 * Create new instance of JFrame object and pass in the ArrayLists for processing
		 */
		UserInterface ProgUI3 = new UserInterface(games, languages);
		
		/*
		 * Set UserInterface Frame to visible
		 */
		ProgUI3.setVisible(true);
	} //End main method
} //End RunAssign4 class
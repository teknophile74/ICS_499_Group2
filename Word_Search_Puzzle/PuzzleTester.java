

//import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * A dummy class to test the logic of different classes
 * It does NOT involve GUI
 * It does NOT involve playing of the game through GUI
 * However, it tests the rest of the logic
 * 
 * * Pseudo Code:
 * 1. Process the URL and set the puzzle ID (if exists) through PuzzlePicker
 * 2. Read the puzzle.txt and populate the PuzzleCollection
 * 4. Get the puzzle to be played based on the puzzleID; if null, get a random puzzle
 * 5. Create a PuzzleTracker object based on the Puzzle
 * 6. Build the GUI gird based on puzzle grid height and width
 * 7. Attach the Listeners to the cells to recognize the MouseUp / MouseDown / MouseMove events
 * 8. Populate the grid content (cells and words) based on puzzle and PuzzleConfig
 * 9. Play the game.
 * 10. Upon completion of the game, put out the gameCompleteMsg and give the option to the user to play the next game
 * 11. Present the next puzzle in the sequence (in the same order the puzzles are present in the input file)
 * 12. Terminate the game if the user doesn't wish to continue or kills the browser 
 * 
 * @author srj
 *
 */
public class PuzzleTester {
	
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		
		// TESTS for puzzle 001
		// get 001 puzzle from the puzzlelist
		PuzzlePicker pp1 = new PuzzlePicker("http://www.dropbox.com/PuzzleLauncher.html?id=001");
		
		// populates the puzzle collection
		PuzzleCollection pc1 = new PuzzleCollection();
		
		// get the puzzle based on the id
		Puzzle p1 = pc1.getPuzzleByID(pp1.getPuzzleId());
		
		// create a puzzle tracking object
		PuzzleTracker pt1 = new PuzzleTracker(p1);
		
		// BEAN BAG, IMPRESA, LENS, MANUAL, MATT, NIKON, PRISM, ROLL, SENSIA, TONE
		
		// find these words
		pt1.isWordInTheList("BEANBAG");
		pt1.isWordInTheList("IMPRESA");
		pt1.isWordInTheList("LENS");
		pt1.isWordInTheList("MANUAL");
		pt1.isWordInTheList("MATT");
		pt1.isWordInTheList("NIKON");
		pt1.isWordInTheList("PRISM");
		pt1.isWordInTheList("ROLL");
		pt1.isWordInTheList("SENSIA");
		
		// TONE is not found yet. So, run this test
		System.out.println("is TONE found? = " + pt1.isWordAlreadyFound("TONE"));
		// And the game is NOT complete
		System.out.println("Puzzle 001  Status" + pt1.getGameStatusMsg());
				
		
		// now find the word TONE and rerun the test
		pt1.isWordInTheList("TONE");
		System.out.println("is TONE found? = " + pt1.isWordAlreadyFound("TONE"));
	
		// Now the game is complete
		System.out.println("Puzzle 001 Status" + pt1.getGameStatusMsg());
		
		// TESTS for puzzle 002
				// get 002 puzzle from the puzzlelist
				PuzzlePicker pp2 = new PuzzlePicker("http://www.dropbox.com/PuzzleLauncher.html?id=002");
				
				// populates the puzzle collection
				PuzzleCollection pc2 = new PuzzleCollection();
				
				// get the puzzle based on the id
				Puzzle p2 = pc2.getPuzzleByID(pp2.getPuzzleId());
				
				// create a puzzle tracking object
				PuzzleTracker pt2 = new PuzzleTracker(p2);
				
				// BEAN BALL, IMPRESA, LENS, MANUAL, NATE, NIKON, PRISM, ROLL, SENSIA, TONE
				
				// find these words
				pt2.isWordInTheList("BEANBALL");
				pt2.isWordInTheList("IMPRESA");
				pt2.isWordInTheList("LENS");
				pt2.isWordInTheList("MANUAL");
				pt2.isWordInTheList("NATE");
				pt2.isWordInTheList("NIKON");
				pt2.isWordInTheList("PRISM");
				pt2.isWordInTheList("ROLL");
				pt2.isWordInTheList("SENSIA");
				
				// TONE is not found yet. So, run this test
				System.out.println("is TONE found? = " + pt2.isWordAlreadyFound("TONE"));
				// And the game is NOT complete
				System.out.println("Puzzle 002  Status" + pt2.getGameStatusMsg());
						
				
				// now find the word TONE and rerun the test
				pt2.isWordInTheList("TONE");
				System.out.println("is TONE found? = " + pt2.isWordAlreadyFound("TONE"));
			
				// Now the game is complete
				System.out.println("Puzzle 002 Status" + pt2.getGameStatusMsg());
		
		
		// TESTS for puzzle 003  (simulating multi-byte strings)
		// BEAN BALL, IMPRESA, LENS, MANUAL, NATE, NIKON, PRISM, ROLL, SENSIA, TONE
				PuzzlePicker pp3 = new PuzzlePicker("http://www.dropbox.com/PuzzleLauncher.html?id=003");
				
				// populates the puzzle collection
				PuzzleCollection pc3 = new PuzzleCollection();
				
				// get the puzzle based on the id
				Puzzle p3 = pc3.getPuzzleByID(pp3.getPuzzleId());
				
				// create a puzzle tracking object
				PuzzleTracker pt3 = new PuzzleTracker(p3);
				
				// BEAN BALL, IMPRESA, LENS, MANUAL, NATE, NIKON, PRISM, ROLL, SENSIA, TONE			
				// find these words
				pt3.isWordInTheList("BEANBALL");
				pt3.isWordInTheList("IMPRESA");
				pt3.isWordInTheList("LENS");
				pt3.isWordInTheList("MANUAL");
				pt3.isWordInTheList("NATE");
				pt3.isWordInTheList("NIKON");
				pt3.isWordInTheList("PRISM");
				pt3.isWordInTheList("ROLL");
				pt3.isWordInTheList("SENSIA");
				
				// TONE is not found yet. So, run this test
				System.out.println("is TONE found? = " + pt3.isWordAlreadyFound("TONE"));
				// And the game is NOT complete
				System.out.println("Puzzle 003 Status" + pt3.getGameStatusMsg());
						
				
				// now find the word TONE and rerun the test
				pt3.isWordInTheList("TONE");
				System.out.println("is TONE found? = " + pt3.isWordAlreadyFound("TONE"));
				// Now the game is complete
				System.out.println("Puzzle 003 Status" + pt3.getGameStatusMsg());
				
				//PrintStream out = new PrintStream(System.out, true, "UTF-8");		
				
				//ID: 004
				//Title:  Martin Luther King  (Telugu)
				//Words:  à°®à°¾à°°à±�à°Ÿà°¿à°¨à±� à°²à±‚à°¥à°°à±� à°•à°¿à°‚à°—à±�,  à°®à±‡à°§à°¾à°µà°¿


				// TESTS for puzzle 004
				// get 004 puzzle from the puzzlelist
				PuzzlePicker pp4 = new PuzzlePicker("http://www.dropbox.com/PuzzleLauncher.html?id=004");

				// populates the puzzle collection
				PuzzleCollection pc4 = new PuzzleCollection(PuzzleConfig.defaultFile);
				
				System.out.println("");

				// get the puzzle based on the id
				Puzzle p4 = pc4.getPuzzleByID(pp4.getPuzzleId());
				//System.out.println(p1);
				//PrintStream out4 = new PrintStream(System.out, true, "UTF-8");		
				
				

				// create a puzzle tracking object
				PuzzleTracker pt4 = new PuzzleTracker(p4);
				// find these words
				pt4.isWordInTheList("à°®à°¾à°°à±�à°Ÿà°¿à°¨à±� à°²à±‚à°¥à°°à±� à°•à°¿à°‚à°—à±�");
				pt4.isWordInTheList("à°®à±‡à°§à°¾à°µà°¿");				// Now the game is complete
				System.out.println("Puzzle 004 Status" + pt4.getGameStatusMsg());
				System.out.println(pt4);
				
	}

}

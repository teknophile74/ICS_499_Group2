

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PuzzlePlayer {

	public static void main(String[] args) throws IOException {
		
		
		// get 001 puzzle from the puzzlelist
		//PuzzlePicker pp1 = new PuzzlePicker("http://www.dropbox.com/PuzzleLauncher.html?id=001");
				
		// populates the puzzle collection
		PuzzleCollection pc1 = new PuzzleCollection();
				
		// Logic to retrieve the puzzle ID
		//TODO: You need to read "lastgame.txt"
		
		// get a random puzzle to start the game
		//TODO: if the tracker.txt exists, read it and set the puzzle to it
		// If it doesn't exist, then set the puzzle id to "001"
		Puzzle p1 = pc1.getPuzzleByID("001");
		//Puzzle p1 = pc1.getRandomPuzzle();				
						
		JFrame frame = new JFrame();
		frame.setTitle(PuzzleConfig.gameTitle);
		
		// Set the SILC Peacock Image to the JFrame
		frame.setIconImage(new ImageIcon(p1.getClass().getResource("res/silc.png")).getImage());
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new PuzzlePlayerGUI (p1));
		frame.pack();
		
		// Centers the frame
		frame.setLocationRelativeTo(null);	
		frame.setResizable(false);
		frame.setVisible(true);
	}

}

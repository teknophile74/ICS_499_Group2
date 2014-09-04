import java.util.ArrayList;
/**
 * This class is responsible for tracking the puzzles completed, the times taken for each puzzle;
 * This will also set the puzzle to be launched
 * so that users do not have to start from scratch each time they 2-click the jar file
 * 
 * This file can not be edited by the end users. 
 * So, we need to create object files / binary files to persist the tracker objects
 * 
 * TODO:  in complete implementation
 * 
 * @author srj
 */
@SuppressWarnings("unused")
public class GameTracker {
	
	
	ArrayList<Tracker> trackerCollection;
	private String userId = "none";   // we will NOT use this
	private String lastGameID = null;
	private String newGameID = "001";
	String trackerFileName = PuzzleConfig.trackerFile;
	
	GameTracker()
	{
	}
	
	private class Tracker
	{
           String puzzleId;
           boolean isComplete;
           int hours =0;
           int minutes =0;
           int seconds=0;
           
	}
	
}


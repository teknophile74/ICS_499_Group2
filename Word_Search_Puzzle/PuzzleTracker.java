

import java.util.ArrayList;
import java.util.List;

/**
 * A java class that acts as a wrapper to the Puzzle to track the play progress
 * and completion
 * 
 * @author srj Course: ICS 240 Programming With Elementary Data Structure
 *         Semester: Summer 2014 Instructor: Silva Jasthi Student: James
 *         Lindstrom Assignment: Final Compiler: JDK 1.7 with Eclipse
 * 
 */
public class PuzzleTracker
{

	private Puzzle puzzle; // this is the selected puzzle;
	private List<Boolean> wordListStatus;
	private List<String> wordList;
	private int numberOfWords;
	private int numberOfWordsFound;

	/**
	 * constructor for setting the puzzle to play
	 * 
	 * @param a_puzzle
	 */
	PuzzleTracker(Puzzle a_puzzle)
	{
		puzzle = a_puzzle;
		wordListStatus = new ArrayList<Boolean>(); // by default, all will be
													// FALSE
		// numberOfWords = puzzle.getWordList().size();
		numberOfWordsFound = 0;
		wordList = puzzle.getWordList();
		numberOfWords = wordList.size();
		for (int i = 0; i < numberOfWords; i++)
		{
			wordListStatus.add(false);
		}
	}

	/**
	 * This method checks whether a_selected_word is in the list of the words
	 * When a match is found, wordListStatus is updated at the corresponding
	 * location
	 * 
	 * @param a_selected_word
	 * @return
	 */
	public boolean isWordInTheList(String a_selected_word)
	{
		boolean theReturn = false;
		//if (isWordAlreadyFound(a_selected_word))
		//	return theReturn;
		for (int i = 0; i < numberOfWords; i++)
		{
			String word = wordList.get(i);
			word = word.replaceAll("\\s+", "");
			//System.out.println(word);
			if (word.equalsIgnoreCase(a_selected_word.replaceAll("\\s+", "")))
			{
				theReturn = true;
				// set the word status
				setSelectedWordAsFound(i);
			}
		}
		return theReturn;
	}

	/**
	 * this method sets the a_selected_word as FOUND precondition is
	 * isWordInTheList = true
	 */
	public void setSelectedWordAsFound(int a_index)
	{
		wordListStatus.add(a_index, true);
		numberOfWordsFound++;
		
	}

	/**
	 * This method checks whether a word has already been marked as found
	 * 
	 * @param a_selected_word
	 * @return
	 */
	public boolean isWordAlreadyFound(String a_selected_word)
	{
		for (int i = 0; i < numberOfWords; i++)
		{
			if (wordListStatus.get(i).equals(true))
			{
				if (wordList.get(i).equals(a_selected_word))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks whether all words have been found by the user
	 */
	public boolean areAllWordsFound()
	{
		return (numberOfWords == numberOfWordsFound);
	}

	/**
	 * Checks the game Status
	 */
	public boolean isGameComplete()
	{
		return (numberOfWords == numberOfWordsFound);
	}

	/**
	 * Gets the game completed message only if the game is completed
	 */
	public String getGameStatusMsg()
	{
		if (isGameComplete() == true)
			return (PuzzleConfig.gameCompletedMsg);
		else
			return (PuzzleConfig.gameIncompleteMsg);
	}
}



import java.util.ArrayList;

/**
 * A class representing a single word search puzzle
 * 
 * @author Vitaly Sots
 * 
 *         TBD: Use JasthiGUI.java to generate the rest of the set and get
 *         methods
 * 
 */
public class Puzzle
{

	private String id;
	private String title;
	private ArrayList<String> wordList;
	private String[][] puzzleGrid;
	private int gridWidth; // derived
	private int gridHeight; // derived

	Puzzle(String an_id, String a_title, ArrayList<String> a_word_list,
			String[][] puzzle_grid, int a_gridWidth, int a_gridHeight)
	{
		id = an_id;
		title = a_title;
		wordList = a_word_list;
		puzzleGrid = puzzle_grid;
		gridWidth = a_gridWidth; 
		gridHeight = a_gridHeight; 
	}

	/**
	 * Method for returning the word list
	 * 
	 * @return
	 */
	public ArrayList<String> getWordList()
	{
		return this.wordList;
	}
	
	/**
	 * Set method for the variable id
	 */
	public void setId(String a_id)
	{
		id = a_id;
	}

	/**
	 * Set method for the variable title
	 */
	public void setTitle(String a_title)
	{
		title = a_title;
	}

	/**
	 * Get method for the variable id
	 */
	public String getId( )
	{
		return id;
	}

	/**
	 * Get method for the variable title
	 */
	public String getTitle( )
	{
		return title;
	}
	
	/**
	 * Set method for the variable gridWidth
	 */
	public void setGridWidth(int a_grid_width)
	{
		gridWidth = a_grid_width;
	}

	/**
	 * Set method for the variable gridHeight
	 */
	public void setGridHeight(int a_grid_height)
	{
		gridHeight = a_grid_height;
	}

	/**
	 * Set method for the variable wordList
	 */
	public void setWordList(ArrayList<String> a_word_list)
	{
		wordList = a_word_list;
	}

	/**
	 * Set method for the variable puzzleGrid
	 */
	public void setPuzzleGrid(String[][] a_puzzle_grid)
	{
		puzzleGrid = a_puzzle_grid;
	}

	/**
	 * Get method for the variable gridWidth
	 */
	public int getGridWidth( )
	{
		return gridWidth;
	}

	/**
	 * Get method for the variable gridHeight
	 */
	public int getGridHeight( )
	{
		return gridHeight;
	}

	

	/**
	 * Get method for the variable puzzleGrid
	 */
	public String[][] getPuzzleGrid( )
	{
		return puzzleGrid;
	}

	/** 
	 * Returns the String representation of Student object 
	 */
	 public String toString()
	{
		 String temp = 
			"\nid = " + id +
			"\ntitle = " + title;

		 return temp;
	}

}

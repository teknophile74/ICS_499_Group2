import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Hashtable;
import java.util.Set;
import java.util.Collection;

public class WordCollection 
{
	// this is our main full word collection
	private ArrayList<Word> themedWords;
	
	private ListIterator<Word> listIt;
	private boolean nextWasCalled = true;
	private boolean previousWasCalled = false;
	
	WordCollection(ArrayList<Word> themed_words)	
	{
		themedWords = themed_words;
		setUpIterator();
	}
	/**
	 * Setting up an iterator to navigate the words in the word collection
	 */
	private void setUpIterator()
	{
		listIt = themedWords.listIterator();
	}
	
	/**
	 * Get the size of this word collection
	 * @return the integer that represents the size of the collection
	 */
	public int size()
	{
		return themedWords.size();
	}
	/**
	 * Get the array list of words in this collection
	 * @return word : an array list of Word Objects
	 */
	public ArrayList<Word> getWordList()
	{
		return themedWords;
	}
	/**
	 * Get the next word in the list.  Will return a Word or null if you are at the end of the list and request another word
	 * @return a Word object or Null if you are at index length and request another word.
	 */
	public Word getNextWord()
	{
		try
		{
			if(listIt.hasNext())
			{
				nextWasCalled = true;
				if (previousWasCalled) 
				{
					previousWasCalled = false;
					listIt.next();
				}
				return listIt.next();
			}
			else return null;
		}catch(Exception e)
		{
			return null;
		}
	}
	/***
	 * Get the previous Word in the list.  Will return a null object if no previous can be returned i.e. you are at the beginning
	 * of the list
	 * @return Word or null if at the start of the list (index 0) and request another word
	 */
	public Word getPrevWord()
	{
		try
		{
			previousWasCalled = true;
			if(listIt.hasPrevious())
			{
				if (nextWasCalled) 
				{
					nextWasCalled = false;
					listIt.previous();
				}
				return listIt.previous();
			}
			else return null;
		}catch(Exception e)
		{
			return null;
		}
	}
	/***
	 * Returns the listIterator's hasNext() value
	 * Passthrough method
	 */
	public boolean hasNextWord()
	{
		return listIt.hasNext();
	}
	/***
	 * Get the index of the next word that will be called.
	 * @return
	 */
	public int nextIndex()
	{
		return listIt.nextIndex();
	}
	
}

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;


public class EntireWordCollection 
{
	    // this is the only collection we need to maintain
		// rest of the variables should be in an inner class 
		// that processes the file contents
		private Hashtable<String, ArrayList<Word>> themedWordsHashtable = 
				new Hashtable<String, ArrayList<Word>>();

		private static final String DELIMETER = "\\|";
		private static final int MAX_ITEMS_PER_LINE = 8;
		
		/**
		 * Default constructor calls the first overloaded constructor with the file path
		 */
		EntireWordCollection()
		{
			this("C:\\ics240\\new.txt");
		}

		
		/**
		 * A constructor that loads test data from a file path that is provided
		 * @param a_file_name is the file path to the test data file.
		 */
		EntireWordCollection(String a_file_name)
		{		
			a_file_name = "C:\\ics240\\new.txt";
			try 
			{	
				processInputTextFile(a_file_name);
			} 
			catch (IOException e) 
			{
				System.out.println("There was an error reading or opening the file. Perhaps the file is empty or the path is bad.");
				System.exit(0);
			}
		}
		
		
		/**
	 * Reads lines from a text file one by one and sends them to the addWord method. Catches a WordAdditionException if one is thrown,
	 * and exits the program as per instructions.  It is possible however, to not exit and skip to the next line. This is a one line code change
	 * that involves removing the exit statement. reads UTF-8
	 * @param filename is a string that represents the path of the file to read
	 * @throws IOException is thrown if the file fails to load
	 */
	private void processInputTextFile(String filename) throws IOException 
	{
		String line_read = "";
		BufferedReader reader = new BufferedReader(
			       new InputStreamReader(
			                  new FileInputStream(filename), "UTF-8"));
		int lineNumber = 0;
		while ((line_read = reader.readLine()) != null) 
		{
			lineNumber++;
			try 
			{
				addWord(line_read);
			} 
			catch (WordAdditionException e) 
			{
				System.out.println(e.getMessage() + "Exiting with error code 0 at test data line # " + lineNumber);
				System.exit(0);
			}
		}
		reader.close();
	}

		/**
		 * Adds a word to the collection 
		 * @param a_line is a single line of text with 16 values separated by 15 commas with values thirteen and fourteen being space delimited lists
		 * @throws WordAdditionException is thrown if the file is empty, a line has other than 15 commas, or the file contains duplicate items
		 */
		private void addWord(String a_line) throws WordAdditionException
		{
			if(a_line.equals(""))
			{
				return;
				//throw new WordAdditionException("File is empty. ");
			}
			
			String[] list = a_line.split(DELIMETER);
			List<String> tokens = Arrays.asList(list);
			
		
			// can not handle unstructured data
			// it is required to have delimiters
			if(tokens.size() != MAX_ITEMS_PER_LINE)
			{
				System.out.println("ERROR: Not enough separators | " + a_line);
				return;
			}
			
			String token1 = tokens.get(0).trim(); // wordId
			String token2 = tokens.get(1).trim(); // english
			String token3 = tokens.get(2).trim(); // lang
			String token4 = tokens.get(3).trim(); // english_in_lang
			String token4_1 = " dummy";                  // lang_in_english; derived
			String token5 = tokens.get(4).trim(); // theme
			String token6 = tokens.get(5).trim(); // image
			String token7 = tokens.get(6).trim(); // english sound
			String token8 = tokens.get(7).trim(); // lang sound
			
			//Now create the Word
			Word new_logical_word = new Word(token1,token2,token3,token4,token4_1,
					                 token5,token6,token7,token8);

			
			//System.out.println(new_logical_word);
			
			// add the word to the word collection
			// We have to create multiple ArrayList<Word> into the hashtable
			addWordToThemedHashTable(new_logical_word);
			
			
		}
		
		/*
		 * process the word that got created
		 */
		private void addWordToThemedHashTable(Word new_logical_word)
		{
			String theme = new_logical_word.getTheme();	
			// key already exists
			// so simply add the word to the Array List there
			if (themedWordsHashtable.containsKey(theme))
			{
			//	System.out.println("Adding to the existing theme");
				ArrayList<Word> themed_words = themedWordsHashtable.get(theme);
				themed_words.add(new_logical_word);
				
				return;
			}
			// otherwise create an array list and add the word there
			else {
			//	System.out.println("Creating a new theme");
				ArrayList<Word> themed_words = new ArrayList<Word>();
				themed_words.add(new_logical_word);
				themedWordsHashtable.put(theme, themed_words);
			}
		}
		
		/**
		 * For getting the names of themes in English
		 */
		public ArrayList<String> getUniqueThemesInEnglish( )
		{
		    Set<String> keys = themedWordsHashtable.keySet();
		    ArrayList list = new ArrayList(keys);     
		    Collections.sort(list);
		    return list;
		}

		/**
		 * For getting the names of themes in English
		 */
		public ArrayList<Word> getWordCollectionForEnglishTheme(String a_theme)
		{
			ArrayList<Word> x = themedWordsHashtable.get(a_theme);
		    return x;
		}

		public String toString( )
		{
			String[] args = {"E_inE","E_in_L","L_in_L","L_in_E" };
			String divider = "------------------------------------------------------------------------------------------------------------------\n";
			String answer = String.format( "%-25s %-25s %-25s %-25s %n", args[0] , args[1] , args[2], args[3]) + divider;
		
			Collection x = themedWordsHashtable.values();
			return "";
		}
		
		/*
		 * Test routine for WordCollection
		 */
		public static void main(String[] args)
		{
			EntireWordCollection x = new EntireWordCollection();
			//System.out.println(x);
			
			System.out.println(x.getUniqueThemesInEnglish());
		}
}

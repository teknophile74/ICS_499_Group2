//********************************************************************** 
// FourPixCreatorCollection.java, Yuxiang Wang, Gj4912oy@metrostate.edu, Midterm, July .9 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * The class that handles the collection of the four pix's.
 */
public class FourPixCreatorCollection {

    /** The ArrayList collection of the four pix's. */
    private ArrayList<FourPixCreator> fourPixCollection;

    /**
     * Instantiates a new four pix creator collection.
     * 
     * @param path the file path of the text file
     */
    public FourPixCreatorCollection(String path, Transliteration transliteration){
	fourPixCollection = readFromTextFile(path, transliteration);
    }

    /**
     * Gets a random FourPixCreator and return its solution word.
     * 
     * @return a random solution word from the collection
     */
    public String getRandomWord() {
	// get a random FourPixCreator and return its solution word
	return getRandomFourPixCreator().getSolutionWord();
    }

    /**
     * Gets a random FourPixCreator.
     * 
     * @return a random FourPixCreator.
     */
    public FourPixCreator getRandomFourPixCreator() {
	// get random integer from 0 to the size of the collection
	int n = new Random().nextInt(fourPixCollection.size());

	// return the nth object from the collection
	return fourPixCollection.get(n);
    }

    /**
     * Gets the size of the collection.
     * 
     * @return the size of the collection
     */
    public int getCollectionSize() {
	return fourPixCollection.size();
    }

    /**
     * Reads input from text file and returns the array list
     * of the four pixs read. This method assumes that the
     * input text file is written in the correct format. 
     * (5 lines per word set, 1st line is the solution word,
     * each word set is divided by dashes, etc...)
     *
     * @param path the file path of the text file
     * @return an ArrayList of the four pix's from the text file
     */
    private static ArrayList<FourPixCreator> readFromTextFile(String path, Transliteration transliteration) {
	// temporary collection
	ArrayList<FourPixCreator> fourPixs = new ArrayList<>();

	try {
	    // instantiate a reader for the file
	    InputStream is = FourPixCreatorCollection.class.getResourceAsStream(path);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	    String line;

	    // read each line in the text file
	    while ((line=br.readLine())!=null) {
		// get the word
		String solutionWord = line.split(":")[1].trim().toUpperCase();
		String solutionLetters[] = transliteration.parseLangStringToLogicalCharacters(solutionWord);

		// if the length of the solution word is greater than 
		// the maximum characters available for the game,
		// cut off the excess letters from the solution word
		int maxCharacters = Configuration.NUMBER_OF_FILLER_ROWS * Configuration.NUMBER_OF_TILES_PER_ROW;
		int actualLength = solutionLetters.length;
		if (actualLength > maxCharacters){
		    String[] newSolutionLetters = new String[maxCharacters];
		    System.arraycopy(solutionLetters, 0, solutionLetters, 0, maxCharacters);
		    solutionLetters = newSolutionLetters;
		}

		// get pictures and their name and url
		// 0th index: word
		// 1st index: url
		String[] picInfo1 = readPicInfo(br.readLine());
		String[] picInfo2 = readPicInfo(br.readLine());
		String[] picInfo3 = readPicInfo(br.readLine());
		String[] picInfo4 = readPicInfo(br.readLine());

		// call the horizontal line so that 
		// the next line will be the solution word of the
		// next word set
		br.readLine();

		// create the FourPixCreator
		FourPixCreator fpc = new FourPixCreator();

		fpc.setSolutionLetters(solutionLetters);

		fpc.setWord1(picInfo1[0]);
		fpc.setWord2(picInfo2[0]);
		fpc.setWord3(picInfo3[0]);
		fpc.setWord4(picInfo4[0]);

		fpc.setURL1(picInfo1[1]);
		fpc.setURL2(picInfo2[1]);
		fpc.setURL3(picInfo3[1]);
		fpc.setURL4(picInfo4[1]);

		// add the four pix to the collection
		fourPixs.add(fpc);
	    }

	    br.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// return the generated temporary collection
	return fourPixs;
    }

    /**
     * Splits the line to the pic word and url.
     * 
     * @param picInfoLine the line where the word and url is written
     * @return an array where the 0th index is the word,
     * and the 1st index is the url
     */
    private static String[] readPicInfo(String picInfoLine) {
	String[] picInfo = picInfoLine.split(",");
	picInfo[0] = picInfo[0].trim();
	picInfo[1] = picInfo[1].trim();
	return picInfo;
    }
}

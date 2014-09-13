
//*******1*********2*********3*********4*********5*********6*********7*********8
// Transliteration.java, Transliteration, 02/11/2012

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * Class that uses transliteration to convert between the Telugu alphabet and
 * the English alphabet.
 * 
 */
public class Transliteration {
    // Contains the null character.
    /** The Constant NULL_CHARACTER. */
    private final static char NULL_CHARACTER = '\u0000';

    // Contains the Unicode for the halant character
    // which is used to combine consonants.
    // (this is for consonant blends)
    /** The halant. */
    private Letter halant;

    // Contains the default vowel.
    /** The default vowel. */
    private Letter defaultVowel;

    // Contains the alphabet.
    /** The alphabet. */
    private ArrayList<Letter> alphabet;
    
    // Contains the vowel letters as strings
    /** The vowel strings. */
    private ArrayList<String> vowelStrings;
    
    // Contains the consonant letters as strings
    /** The consonant strings. */
    private ArrayList<String> consonantStrings;
    
    // Contains the all letters as strings
    /** The all strings. */
    private ArrayList<String> allStrings;

    // Contains a list of equivalent Transliterations.
    // Not being leveraged now; can be ignored
    // private ArrayList<Letter> equivalentList;

    // Contains the largest size of a transliterate character.
    /** The max transliterate size. */
    private int maxTransliterateSize = 0;

    // Contains the name of the recommended font to be used by client.
    // Telugu.txt unicode configuration file has the Font name in the first line
    /** The recommended font. */
    private String recommendedFont = "";

    // turn this to false, if you don't want the clutter of system.out.printlns
    /** The debug. */
    boolean debug = false;

    // Hashtable of the filler characters
    /** The filler char table. */
    Hashtable<String, ArrayList<String>> fillerCharTable = new Hashtable<String, ArrayList<String>>();

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
	Transliteration telugu = new Transliteration("Telugu.txt");
	String s1 = "è„¿æŽ³éˆ¥âˆ¶çŠ…æ‰³ï¿½è„¿æŽ³å¨„è„¿å�¤é”ŸçŸ«çŠ…å¥¥æ‡ŠçŠ…å¥¥îž¡çŠ…å¥¥îŸ‹çŠ…æ†‹æ‹·è„¿æŽ³èµ‚è„¿å�¤é”ŸçŸ«çŠ…å¥¥è¯¥çŠ…æ†‹æ‹· "; // è„¿æŽ³éˆ¥âˆ¶çŠ…æ‰³ï¿½ .	
							     // è„¿æŽ³å¨„è„¿å�¤é”ŸçŸ«çŠ…å¥¥ï¿½. è„¿æŽ³æ�‚.
							     // è„¿æŽ³ç¯“è„¿å�¤é”Ÿï¿½
							     // è„¿æŽ³èµ‚è„¿å�¤é”ŸçŸ«çŠ…å¥¥è¯¥çŠ…æ†‹æ‹·
	System.out.println("Number of Unicode Characters = " + s1.length());
	System.out.println("Number of Ulogical Characters = "
		+ telugu.getLengthOfLangPhrase(s1));

	String s2 = "è„¿æŽ³é™‹è„¿å�¤é”ŸçŸ«çŠ…å¥¥æ‡ŠçŠ…æ‰�ï¿½è„¿æŽ³éˆ¥æ–†çŠ…æ†‹æ‹·è„¿æŽ³æŽ³è„¿æŽ³æˆ®è„¿æŽ³åº�è„¿æŽ³é©´è„¿æŽ³éˆ¥æ¯­çŠ…æ‰³ï¿½è„¿å�¤é”Ÿï¿½"; // è„¿æŽ³é™‹è„¿å�¤é”ŸçŸ«çŠ…å¥¥æ‡ŠçŠ…æ‰�ï¿½
								// .
								// è„¿æŽ³éˆ¥æ–†çŠ…æ†‹æ‹·è„¿æŽ³æŽ³è„¿æŽ³æˆ®
								// . è„¿æŽ³åº�è„¿æŽ³é©´è„¿æŽ³éˆ¥ï¿½.
								// è„¿æŽ³éˆ¥æ–†çŠ…æ†‹æ‹·
	System.out.println("Number of Unicode Characters = " + s2.length());
	System.out.println("Number of Ulogical Characters = "
		+ telugu.getLengthOfLangPhrase(s2));

	String s3 = "è„¿æŽ³éˆ¥çŠ†çŠ…å¥¥îŸ‹çŠ…æ‰³ï¿½è„¿æŽ³å¨„è„¿æŽ³åº�è„¿æŽ³éˆ¥â’šçŠ…å¥¥æ‡ŠçŠ…æ‰³ï¿½è„¿æŽ³å¨„è„¿æŽ³åº�è„¿å�¤é”Ÿï¿½"; // "è„¿æŽ³éˆ¥ï¿½. è„¿æŽ³ç¯“è„¿æŽ³éˆ¥ï¿½. è„¿æŽ³å¨„ . è„¿æŽ³åº�. è„¿æŽ³éˆ¥ï¿½ è„¿æŽ³æŽ³è„¿æŽ³éˆ¥ï¿½. è„¿æŽ³å¨„ .è„¿æŽ³åº�è„¿å�¤é”Ÿï¿½";
	System.out.println("Number of Unicode Characters = " + s3.length());
	System.out.println("Number of Ulogical Characters = "
		+ telugu.getLengthOfLangPhrase(s3));

    }

    /**
     * Creates a Transliteration object and loads transliteration data from the
     * specified file. Instances of the File class are immutable and there is
     * not a default constructor because the transliteration data must be loaded
     * from a file.
     *
     * @param filename            string containing the filename of the file containing the
     *            transliteration data.
     */
    public Transliteration(String filename) {
	loadTransliterationData(filename);
	initAlphabet();
    }

    /*
     * Loads the transliteration data from the specified file.
     * 
     * @param filename string containing the filename of the file containing the
     * transliteration data.
     * 
     * @throws FileNotFoundException If the file does not exist, is a directory
     * rather than a regular file, or for some other reason cannot be opened for
     * reading.
     */
    /**
     * Load transliteration data.
     *
     * @param filename the filename
     */
    private void loadTransliterationData(String filename) {
	// Used to store the line being read.
	String[] get_line;

	// Used to read the file.
	Scanner input = new Scanner(getClass().getResourceAsStream(filename));

	alphabet = new ArrayList<Letter>();

	// Reads file line by line.
	while (input.hasNextLine()) {
	    get_line = input.nextLine().trim().split("\\s+");

	    // Checks that it isn't empty and stores line.
	    if (get_line.length > 0) {

		// Stores the recommended font name.
		if (get_line[0].toLowerCase().equals("font")) {
		    recommendedFont = get_line[1];
		    for (int i = 2; i < get_line.length; i++) {
			recommendedFont += " " + get_line[i];
		    }
		}

		// Adds vowel to alphabet
		if (get_line[0].toLowerCase().equals("vowel")) {
		    if (get_line.length == 3) {
			alphabet.add(new Letter(Letter.VOWEL, get_line[1],
				hexToChar(get_line[2])));
			maxTransliterateSize = Math.max(maxTransliterateSize,
				get_line[1].length());
			// Stores default vowel
			if (defaultVowel == null) {
			    defaultVowel = new Letter(Letter.VOWEL,
				    get_line[1], hexToChar(get_line[2]));
			}
		    } else if (get_line.length == 4) {
			alphabet.add(new Letter(Letter.VOWEL, get_line[1],
				hexToChar(get_line[2]), hexToChar(get_line[3])));
			maxTransliterateSize = Math.max(maxTransliterateSize,
				get_line[1].length());
		    }
		}

		// Adds consonant to alphabet
		else if (get_line[0].toLowerCase().equals("consonant")
			&& get_line.length == 3) {
		    alphabet.add(new Letter(Letter.CONSONANT, get_line[1],
			    hexToChar(get_line[2])));
		    maxTransliterateSize = Math.max(maxTransliterateSize,
			    get_line[1].length());
		}

		// Stores halant
		else if (get_line[0].toLowerCase().equals("halant")
			&& get_line.length == 3 && halant == null) {
		    halant = new Letter(Letter.HALANT, get_line[1],
			    hexToChar(get_line[2]));
		    alphabet.add(halant);
		    maxTransliterateSize = Math.max(maxTransliterateSize,
			    get_line[1].length());
		}

		// Adds number to alphabet
		else if (get_line[0].toLowerCase().equals("number")
			&& get_line.length == 3) {
		    alphabet.add(new Letter(Letter.NUMBER, get_line[1],
			    hexToChar(get_line[2])));
		    maxTransliterateSize = Math.max(maxTransliterateSize,
			    get_line[1].length());
		}

		// Adds other characters to alphabet
		else if (get_line[0].toLowerCase().equals("other")
			&& get_line.length == 3) {
		    alphabet.add(new Letter(Letter.OTHER, get_line[1],
			    hexToChar(get_line[2])));
		    maxTransliterateSize = Math.max(maxTransliterateSize,
			    get_line[1].length());
		}

		// Adds equivalents to equivalent list
		else if (get_line[0].toLowerCase().equals("equivalent")
			&& get_line.length == 3) {
		    // To DO
		}
	    }
	}
	// Close file
	input.close();
    }

    /**
     * Converts the specified hex number to a char.
     * 
     * @param hex
     *            String containing a hex number.
     * @return A char containing the specified hex number.
     */
    private char hexToChar(String hex) {
	return (char) Integer.decode("#" + hex).intValue();
    }

    /**
     * Transliterate english phrase.
     *
     * @param phrase the phrase
     * @return the string
     */
    public String transliterateEnglishPhrase(String phrase) {
	// Used to hold the return value.
	StringBuilder result = new StringBuilder(phrase.length());

	// Used to hold the previous end location while searching the phrase.
	int previous_position = 0;

	// Used to find each word in the phrase.
	Matcher phrase_search = Pattern.compile("[a-zA-Z0-9~]+")
		.matcher(phrase);

	while (phrase_search.find()) {
	    result.append(phrase.substring(previous_position,
		    phrase_search.start()));
	    result.append(transliterateEnglishToken(phrase.substring(
		    phrase_search.start(), phrase_search.end())));
	    previous_position = phrase_search.end();
	}

	result.append(phrase.substring(previous_position));

	return result.toString();
    }

    /**
     * For transliterating English characters - as found in the configuration
     * file - to Language (Telugu) Characters.
     *
     * @param word the word
     * @return the string
     */
    public String transliterateEnglishToken(String word) {
	// Used to hold each transliterated letter after it has been matched.
	LinkedList<Letter> transliterated_letters = new LinkedList<Letter>();
	// Contains the index of the the matching character in alphabet.
	int match = -1;
	// Used to create and store the return value.
	StringBuilder result = new StringBuilder();
	// Used to get each Letter.
	Letter get_letter;

	Collections.sort(alphabet);

	// Iterates through each letter in word.
	for (int begin = 0; begin < word.length(); begin++) {
	    // Iterates through each possible transliterate character in
	    // word_string.
	    for (int end = begin + maxTransliterateSize; end > begin; end--) {
		if (end <= word.length()) {

		    // Searches for a match in the alphabet.
		    match = Collections.binarySearch(alphabet,
			    new Letter(word.substring(begin, end)));
		    if (match >= 0) {
			begin = end - 1;
			transliterated_letters.addLast(alphabet.get(match));
		    }
		}
	    }

	    // Match not found so word is not valid.
	    if (match < 0) {
		return word;
	    }
	}

	while (!transliterated_letters.isEmpty()) {
	    get_letter = transliterated_letters.removeFirst();
	    result.append(get_letter.getUnicode());

	    if (get_letter.getType() == Letter.CONSONANT
		    && !transliterated_letters.isEmpty()) {
		// Consonant followed by a vowel
		if (transliterated_letters.peekFirst().getType() == Letter.VOWEL) {
		    get_letter = transliterated_letters.removeFirst();
		    if (get_letter.getDependentUnicode() != NULL_CHARACTER) {
			result.append(get_letter.getDependentUnicode());
		    }
		}
		// Consonant followed by a consonant
		else if (transliterated_letters.peekFirst().getType() == Letter.CONSONANT) {
		    result.append(halant.getUnicode());
		}
	    }
	}

	return result.toString();
    }

    /**
     * Converts the specified Telugu script to a String containing the
     * transliteration.
     * 
     * @param lang_word_string
     *            A String containing Telugu script.
     * @return A String containing the transliteration of the specified Telugu
     *         script.
     */
    public String transliterateLangPhrase(String lang_word_string) {
	// Contains the index of the the matching character in alphabet.
	int match;
	int vowel_match;

	// Used to create and store the return value.
	StringBuilder result = new StringBuilder();

	Collections.sort(alphabet, new LetterCompareUnicode());

	// Iterates through each character in the Telugu script.
	for (int i = 0; i < lang_word_string.length(); i++) {
	    // Searches for a match in the alphabet.
	    match = Collections.binarySearch(alphabet, new Letter(
		    lang_word_string.charAt(i)), new LetterCompareUnicode());

	    // Match found
	    if (match > 0 && alphabet.get(match).getType() != Letter.HALANT) {
		result.append(alphabet.get(match).getTransliteration());
		// Check if match is consonant.
		if (alphabet.get(match).getType() == Letter.CONSONANT) {
		    // Check if end of string.
		    if (i + 1 < lang_word_string.length()) {
			// check if next character is not a halant.
			if (lang_word_string.charAt(i + 1) != halant
				.getUnicode()) {
			    // check if next character is not a vowel.
			    vowel_match = searchDependentUnicode(lang_word_string
				    .charAt(i + 1));
			    if (vowel_match < 0
				    || alphabet.get(vowel_match).getType() != Letter.VOWEL) {
				result.append(defaultVowel.getTransliteration());
			    }
			}
		    }
		    // End of string so no halant so add 'a'.
		    else {
			result.append(defaultVowel.getTransliteration());
		    }
		}
	    }
	    // Halant found
	    else if (match > 0
		    && alphabet.get(match).getType() == Letter.HALANT) {
		// if next character is not a consonant than than add halant
	    }
	    // Not found
	    else {

		// Search for match Unicode of the dependent form of the Telugu
		// letter
		match = searchDependentUnicode(lang_word_string.charAt(i));

		// Dependent form found
		if (match > 0) {
		    result.append(alphabet.get(match).getTransliteration());
		}
		// Other character
		else {
		    result.append(lang_word_string.charAt(i));
		}
	    }
	}

	return result.toString();
    }

    /**
     * Searches the alphabet for Dependent Unicode that match the specified
     * Unicode value.
     * 
     * @param unicode
     *            A Unicode value that is being searched for.
     * @return Returns the index if a match is found or -1 if no match is found.
     */
    private int searchDependentUnicode(char unicode) {
	// Iterates through each letter in the alphabet searching for a match.
	for (int i = 0; i < alphabet.size(); i++) {
	    if (alphabet.get(i).getDependentUnicode() == unicode) {
		return i;
	    }
	}

	// No match found.
	return -1;
    }

    /**
     * Parses the specified string of characters in a native script into a list
     * of the characters.
     * 
     * @param text
     *            A String in a native script to be parsed into individual
     *            characters.
     * @return Returns a LinkedList containing a list of the characters parsed
     *         from the specified string.
     */

    public String[] parseLangStringToLogicalCharacters(String text) {

	// Used to create the list of the characters parsed from the specified
	// string.
	LinkedList<String> logical_char_list = new LinkedList<String>();

	// Used to create the return value;
	// This is used to simply convert the LinkedList to Array
	String[] result;

	// Check for valid string.
	if (text == null || text.isEmpty()) {
	    return new String[0];
	}

	// Contains the index of the the matching character in alphabet.
	int match;
	int vowel_match;
	boolean add_to_previous = false;
	String str = null;
	Collections.sort(alphabet, new LetterCompareUnicode());

	// Iterates through each character in the text.
	for (int i = 0; i < text.length(); i++) {

	    if (i != 0) {// Look for M, M can not be the 1'st letter.
		match = Collections.binarySearch(alphabet,
			new Letter(text.charAt(i)), new LetterCompareUnicode());
		if (match == 1) {
		    str = logical_char_list.removeLast()
			    + Character.toString(text.charAt(i));
		    logical_char_list.addLast(str);
		    continue;
		}
	    }
	    if (add_to_previous) {
		str = logical_char_list.removeLast()
			+ Character.toString(text.charAt(i));
		logical_char_list.addLast(str);
	    } else {
		logical_char_list.addLast(Character.toString(text.charAt(i)));
	    }

	    // Searches for a match in the alphabet.
	    match = Collections.binarySearch(alphabet,
		    new Letter(text.charAt(i)), new LetterCompareUnicode());
	    // Match found
	    if (match > 0 && i + 1 < text.length()) {
		switch (alphabet.get(match).getType()) {

		case Letter.HALANT:
		    // Check if next letter is a consonant.
		    match = Collections.binarySearch(alphabet,
			    new Letter(text.charAt(i + 1)),
			    new LetterCompareUnicode());
		    if (match > 0
			    && alphabet.get(match).getType() == Letter.CONSONANT) {
			add_to_previous = true;
		    } else {
			add_to_previous = false;
		    }
		    break;
		case Letter.CONSONANT:
		    // Check if next letter is a dependent vowel or a halant.
		    match = Collections.binarySearch(alphabet,
			    new Letter(text.charAt(i + 1)),
			    new LetterCompareUnicode());
		    vowel_match = searchDependentUnicode(text.charAt(i + 1));
		    if ((match > 0 && alphabet.get(match).getType() == Letter.HALANT)
			    || (vowel_match > 0)) {
			add_to_previous = true;
		    } else {
			add_to_previous = false;
		    }
		    break;
		default:
		    add_to_previous = false;
		}
	    } else {
		add_to_previous = false;
	    }
	}
	// Convert result from LinkedList to an array.
	result = new String[logical_char_list.size()];
	for (int i = 0; i < result.length; i++) {
	    result[i] = logical_char_list.removeFirst();
	}
	return result;
    }

    /**
     * Gets the alphabet.
     *
     * @return the alphabet
     */
    public ArrayList<Letter> getAlphabet() {
	return new ArrayList<Letter>(alphabet);
    }

    /**
     * Gets the halant.
     *
     * @return the halant
     */
    public Letter getHalant() {
	return halant;
    }

    /**
     * Returns the name of recommended Font.
     * 
     * @return Returns a string containing the name of recommended Font.
     */
    public String getRecommendedFontName() {
	return recommendedFont;
    }

    /*
     * An Internal private class Used to compare two Letters based on their
     * Unicode values
     */
    /**
     * The Class LetterCompareUnicode.
     */
    private class LetterCompareUnicode implements Comparator<Letter> {

	/*
	 * Compares the Unicode value of the two specified Letters for order.
	 * 
	 * Compares the Unicode value of the two specified Letters for order.
	 * Returns a negative integer, zero, or a positive integer as the
	 * Unicode value of the first Letter is less than, equal to, or greater
	 * than the Unicode value of the second Letter.
	 * 
	 * @param compare_1 The first Letter to be compared.
	 * 
	 * @param compare_2 The second Letter to be compared.
	 * 
	 * @return a negative integer, zero, or a positive integer as the
	 * Unicode value of the first Letter is less than, equal to, or greater
	 * than the Unicode value of the second Letter.
	 */
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Letter compare_1, Letter compare_2) {
	    return (int) (compare_1.getUnicode() - compare_2.getUnicode());
	}
    }

    /**
     * This method returns the length of logical characters in a Telugu String.
     *
     * @param phrase            - The phrase to count characters for.
     * @return The number of logical characters in the telugu string.
     */
    public int getLengthOfLangPhrase(String phrase) {
	String[] logical_char_array = parseLangStringToLogicalCharacters(phrase);
	return logical_char_array.length;
    }

    /*
     * This method creates a filler table / hashtable based on the logical
     * character type key would be: type of logical character ANY LETTER VOWELS
     * CONSONANTS VOWEL_MIXERS CONSONANT_BLENDS value will be an arraylist of
     * logical characters supplied by the ArrayList logicalCharacters
     */
    /**
     * Creates the filler table.
     *
     * @param logicalCharacters the logical characters
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createFillerTable(ArrayList<String> logicalCharacters) {

	fillerCharTable.put("ANY_LETTER", new ArrayList<String>());

	for (int i = 0; i < logicalCharacters.size(); i++) {
	    // get the logical character from the input
	    String x = logicalCharacters.get(i);
	    ((ArrayList) fillerCharTable.get("ANY_LETTER")).add(x);
	}

	/*
	 * ====== BEGIN: temporarily disable all of this
	 * 
	 * // Create the fillerTable (empty Hashtable); // ANYLETTER can be
	 * constructed dynamically, // but for now, go brute force
	 * fillerCharTable.put("ANY_LETTER", new ArrayList<String>());
	 * fillerCharTable.put("VOWEL", new ArrayList<String>());
	 * fillerCharTable.put("CONSONANT", new ArrayList<String>());
	 * fillerCharTable.put("VOWEL_MIXER", new ArrayList<String>());
	 * fillerCharTable.put("CONSONANT_BLEND", new ArrayList<String>());
	 * fillerCharTable.put("PARAGRAPH", new ArrayList<String>());
	 * fillerCharTable.put("WORD_LIST", new ArrayList<String>());
	 * fillerCharTable.put("NUMBER", new ArrayList<String>());
	 * 
	 * // for each logical character, determine its type for (int i = 0; i <
	 * logicalCharacters.size(); i++) { // get the logical character from
	 * the input String x = logicalCharacters.get(i);
	 * 
	 * if (debug) { System.out.print("Logical Character = " + x);
	 * System.out.print("; Code Point Length = " + x.length() +
	 * "; Type = "); }
	 * 
	 * // our logic is based on the number of code points present // and
	 * their pattern int code_point_length = x.length();
	 * 
	 * Collections.sort(alphabet, new LetterCompareUnicode()); int match =
	 * 0;
	 * 
	 * switch (code_point_length) { // can be vowel or consonant case 1:
	 * match = Collections.binarySearch(alphabet, new Letter(x.charAt(0)),
	 * new LetterCompareUnicode()); if (match > 0) { switch
	 * (alphabet.get(match).getType()) { case Letter.VOWEL: if (debug)
	 * System.out.println("VOWEL"); ((ArrayList)
	 * fillerCharTable.get("VOWEL")).add(x); ((ArrayList)
	 * fillerCharTable.get("ANY_LETTER")).add(x); break;
	 * 
	 * case Letter.CONSONANT: if (debug) System.out.println("CONSONANT");
	 * ((ArrayList) fillerCharTable.get("CONSONANT")).add(x); ((ArrayList)
	 * fillerCharTable.get("ANY_LETTER")).add(x); break; } } // if the match
	 * is NOT found else if (debug) System.out.println("Unknown"); break; //
	 * case 2: vowel mixer (including consonant + halant) (consonat + //
	 * sunna) (vowel + sunna) case 2: if (debug)
	 * System.out.println("VOWEL_MIXER"); ((ArrayList)
	 * fillerCharTable.get("VOWEL_MIXER")).add(x); ((ArrayList)
	 * fillerCharTable.get("ANY_LETTER")).add(x); break; // case 3: if 2nd
	 * code point is HALANT, it is consonant blend // otherwise a VOWEL
	 * MIXER case 3: match = Collections.binarySearch(alphabet, new
	 * Letter(x.charAt(1)), new LetterCompareUnicode()); if ((match > 0) &&
	 * (alphabet.get(match).getType() == Letter.HALANT)) { if (debug)
	 * System.out.println("CONSONANT_BLEND"); ((ArrayList)
	 * fillerCharTable.get("CONSONANT_BLEND")).add(x); ((ArrayList)
	 * fillerCharTable.get("ANY_LETTER")).add(x); } else { if (debug)
	 * System.out.println("VOWEL_MIXER"); ((ArrayList)
	 * fillerCharTable.get("VOWEL_MIXER")).add(x); ((ArrayList)
	 * fillerCharTable.get("ANY_LETTER")).add(x); } break; // case 4:
	 * Anything 4 and up will always be consonant blend default: if (debug)
	 * System.out.println("Consonant Blend"); ((ArrayList)
	 * fillerCharTable.get("CONSONANT_BLEND")).add(x); ((ArrayList)
	 * fillerCharTable.get("ANY_LETTER")).add(x); break; } } // end for loop
	 * 
	 * ====== BEGIN: temporarily disable all of this
	 */

    } // end create fillterTable

    /**
     * Gets the filler char table.
     *
     * @param type the type
     * @return the filler char table
     */
    public ArrayList<String> getFillerCharTable(int type) {
	switch (type) {
	case 4:
	    return fillerCharTable.get("VOWEL_MIXER");
	case 5:
	    return fillerCharTable.get("CONSONANT_BLEND");
	default:
	    return fillerCharTable.get("ANY_LETTER");
	}

    }
    
    /**
     * Initializes the ArrayList of vowel strings, consonant strings, and all strings.
     */
    private void initAlphabet() {
	consonantStrings = new ArrayList<String>();
	vowelStrings = new ArrayList<String>();
	allStrings = new ArrayList<String>();
	
	for (Letter l : alphabet) {
	    
	    if (l.getType() == Letter.CONSONANT) {
		// add string to consonant strings if consonant
		consonantStrings.add(transliterateEnglishToken(l.getTransliteration()));
	    } else if (l.getType() == Letter.VOWEL) {
		// add string to vowel strings if vowel
		vowelStrings.add(transliterateEnglishToken(l.getTransliteration()));
	    }
	    
	    // add string to all strings
	    allStrings.add(transliterateEnglishToken(l.getTransliteration()));
	}
    }

    /**
     * Gets the vowel strings.
     *
     * @return the vowel strings
     */
    public ArrayList<String> getVowelStrings() {
        return vowelStrings;
    }

    /**
     * Gets the consonant strings.
     *
     * @return the consonant strings
     */
    public ArrayList<String> getConsonantStrings() {
        return consonantStrings;
    }

    /**
     * Gets the all strings.
     *
     * @return the all strings
     */
    public ArrayList<String> getAllStrings() {
        return allStrings;
    }

}

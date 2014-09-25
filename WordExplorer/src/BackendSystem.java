/*	ICS499
 *  Group 2
 *  Main.java
 *  This is the Main program the starts program and builds GUI
 *  Please make sure jl1.0.1.jar is included in project build path
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BackendSystem {

	// Declares Global Variables
	protected String wordId;
	protected String englishInEnglish;
	protected String langInLang;
	protected String englishInLang;
	protected String langInEnglish;
	protected List<String> themesInEnglish;
	protected String themesInEnglishString;
	protected List<String> themesInLang;
	protected String themesInLangString;
	protected String imageURI;
	protected String infoURI;
	protected String soundURIOfEnglish;
	protected String soundURIOfLang;
	protected WordList wordList;
	protected WordList themedList;
	protected WordList tempList;
	protected WordList tempList2;
	protected int hasLines;
	protected int themedSize;
	protected int countLines;
	protected int wordsAdded;
	protected File file;
	protected Scanner scanner1;
	protected Scanner scanner2;
	protected Scanner scanner3;
	protected Scanner scanner4;
	protected Word word1;
	protected Word tempWord;
	protected String[] rows;
	private static final BackendSystem INSTANCE = new BackendSystem();

	private BackendSystem() {

	}

	//Creates Word and adds to wordList
	public void createWord(int number) {

		String tempWordId = englishInEnglish + langInLang;

		if (wordsAdded == 0) {
			tempWord = new Word(englishInEnglish, langInLang, englishInLang,
					langInEnglish, themesInEnglish, themesInLang, imageURI,
					infoURI, soundURIOfEnglish, soundURIOfLang);
			wordList.addWord(tempWord);
			wordsAdded++;
		} else {

			boolean isDup = wordList.searchWordIsDup(tempWordId);
			if (isDup == true) {
				System.out.println("Sorry word is already added to list.");
			} else {
				tempWord = new Word(englishInEnglish, langInLang,
						englishInLang, langInEnglish, themesInEnglish,
						themesInLang, imageURI, infoURI, soundURIOfEnglish,
						soundURIOfLang);
				wordList.addWord(tempWord);
				wordsAdded++;

			}

		}

	}

	//Creates and returns a new WordList
	public WordList createWordList(int number) {
		WordList tempList3 = new WordList(number);
		return tempList3;
	}

	//Get tempList
	public WordList getTempList() {
		return tempList;
	}
	//Get wordList
	public WordList getWordList() {
		return wordList;
	}

	//Scans file and uses them to build wordList
	public void scanFile(File tempFile) {

		wordsAdded = 0;
		wordId = "";
		englishInEnglish = "";
		langInLang = "";
		englishInLang = "";
		langInEnglish = "";
		themesInEnglish = new ArrayList<String>();
		themesInLang = new ArrayList<String>();
		imageURI = "";
		infoURI = "";
		soundURIOfEnglish = "";
		soundURIOfLang = "";

		file = tempFile;
		hasLines = scanFileForLinesNum();

		wordList = new WordList(hasLines);
		rows = new String[hasLines];

		scanLines();

		scanRows();

	}

	//Scans file and returns number of lines/entires
	public int scanFileForLinesNum() {

		int numLines = 0;

		try {
			scanner1 = new Scanner(file, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner1.reset();

		while (scanner1.hasNextLine()) {
			scanner1.nextLine();
			numLines++;
		}

		return numLines;
	}

	//Scans each line and adds them to an String Array rows[]
	public void scanLines() {

		try {
			scanner2 = new Scanner(file, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int lineCount = 0;
		scanner2.reset();
		while (scanner2.hasNextLine()) {
			rows[lineCount] = "";
			String tempString2 = scanner2.nextLine();
			rows[lineCount] = tempString2;
			lineCount++;

		}

	}

	// Scans each row of words and seperates each section into its correct
	// String
	public void scanRows() {

		int lineCount = 0;
		int rowCount = 0;
		String tempWord2 = "";

		while (lineCount < rows.length) {
			List<String> tempTIE = new ArrayList<String>();
			List<String> tempTIL = new ArrayList<String>();
			String[] collumn = new String[10];

			collumn = rows[lineCount].split("\\|");

			if (collumn.length == 10) {
				englishInEnglish = collumn[0].trim();
				langInLang = collumn[1].trim();
				englishInLang = collumn[2].trim();
				langInEnglish = collumn[3].trim();
				themesInEnglishString = collumn[4].trim();
				themesInLangString = collumn[5].trim();
				imageURI = collumn[6].trim();
				infoURI = collumn[7].trim();
				soundURIOfEnglish = collumn[8].trim();
				soundURIOfLang = collumn[9].trim();

			} else {
				if (collumn.length != 1) {
					String[] temp = new String[10];

					for (int i = 0; i < 10; i++) {
						if (i <= collumn.length - 1)
							temp[i] = collumn[i];
						else
							temp[i] = "";
					}

					englishInEnglish = temp[0].trim();
					langInLang = temp[1].trim();
					englishInLang = temp[2].trim();
					langInEnglish = temp[3].trim();
					themesInEnglishString = temp[4].trim();
					themesInLangString = temp[5].trim();
					imageURI = temp[6].trim();
					infoURI = temp[7].trim();
					soundURIOfEnglish = temp[8].trim();
					soundURIOfLang = temp[9].trim();

				}

			}

			// Scanner 3 to split themesInEnglishString to seperate string
			// entries
			if (themesInEnglishString != "" && themesInEnglishString != null) {
				// Creates Scanners to sort through themes and seperate them by
				// ,
				scanner3 = new Scanner(themesInEnglishString);
				// Themes In English Seperator and adding
				while (scanner3.hasNext()) {
					// While loop to keep feeding next letter into the word
					// until end of section
					scanner3.useDelimiter(",");
					tempWord2 = scanner3.next();

					if (tempWord2.equals(",")) {

					} else {
						tempTIE.add(tempWord2);
					}

				}
				themesInEnglish = tempTIE;
			}
			// Scanner 4 to split themesInLangString to seperate string entries
			if (themesInLangString != "" && themesInLangString != null) {

				scanner4 = new Scanner(themesInLangString);
				// Themes In Lang Seperator and adding
				while (scanner4.hasNext()) {
					// While loop to keep feeding next letter into the word
					// until end of section
					scanner3.useDelimiter(",");
					tempWord2 = scanner4.next();

					if (tempWord2.equals(",")) {

					} else {
						tempTIL.add(tempWord2);
					}

				}
				themesInLang = tempTIL;
			}

			if (englishInEnglish != null && langInLang != null) {
				createWord(rowCount);
				rowCount++;
			}
			lineCount++;

		}

	}
	
	//Finds and returns the englishThemes list from wordList
	public List<String> getEngList() {

		int counter = 0;

		List<String> tempAList = new ArrayList<String>();
		List<String> themesList = new ArrayList<String>();
		String tempString = "";

		while (counter < wordList.getSize()) {

			if (!wordList.getWord(counter).getThemesInEnglish().isEmpty()) {

				tempAList = wordList.getWord(counter).getThemesInEnglish();

				if (tempAList.size() == 1) {
					tempString = tempAList.get(0);
					if (themesList.contains(tempString)) {

					} else {
						themesList.add(tempString);
					}
				} else {
					int counter1 = 0;
					while (counter1 < tempAList.size()) {
						tempString = tempAList.get(counter1);
						if (themesList.contains(tempString)) {

						} else {
							themesList.add(tempString);

						}
						counter1++;

					}
				}
				counter++;

			}

		}

		return themesList;
	}
	
	//Finds and returns the langThemes list from wordList
		public List<String> getLangList() {

			int counter = 0;

			List<String> tempAList = new ArrayList<String>();
			List<String> themesList = new ArrayList<String>();
			String tempString = "";

			while (counter < wordList.getSize()) {

				if (!wordList.getWord(counter).getThemesInLang().isEmpty()) {

					tempAList = wordList.getWord(counter).getThemesInLang();

					if (tempAList.size() == 1) {
						tempString = tempAList.get(0);
						if (themesList.contains(tempString)) {

						} else {
							themesList.add(tempString);
						}
					} else {
						int counter1 = 0;
						while (counter1 < tempAList.size()) {
							tempString = tempAList.get(counter1);
							if (themesList.contains(tempString)) {

							} else {
								themesList.add(tempString);

							}
							counter1++;

						}
					}
					counter++;

				}

			}

			return themesList;
		}

	//Randomizes wordList based on tempTheme and returns a seperate wordList of results
	public void randomN(String tempTheme) {

		int randomNum = 0;
		int num[];
		Random generator = new Random();
		int n = 0;

		if (tempTheme == "All Words") {
			int number = wordList.getSize();
			tempList = new WordList(number);
			num = new int[number];
			for (int y = 0; y < number; y++) {
				num[y] = -1;
			}

			for (int x = 0; x < number; x++) {

				boolean isSame = true;
				int tempLength = 1;
				while (isSame == true) {
					n = hasLines;
					randomNum = generator.nextInt(n);

					for (int z = 0; z < tempLength; z++) {
						if (randomNum == num[z]) {
							isSame = true;
							break;
						} else {
							isSame = false;

						}
					}

				}
				num[x] = randomNum;
				tempLength++;

			}

			for (int q = 0; q < number; q++) {
				int tempNumber = num[q];
				tempWord = wordList.getWord(tempNumber);
				tempList.addWord(tempWord);
			}

		} else {
			int x = 0;
			int tempLength = 1;
			List<String> tempThemes = new ArrayList<String>();
			themedList = new WordList();

			while (x < wordList.getSize()) {
				tempWord = wordList.getWord(x);
				tempThemes = tempWord.getThemesInEnglish();
				if (tempThemes.contains(tempTheme)) {
					themedList.addWord(tempWord);
				}
				x++;
			}

			int number2 = themedList.getSize();
			tempList = new WordList(number2);
			num = new int[number2];

			for (int y2 = 0; y2 < number2; y2++) {
				num[y2] = -1;
			}

			for (int x2 = 0; x2 < number2; x2++) {

				boolean isSame = true;

				while (isSame == true) {
					n = number2;
					randomNum = generator.nextInt(n);

					for (int z = 0; z < tempLength; z++) {
						if (randomNum == num[z]) {
							isSame = true;
							break;
						} else {
							isSame = false;

						}
					}

				}
				num[x2] = randomNum;
				tempLength++;

			}

			for (int q = 0; q < number2; q++) {
				int tempNumber = num[q];
				tempWord = themedList.getWord(tempNumber);
				tempList.addWord(tempWord);
			}

		}

	}

	//Returns new wordList based on tempTheme and sequentially sorted how the text file presents it
	public void sequentialN(String tempTheme) {
		themedList = new WordList();
		int number = 0;

		if (tempTheme == "All Words") {
			number = wordList.getSize();
			tempList = new WordList(number);

			for (int q = 0; q < number; q++) {
				tempWord = wordList.getWord(q);
				tempList.addWord(tempWord);
			}
		} else {
			int x = 0;
			List<String> tempThemes = new ArrayList<String>();

			while (x < wordList.getSize()) {
				tempWord = wordList.getWord(x);
				tempThemes = tempWord.getThemesInEnglish();

				if (tempThemes.contains(tempTheme)) {
					themedList.addWord(tempWord);
				}
				x++;
			}

			number = themedList.getSize();

			tempList = new WordList(number);

			for (int x2 = 0; x2 < number; x2++) {
				tempWord = themedList.getWord(x2);
				tempList.addWord(tempWord);
			}

		}

	}

	
	//Returns the instance of BackendSystem
	public static BackendSystem getInstance() {
		return INSTANCE;
	}

}

/*	
 *	ICS499
 *  Group 2
 *  This is the Main program the starts program and builds GUI
 */
/*
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
*/
function BackendSystem() 
{
	// Declares Global Variables
	 var wordId;; //String 
	 var englishInEnglish;; //String 
	 var langInLang;; //String 
	 var englishInLang;; //String 
	 var langInEnglish;; //String 
	 var themesInLangString;; //String 
	 var imageURI;; //String 
	 var infoURI;; //String 
	 var soundURIOfEnglish;; //String 
	 var soundURIOfLang;; //String 	 
	 var themesInEnglishString;; //String 
	 var themesInEnglish; //List<String>
	 var themesInLang; //List<String>
	 var wordList; //WordList
	 var themedList; //WordList
	 var tempList; //WordList
	 var tempList2; //WordList
	 var hasLines; //int
	 var themedSize; //int
	 var countLines; //int
	 var wordsAdded; //int
	 var file; //File
	 var scanner1; //Scanner
	 var scanner2; //Scanner
	 var scanner3; //Scanner
	 var scanner4; //Scanner
	 var word1; //Word
	 var tempWord; //Word
	 var rows = null; //int[]
	// TODO: Evaluate need for this function
	//private static final BackendSystem INSTANCE = new BackendSystem();


	//Creates Word and adds to wordList; Takes in int as variable
	this.createWord=function(number)
	{

		var tempWordId = englishInEnglish + langInLang;; //String 

		if (wordsAdded == 0) {
			tempWord = new Word(englishInEnglish, langInLang, englishInLang,
					langInEnglish, themesInEnglish, themesInLang, imageURI,
					infoURI, soundURIOfEnglish, soundURIOfLang);
			wordList.addWord(tempWord);
			wordsAdded++;
		} else {

			var isDup = wordList.searchWordIsDup(tempWordId); //boolean
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

	// TODO: - work on connecting function
	/*//Creates and returns a new WordList
	public WordList createWordList(int number) {
		var tempList3 = new WordList(number); //WordList
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
	*/
	
	//Scans file and uses them to build wordList
	this.scanFile=function(File tempFile) {

		wordsAdded = 0;
		wordId = "";
		englishInEnglish = "";
		langInLang = "";
		englishInLang = "";
		langInEnglish = "";
		themesInEnglish = new Array(); //ArrayList<String>
		themesInLang = new Array(); //ArrayList<String>
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
	this.scanFileForLinesNum=function() { //int

		var numLines = 0; //int

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
	this.scanLines=function() {

		try {
			scanner2 = new Scanner(file, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		var lineCount = 0; //int
		scanner2.reset();
		while (scanner2.hasNextLine()) {
			rows[lineCount] = "";
			var tempString2 = scanner2.nextLine(); //String 
			rows[lineCount] = tempString2;
			lineCount++;

		}

	}

	// Scans each row of words and seperates each section into its correct
	// String
	this.scanRows=function() {

		var lineCount = 0; //int
		var rowCount = 0; //int
		var tempWord2 = ""; //String 

		while (lineCount < rows.length) {
			var tempTIE = new Array(); //ArrayList<String> //List<String>
			var tempTIL = new Array(); //ArrayList<String> //List<String>
			var collumn = new String[10]; //String 

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
					var temp = new String[10]; //String 

					for (var i = 0; i < 10; i++) {
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
	this.getEngListList=function() { //List<String>

		var counter = 0; //int

		var tempAList = new Array(); //ArrayList<String> //List<String>
		var themesList = new Array(); //ArrayList<String> //List<String>
		var tempString = ""; //String 

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
					var counter1 = 0; //int
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
		this.getLangList=function() { // List<String> 

			var counter = 0; //int

			var tempAList = new Array(); //ArrayList<String> //List<String>
			var themesList = new Array(); //ArrayList<String> //List<String>
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
						var counter1 = 0; //int
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
	this.randomN=function(String tempTheme) {

		var randomNum = 0; //int
		var num[]; //int
		var generator = new Random(); //Random
		var n = 0; //int

		if (tempTheme == "All Words") {
			var number = wordList.getSize(); //int
			tempList = new WordList(number);
			num = new int[number];
			for (var y = 0; y < number; y++) {
				num[y] = -1;
			}

			for (var x = 0; x < number; x++) {

				var isSame = true; //boolean
				var tempLength = 1; //int
				while (isSame == true) {
					n = hasLines;
					randomNum = generator.nextInt(n);

					for (var z = 0; z < tempLength; z++) {
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

			for (var q = 0; q < number; q++) {
				var tempNumber = num[q]; //int
				tempWord = wordList.getWord(tempNumber);
				tempList.addWord(tempWord);
			}

		} else {
			var x = 0; //int
			var tempLength = 1; //int
			var tempThemes = new Array(); //ArrayList<String> //List<String>
			themedList = new WordList();

			while (x < wordList.getSize()) {
				tempWord = wordList.getWord(x);
				tempThemes = tempWord.getThemesInEnglish();
				if (tempThemes.contains(tempTheme)) {
					themedList.addWord(tempWord);
				}
				x++;
			}

			var number2 = themedList.getSize(); //int
			tempList = new WordList(number2);
			num = new int[number2];

			for (var y2 = 0; y2 < number2; y2++) {
				num[y2] = -1;
			}

			for (var x2 = 0; x2 < number2; x2++) {

				var isSame = true; //boolean

				while (isSame == true) {
					n = number2;
					randomNum = generator.nextInt(n);

					for (var z = 0; z < tempLength; z++) {
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

			for (var q = 0; q < number2; q++) {
				var tempNumber = num[q]; //int
				tempWord = themedList.getWord(tempNumber);
				tempList.addWord(tempWord);
			}

		}

	}

	//Returns new wordList based on tempTheme and sequentially sorted how the text file presents it
	this.sequentialN=function(String tempTheme) {
		themedList = new WordList();
		var number = 0; //int

		if (tempTheme == "All Words") {
			number = wordList.getSize();
			tempList = new WordList(number);

			for (var q = 0; q < number; q++) {
				tempWord = wordList.getWord(q);
				tempList.addWord(tempWord);
			}
		} else {
			var x = 0; //int
			var tempThemes = new Array(); //ArrayList<String> //List<String>

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

			for (var x2 = 0; x2 < number; x2++) {
				tempWord = themedList.getWord(x2);
				tempList.addWord(tempWord);
			}
		}
	}

	//TODO: 
	/* Evaluate this fucntion
	//Returns the instance of BackendSystem
	function BackendSystem getInstance() {
		return INSTANCE;
	}
	*/
}
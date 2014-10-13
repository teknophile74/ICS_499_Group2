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
function BackendSystem() {
	// Declares Global Variables
	// Strings
	var wordId, englishInEnglish, langInLang, englishInLang, langInEnglish, themesInLangString;
	var imageURI, infoURI, soundURIOfEnglish, soundURIOfLang, themesInEnglishString;
	// List<String>
	var themesInEnglish, themesInLang;
	// WordList
	var wordList, themedList, tempList;
	// int
	var hasLines, themedSize, countLines, wordsAdded;
	// File
	var file;
	// Scanner
	var scanner1, scanner2, scanner3, scanner4;
	//Word
	var tempWord;
	// int array
	var rows = new array();

	//Creates Word and adds to wordList; Takes in int as variable
	this.createWord = function(number)
	{
		var tempWordId = englishInEnglish + langInLang; //String 

		if (wordsAdded === 0) {
			tempWord = new Word(englishInEnglish, langInLang, englishInLang,
					langInEnglish, themesInEnglish, themesInLang, imageURI,
					infoURI, soundURIOfEnglish, soundURIOfLang);
			wordList.addWord(tempWord);
			wordsAdded++;
		} else {

			var isDup = wordList.searchWordIsDup(tempWordId); //boolean
			if (isDup === true) {
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
	};

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
	this.scanFile=function(tempFile) {

		wordsAdded = 0;
		wordId = "";
		englishInEnglish = "";
		langInLang = "";
		englishInLang = "";
		langInEnglish = "";
		themesInEnglish = new array(); //ArrayList<String>
		themesInLang = new array(); //ArrayList<String>
		imageURI = "";
		infoURI = "";
		soundURIOfEnglish = "";
		soundURIOfLang = "";

		file = tempFile;
		hasLines = scanFileForLinesNum();

		wordList = new array(); //WordList(hasLines);
		rows = new array(); //[hasLines]

	//	scanLines();
	//	scanRows();
	};

	//Scans file and returns number of lines/entires
	this.scanFileForLinesNum=function() { //int

		var numLines = 0; //int

		try {
			scanner1 = new Scanner(file, "UTF-8");
		} 
		catch (e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner1.reset();

		while (scanner1.hasNextLine()) {
			scanner1.nextLine();
			numLines++;
		}

		return numLines;
	};

	//Scans each line and adds them to an String Array rows[]
	this.scanLines=function() {

		try {
			scanner2 = new Scanner(file, "UTF-8");
		}
		catch (e) {
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
	};

	// Scans each row of words and seperates each section into its correct
	// String
	this.scanRows=function() {

		var lineCount = 0; //int
		var rowCount = 0; //int
		var tempWord2 = ""; //String 

		while (lineCount < rows.length) {
			var tempTIE = new array(); //ArrayList<String> //List<String>
			var tempTIL = new array(); //ArrayList<String> //List<String>
			var collumn = new array(); //String[10];

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
					var temp = new array(); //String[10];

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
			if (themesInEnglishString !== "" && themesInEnglishString !== null) {
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
			if (themesInLangString !== "" && themesInLangString !== null) {

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

			if (englishInEnglish !== null && langInLang !== null) {
				createWord(rowCount);
				rowCount++;
			}
			lineCount++;

		}

	};
	
	//Finds and returns the englishThemes list from wordList
	this.getEngListList=function() { //List<String>

		var counter = 0; //int

		var tempAList = new array(); //ArrayList<String> //List<String>
		var themesList = new array(); //ArrayList<String> //List<String>
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
	};
	
	//Finds and returns the langThemes list from wordList
		this.getLangList=function() { // List<String> 

			var counter = 0; //int

			var tempAList = new array(); //ArrayList<String> //List<String>
			var themesList = new array(); //ArrayList<String> //List<String>
			var tempString = "";

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
		};

	//Randomizes wordList based on tempTheme and returns a seperate wordList of results
	this.randomN=function(tempTheme) {
		var isSame = true; //boolean
		var tempLength = 1; //int
		var randomNum = 0; //int
		var num = new array(); //int
		var generator = new Random(); //Random
		var n = 0; //int

		if (tempTheme == "All Words") {
			var number = wordList.getSize(); //int
			tempList = new WordList(number);
			num = new array(); //int[number];
			for (var y = 0; y < number; y++) {
				num[y] = -1;
			}

			for (var x = 0; x < number; x++) {

				tempLength = 1; //int
				while (isSame === true) {
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

			for (var t = 0; t < number; t++) {
				var tempNumber = num[t]; //int
				tempWord = wordList.getWord(tempNumber);
				tempList.addWord(tempWord);
			}

		}
		else {
			var x1 = 0; //int
			var tempThemes = new array(); //ArrayList<String> //List<String>
			themedList = new WordList();

			while (x1 < wordList.getSize()) {
				tempWord = wordList.getWord(x1);
				tempThemes = tempWord.getThemesInEnglish();
				if (tempThemes.contains(tempTheme)) {
					themedList.addWord(tempWord);
				}
				x1++;
			}

			var number2 = themedList.getSize(); //int
			tempList = new WordList(number2);
			num = new array(); //int[number2];

			for (var y2 = 0; y2 < number2; y2++) {
				num[y2] = -1;
			}

			for (var x2 = 0; x2 < number2; x2++) {

				isSame = true; //boolean
				tempLength = 1; //int
				while (isSame === true) {
					n = number2;
					randomNum = generator.nextInt(n);

					for (var z2 = 0; z2 < tempLength; z2++) {
						if (randomNum == num[z2]) {
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
				var tempNumber1 = num[q]; //int
				tempWord = themedList.getWord(tempNumber1);
				tempList.addWord(tempWord);
			}
		}
	};

	//Returns new wordList based on tempTheme and sequentially sorted how the text file presents it
	this.sequentialN=function(tempTheme) {
		themedList = new WordList();
		var number = 0; //int

		if (tempTheme == "All Words") {
			number = wordList.getSize();
			tempList = new WordList(number);

			for (var q = 0; q < number; q++) {
				tempWord = wordList.getWord(q);
				tempList.addWord(tempWord);
			}
		} 
		else {
			var x = 0; //int
			var tempThemes = new array(); //ArrayList<String> //List<String>

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
	};

	//TODO: 
	/* Evaluate this fucntion
	//Returns the instance of BackendSystem
	function BackendSystem getInstance() {
		return INSTANCE;
	}
	*/
}
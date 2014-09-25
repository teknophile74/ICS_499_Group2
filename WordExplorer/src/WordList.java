/*	ICS499
 *  Group 2
 *  Main.java
 *  This is the Main program the starts program and builds GUI
 *  Please make sure jl1.0.1.jar is included in project build path
 */

import java.util.ArrayList;
import java.util.List;

public class WordList implements WordListInterface {

	private ArrayList<Word> wordList;
	private int capacity;
	private int wordsAdded;

	/*
	 * Creates WordList of 250 capacity No Return
	 */
	// Creates WordList
	public WordList() {
		this.capacity = 250;
		wordList = new ArrayList<>(this.capacity);
	}

	/*
	 * Creates WordList of initialCapacity size No return
	 */
	public WordList(int initialCapacity) {
		this.capacity = initialCapacity;
		wordList = new ArrayList<>(this.capacity);
	}

	/*
	 * Searches WordList to see if empty Returns true if empty false if not
	 * 
	 * @see WordListInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if (wordsAdded == 0) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * Searches WordList to see if it's full Returns true if full false if not
	 * 
	 * @see WordListInterface#isFull()
	 */
	@Override
	public boolean isFull() {
		if (wordsAdded < capacity) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Searches WordList for word matching newWord Returns true and adds word or
	 * returns false and doesn't
	 * 
	 * @see WordListInterface#addWord(Word newWord)
	 */
	@Override
	public boolean addWord(Word newWord) {
		if (wordList.size() > capacity) {
			return false;
		} else {
			wordList.add(newWord);
			wordsAdded++;
			return true;
		}

	}

	/*
	 * Searches WordList for word that matches newWord Either returns true and
	 * removes word or returns false and does nothing
	 * 
	 * @see WordListInterface#removeWord(Word newWord)
	 */
	@Override
	public boolean removeWord(Word newWord) {
		return wordList.remove(newWord);
	}

	/*
	 * Searches WordList for word with wordNum Returns Word that matches wordNum
	 * int
	 * 
	 * @see WordListInterface#getWord(int wordNum)
	 */
	@Override
	public Word getWord(int wordNum) {
		Word tempWord = new Word();

		tempWord = wordList.get(wordNum);

		return tempWord;

	}

	/*
	 * Finds size of wordList Returns int of size
	 * 
	 * @see WordListInterface#getSize()
	 */
	@Override
	public int getSize() {
		return wordList.size();
	}

	/*
	 * Searches WordList for duplicate wordId Returns boolean if duplicate word
	 * is found
	 * 
	 * @see WordListInterface#searchWordIsDup(String wordId)
	 */
	@Override
	public boolean searchWordIsDup(String wordId) {

		int counter = 0;
		boolean isFound = false;
		boolean found = false;

		while (counter < wordList.size() && found == false) {
			if (this.getWord(counter).getWordId() == wordId) {
				found = true;
				isFound = true;
			} else {

				isFound = false;
			}
			counter++;
		}

		return isFound;
	}

	/*
	 * Searches WordList englishInEnglish that matches wordInEnglish Returns int
	 * of Word that matches wordInEnglish
	 * 
	 * @see WordListInterface#searchWordEnglish(String wordInEnglish)
	 */
	@Override
	public int searchWordEnglish(String wordInEnglish) {
		int wordNum = 0;
		int counter = 0;
		boolean found = false;

		while (counter < wordList.size() && found == false) {
			String tempString = wordList.get(counter).getEnglishInEnglish();
			if (tempString.equals(wordInEnglish)) {
				found = true;
				wordNum = counter;
			}

			counter++;
		}
		if (found = true) {
			return wordNum;
		} else {
			return -1;
		}

	}

	/*
	 * Searches WordList langInLang that matches wordInLang Returns int of Word
	 * that matches wordInLang
	 * 
	 * @see WordListInterface#searchWordLang(String WordInLang)
	 */
	@Override
	public int searchWordLang(String wordInLang) {
		int wordNum = 0;
		int counter = 0;
		boolean found = false;

		while (counter < wordList.size() && found == false) {
			if (wordList.get(counter).getLangInLang().equals(wordInLang)) {
				found = true;
				wordNum = counter;
			}

			counter++;
		}
		if (found = true) {
			return wordNum;
		} else {
			return -1;
		}

	}

	/*
	 * Searches WordList for word Returns int of Word that matches wordID
	 * 
	 * @see WordListInterface#searchWordId(String WordId)
	 */
	@Override
	public int searchWordId(String wordId) {
		int wordNum = 0;
		int counter = 0;
		boolean found = false;

		while (counter < wordList.size() && found == false) {
			if (wordList.get(counter).getWordId().equals(wordId)) {
				found = true;
				wordNum = counter;
			}
			counter++;
		}
		if (found != true) {
			wordNum = -1;
		}

		return wordNum;
	}

	/*
	 * Returns List of english themes List
	 * 
	 * @see WordListInterface#getEngList()
	 */
	@Override
	public List<String> getEngList() {

		int counter = 0;

		List<String> tempAList = new ArrayList<String>();
		List<String> themesList = new ArrayList<String>();
		String tempString = "";

		while (counter < wordList.size()) {

			if (this.getWord(counter).getThemesInEnglish().isEmpty()) {

				tempAList = this.getWord(counter).getThemesInEnglish();

				if (tempAList.size() == 1) {
					tempString = tempAList.get(0);
					if (themesList.contains(tempString)) {

					} else {
						themesList.add(tempString);
					}
				} else {
					int counter1 = 0;
					while (counter1 <= tempAList.size()) {
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

}

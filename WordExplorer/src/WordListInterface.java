/*	ICS499
 *  Group 2
 *  Main.java
 *  This is the Main program the starts program and builds GUI
 *  Please make sure jl1.0.1.jar is included in project build path
 */

import java.util.List;

public interface WordListInterface {

	// Interface Methods
	public boolean addWord(Word newWord);

	public boolean removeWord(Word newWord);

	public Word getWord(int wordNum);

	public int getSize();

	public boolean searchWordIsDup(String wordId);

	public int searchWordEnglish(String wordInEnglish);

	public int searchWordLang(String wordInLang);

	public int searchWordId(String wordId);

	public List<String> getEngList();

	public boolean isEmpty();

	public boolean isFull();

}

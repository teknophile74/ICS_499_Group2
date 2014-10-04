/*	ICS499
 *  Group 2
 */

function WordListInterface {
	// Interface Methods
	 var addWord = newWord() //function Word ; //boolean
	 var removeWord = newWord() //function Word; //boolean
	 var getWord(wordNum); //Word
	 var getSize(); //int
	 var searchWordIsDup(wordId); //boolean
	 var searchWordEnglish(wordInEnglish); //int
	 var searchWordLang(wordInLang); //int
	 var searchWordId(wordId); //int
	 var getEngList(); //List<String>
	 var isEmpty(); //boolean
	 var isFull(); //boolean
}

/*	ICS370 - Final Project
 *  Group 3
 *  Word.java
 *  This is the word object that is created for each word
 *  Please make sure jl1.0.1.jar is included in project build path
 */

//import java.util.ArrayList;
//import java.util.List;

function Word() {

	// Values for Word
	this.wordId = "";
	this.englishInEnglish = "";
	this.langInLang = "";
	this.englishInLang = "";
	this.langInEnglish = "";
	this.themesInEnglish = []; // new ArrayList<String>();
	this.themesInLang = []; // new ArrayList<String>();
	this.imageURI = "";
	this.infoURI = "";
	this.soundURIOfEnglish = "";
	this.soundURIOfLang = "";

	/*
	 * Creates Word with all values includeded No Return
	 */
	/* TODO - may just need assignment via JSON arrays
	Word(eIE, lIL, eIL, lIE,
			List<String> tIE, List<String> tIL, imURI, inURI,sURIE, sURIL) {

		wordId = eIE + lIE;       //eIE = English in English
		englishInEnglish = eIE;   // LiE= Lang in English
		langInLang = lIL;         // lil= Lang in Lang
		englishInLang = eIL;
		langInEnglish = lIE;
		themesInEnglish = tIE;
		themesInLang = tIL;
		imageURI = imURI;
		infoURI = inURI;
		soundURIOfEnglish = sURIE;
		soundURIOfLang = sURIL;

	};
	*/

	/*
	 * Grabs wordID of Word Returns String wordId
	 *
	 * @see WordListInterface#getEngList()
	 */

	this.setEnglishInEnglish=function(englishInEnglish) {
		this.englishInEnglish = englishInEnglish;

		if (this.englishInEnglish !== null && this.langInLang !== null) {
			setWordId(this.englishInEnglish + this.langInLang);
		}
	};

	this.setLangInLang=function(langInLang) {
		this.langInLang = langInLang;

		if (this.englishInEnglish !== null && this.langInLang !== null) {
			setWordId(this.englishInEnglish + this.langInLang);
		}
	};
}

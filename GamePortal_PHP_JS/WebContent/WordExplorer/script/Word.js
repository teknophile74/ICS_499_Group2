
/*	ICS370 - Final Project
 *  Group 3
 *  Word.java
 *  This is the word object that is created for each word
 *  Please make sure jl1.0.1.jar is included in project build path
 */

//import java.util.ArrayList;
//import java.util.List;

function Word {

	// Values for Word
	var wordId;
	var englishInEnglish;
	var langInLang;
	var englishInLang;
	var langInEnglish;
	themesInEnglish = new Array(); //ArrayList<String>
	themesInLang = new Array(); //ArrayList<String>
	var imageURI;
	var infoURI;
	var soundURIOfEnglish;
	var soundURIOfLang;

	/*
	 * Creates Word No Return
	 */
	public Word() {

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

	}

	/*
	 * Creates Word with all values inclued No Return
	 */
	public Word(String eIE, String lIL, String eIL, String lIE,
			List<String> tIE, List<String> tIL, String imURI, String inURI,
			String sURIE, String sURIL) {

		wordId = eIE + lIE;
		englishInEnglish = eIE;
		langInLang = lIL;
		englishInLang = eIL;
		langInEnglish = lIE;
		themesInEnglish = tIE;
		themesInLang = tIL;
		imageURI = imURI;
		infoURI = inURI;
		soundURIOfEnglish = sURIE;
		soundURIOfLang = sURIL;

	}

	/*
	 * Grabs wordID of Word Returns String wordId
	 *
	 * @see WordListInterface#getEngList()
	 */
	var getWordId() {
		return wordId;
	}

	public void setWordId(String wordId) {
		this.wordId = wordId;

	}

	public String getEnglishInEnglish() {
		return englishInEnglish;
	}

	public void setEnglishInEnglish(String englishInEnglish) {
		this.englishInEnglish = englishInEnglish;

		if (this.englishInEnglish != null && this.langInLang != null) {
			setWordId(this.englishInEnglish + this.langInLang);
		}
	}

	public String getLangInLang() {
		return langInLang;

	}

	public void setLangInLang(String langInLang) {
		this.langInLang = langInLang;

		if (this.englishInEnglish != null && this.langInLang != null) {
			setWordId(this.englishInEnglish + this.langInLang);
		}
	}

	public String getEnglishInLang() {
		return englishInLang;
	}

	public void setEnglishInLang(String englishInLang) {
		this.englishInLang = englishInLang;
	}

	public String getLangInEnglish() {
		return langInEnglish;
	}

	public void setLangInEnglish(String langInEnglish) {
		this.langInEnglish = langInEnglish;
	}

	public List<String> getThemesInEnglish() {
		return themesInEnglish;
	}

	public void setThemesInEnglish(List<String> themesInEnglish) {
		this.themesInEnglish = themesInEnglish;
	}

	public List<String> getThemesInLang() {
		return themesInLang;
	}

	public void setThemesInLang(List<String> themesInLang) {
		this.themesInLang = themesInLang;
	}

	public String getImageURI() {
		return imageURI;
	}

	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}

	public String getInfoURI() {
		return infoURI;
	}

	public void setInfoURI(String infoURI) {
		this.infoURI = infoURI;
	}

	public String getSoundURIOfEnglish() {
		return soundURIOfEnglish;
	}

	public void setSoundURIOfEnglish(String soundURIOfEnglish) {
		this.soundURIOfEnglish = soundURIOfEnglish;
	}

	public String getSoundURIOfLang() {
		return soundURIOfLang;
	}

	public void setSoundURIOfLang(String soundURIOfLang) {
		this.soundURIOfLang = soundURIOfLang;
	}

}

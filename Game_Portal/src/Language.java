/*
 * Basic class for storing Language information
 * stores department and course number information
 */
public class Language {
	private int langID;
	private String langName;
	private String LangUTFDesignation;
	
	/*
	 * Main Constructor
	 * sets department and course number information
	 */
	public Language(int langID, String langName, String LangUTFDesignation) {
		setLangID(langID);
		setLangName(langName);
		setLangUTFDesignation(LangUTFDesignation);
	}
	
	public int getLangID() {
		return langID;
	}

	public void setLangID(int langID) {
		this.langID = langID;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public String getLangUTFDesignation() {
		return LangUTFDesignation;
	}

	public void setLangUTFDesignation(String langUTFDesignation) {
		LangUTFDesignation = langUTFDesignation;
	}

}

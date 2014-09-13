

/*======================================================================
 Author     : JasthiGUI.java Program
======================================================================*/

import java.util.ArrayList;

/**
 * This class encapsulates a word consisting of different attributes
 */

public class Word
{	
	//[1] for representing a unique ID of the word - generally a number
		private String wordId;	

		//[2] English Word written in ENGLISH
		private String englishInEnglish;	

		//[3] <LANG> Word written in <LANG> Language
		private String langInLang;	

		//[4] English Word written in <LANG> Language
		private String englishInLang;	

		//[4.1] Lang Word written in <ENGLISH> Language
		private String langInEnglish;	

		//[5] Theme in English
		private String theme;	

		//[6] URI 1 for the image file for the Word
		private String imageURI;	

		//[7] URI for the sound file for ENGLISH sound
		private String englishSoundURI;	

		//[8] URI for the sound file for <LANG> sound
		private String langSoundURI;
	
		
	/**
	 * Default Constructor For Word Class
	 */
	 public Word()
	 {
 
	 };

	 /**
		* Overloded Constructor For Word Class
		*/
		 public Word(String a_word_id, String a_english_in_english, String a_lang_in_lang, String a_eng_in_lang, String a_lang_in_eng, String a_theme, String a_image_u_r_i, String a_english_sound_u_r_i, String a_lang_sound_u_r_i)
		 { 
			wordId = a_word_id;
			englishInEnglish = a_english_in_english;
			langInLang = a_lang_in_lang;
			englishInLang = a_eng_in_lang;
			langInEnglish = a_lang_in_eng; // this is derived
			theme = a_theme;
			imageURI = a_image_u_r_i;
			englishSoundURI = a_english_sound_u_r_i;
			langSoundURI = a_lang_sound_u_r_i;
		 }



			/**
			 * Set method for the variable wordId
			 */
			public void setWordId(String a_word_id)
			{
				wordId = a_word_id;
			}

			/**
			 * Set method for the variable englishInEnglish
			 */
			public void setEnglishInEnglish(String a_english_in_english)
			{
				englishInEnglish = a_english_in_english;
			}

			/**
			 * Set method for the variable langInLang
			 */
			public void setLangInLang(String a_lang_in_lang)
			{
				langInLang = a_lang_in_lang;
			}

			/**
			 * Set method for the variable engInLang
			 */
			public void setEnglishInLang(String a_eng_in_lang)
			{
				englishInLang = a_eng_in_lang;
			}

			/*
			 * Set method for the variable langInEng
			 * We don't need this as this is derived value
			 */
			/*
			public void setLangInEng(String a_lang_in_eng)
			{
				langInEng = a_lang_in_eng;
			}
			*/

			/**
			 * Set method for the variable theme
			 */
			public void setTheme(String a_theme)
			{
				theme = a_theme;
			}

			/**
			 * Set method for the variable imageURI
			 */
			public void setImageURI(String a_image_u_r_i)
			{
				imageURI = a_image_u_r_i;
			}

			/**
			 * Set method for the variable englishSoundURI
			 */
			public void setEnglishSoundURI(String a_english_sound_u_r_i)
			{
				englishSoundURI = a_english_sound_u_r_i;
			}

			/**
			 * Set method for the variable langSoundURI
			 */
			public void setLangSoundURI(String a_lang_sound_u_r_i)
			{
				langSoundURI = a_lang_sound_u_r_i;
			}

			/**
			 * Get method for the variable wordId
			 */
			public String getWordId( )
			{
				return wordId;
			}

			/**
			 * Get method for the variable englishInEnglish
			 */
			public String getEnglishInEnglish( )
			{
				return englishInEnglish;
			}

			/**
			 * Get method for the variable langInLang
			 */
			public String getLangInLang( )
			{
				return langInLang;
			}

			/**
			 * Get method for the variable engInLang
			 */
			public String getEnglishInLang( )
			{
				return englishInLang;
			}

			/**
			 * Get method for the variable langInEng
			 */
			public String getLangInEnglish( )
			{
				return langInEnglish;
			}

			/**
			 * Get method for the variable theme
			 */
			public String getTheme( )
			{
				return theme;
			}

			/**
			 * Get method for the variable imageURI
			 */
			public String getImageURI( )
			{
				return imageURI;
			}

			/**
			 * Get method for the variable englishSoundURI
			 */
			public String getEnglishSoundURI( )
			{
				return englishSoundURI;
			}

			/**
			 * Get method for the variable langSoundURI
			 */
			public String getLangSoundURI( )
			{
				return langSoundURI;
			}
	
			/** 
			 * Returns the String representation of Student object 
			 */
			 public String toString()
			{
				 String temp = 
					"\nwordId = " + wordId +
					"\nenglishInEnglish = " + englishInEnglish +
					"\nlangInLang = " + langInLang +
					"\nengInLang = " + englishInLang +
					"\nlangInEng = " + langInEnglish +
					"\ntheme = " + theme +
					"\nimageURI = " + imageURI +
					"\nenglishSoundURI = " + englishSoundURI +
					"\nlangSoundURI = " + langSoundURI;

				 return temp;
			}

	
}




//********************************************************************** 
// FourPixMain.java, Yuxiang Wang, Gj4912oy@metrostate.edu, Midterm, July .9 
/*
 * *
 * Four Pix Main Game. 
 */
public class FourPixMain {

    public static void main(String[] args) {
	// prepare the language used
	Transliteration transliteration = new Transliteration(Configuration.LANG_FILE_NAME);
	
	// prepare the FourPix's from the input file
	FourPixCreatorCollection fpcc = new FourPixCreatorCollection(Configuration.INPUT_FILE_NAME, transliteration);
	
	// get a random FourPix from the input collection
	FourPixCreator fpc = fpcc.getRandomFourPixCreator();
	
	// display the random FourPix
	new FourPixCreatorGUI(fpc, transliteration);
    }

}

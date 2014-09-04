//********************************************************************** 
// FourPixCreatorGUI.java, Yuxiang Wang, Gj4912oy@metrostate.edu, Midterm, July .9 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FourPixCreatorGUI extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    // Integer representations of letter types
    private static final int ANY_LETTER_TYPE = 0;
    private static final int CONSONANT_LETTER_TYPE = 1;
    private static final int VOWEL_LETTER_TYPE = 2;
    
    // Width and height of the JLabels
    private static final int LABEL_SIZE = 150;
    
    // Transliteration object
    private Transliteration transliteration;

    // Font recommended
    private Font recommendedFont;
    
    // Pic JLabels
    private JLabel lableImg1 = new JLabel();
    private JLabel lableImg2 = new JLabel();
    private JLabel lableImg3 = new JLabel();
    private JLabel lableImg4 = new JLabel();
    
    // Text fields for the answer
    private JTextField[] jtf;

    // Contains the four pix display
    private Container fourPixContainer;
    
    // The FourPixCreator being displayed
    private FourPixCreator fourPix;
    
    // Maps for the letters and their JButton/JTextField
    private Map<String, String> textMap;
    private Map<String, JButton> buttonMap;

    /**
     * Instantiates and displays a new FourPixCreatorGUI.
     *     
     * @param fpc the FourPixCreator to be displayed
     * @param transliteration
     */
    public FourPixCreatorGUI(FourPixCreator fpc, Transliteration t) {
	// set the objects
	fourPix = fpc;
	transliteration = t;
	
	// set the font
	try {
	    InputStream is = this.getClass().getResourceAsStream("/res/"+transliteration.getRecommendedFontName());
	    recommendedFont = Font.createFont(Font.TRUETYPE_FONT, is);
	    recommendedFont = recommendedFont.deriveFont(20f);
	} catch (final FontFormatException | IOException e) {
	    recommendedFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	}
	
	// instantiate the attributes
	fourPixContainer = new Container();
	textMap = new HashMap<String, String>();
	buttonMap = new HashMap<String, JButton>();
	
	// initialize the attributes
	initFourPix();
	initCharacters();
	
	// set the JFrame attributes
	this.setContentPane(fourPixContainer);
	this.setBounds(70, 70, 700, 650);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setVisible(true);
    }

    /**
     * Initializes the display of the FourPixCreator.
     */
    private void initFourPix() {

	if ("text".equalsIgnoreCase(Configuration.DISPLAY_MODE)) {
	    int fontSize = 30;
	    lableImg1.setText(fourPix.getWord1());
	    lableImg1.setForeground(Color.red);//
	    lableImg1.setFont(new Font("Serif", Font.PLAIN, fontSize));//
	    lableImg1.setBounds(80, 10, LABEL_SIZE, LABEL_SIZE);

	    lableImg2.setText(fourPix.getWord2());
	    lableImg2.setFont(new Font("Serif", Font.PLAIN, fontSize));//
	    lableImg2.setForeground(Color.blue);//
	    lableImg2.setBounds(80, LABEL_SIZE + 20, LABEL_SIZE, LABEL_SIZE);

	    lableImg3.setText(fourPix.getWord3());
	    lableImg3.setFont(new Font("Serif", Font.PLAIN, fontSize));// /
	    lableImg3.setForeground(Color.yellow);//
	    lableImg3.setBounds(80 + LABEL_SIZE + 20, 10, LABEL_SIZE, LABEL_SIZE);

	    lableImg4.setText(fourPix.getWord4());
	    lableImg4.setFont(new Font("Serif", Font.PLAIN, fontSize));//
	    lableImg4.setForeground(Color.green);//
	    lableImg4.setBounds(80 + LABEL_SIZE + 20, LABEL_SIZE + 20, LABEL_SIZE,
		    LABEL_SIZE);

	} else if ("image".equalsIgnoreCase(Configuration.DISPLAY_MODE)) {
	    ImageIcon icon1 = null;
	    ImageIcon icon2 = null;
	    ImageIcon icon3 = null;
	    ImageIcon icon4 = null;

	    try {
		URL imgURL1 = new URL(fourPix.getURL1());
		icon1 = new ImageIcon(imgURL1);

		URL imgURL2 = new URL(fourPix.getURL2());
		icon2 = new ImageIcon(imgURL2);

		URL imgURL3 = new URL(fourPix.getURL3());
		icon3 = new ImageIcon(imgURL3);

		URL imgURL4 = new URL(fourPix.getURL4());
		icon4 = new ImageIcon(imgURL4);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    ScaleIcon scaleicon1 = new ScaleIcon(icon1, LABEL_SIZE, LABEL_SIZE);
	    lableImg1.setIcon(scaleicon1);
	    lableImg1.setBounds(80, 10, LABEL_SIZE, LABEL_SIZE);

	    ScaleIcon scaleicon2 = new ScaleIcon(icon2, LABEL_SIZE, LABEL_SIZE);
	    lableImg2.setIcon(scaleicon2);
	    lableImg2.setBounds(80, LABEL_SIZE + 20, LABEL_SIZE, LABEL_SIZE);

	    ScaleIcon scaleicon3 = new ScaleIcon(icon3, LABEL_SIZE, LABEL_SIZE);
	    lableImg3.setIcon(scaleicon3);
	    lableImg3.setBounds(80 + LABEL_SIZE + 20, 10, LABEL_SIZE, LABEL_SIZE);

	    ScaleIcon scaleicon4 = new ScaleIcon(icon4, LABEL_SIZE, LABEL_SIZE);
	    lableImg4.setIcon(scaleicon4);
	    lableImg4.setBounds(80 + LABEL_SIZE + 20, LABEL_SIZE + 20, LABEL_SIZE,
		    LABEL_SIZE);
	}

	fourPixContainer.add(lableImg1, BorderLayout.CENTER);
	fourPixContainer.add(lableImg2, BorderLayout.CENTER);
	fourPixContainer.add(lableImg3, BorderLayout.CENTER);
	fourPixContainer.add(lableImg4, BorderLayout.CENTER);

    }

    /**
     * Initializes the display of the characters available.
     */
    private void initCharacters() {
	// generate the available letters
	ArrayList<String> letters = generateAvailableLetters();
	
	int x0 = 20;
	int size = 70;
	
	int yLabelStart = LABEL_SIZE + LABEL_SIZE + 60;
	
	jtf = new JTextField[fourPix.getSolutionLength()];
	for (int i = 0; i < fourPix.getSolutionLength(); i++) {
	    jtf[i] = new JTextField();
	    jtf[i].setName("JTextField" + i);
	    jtf[i].setBounds(x0 + ((size+10) * i), yLabelStart, size, size);
	    jtf[i].setFont(recommendedFont);
	    jtf[i].setEditable(false);
	    jtf[i].addMouseListener(new LetterTextListener());
	    fourPixContainer.add(jtf[i]);
	}
	
	int yButtonStart = yLabelStart + size + 10;
	
	int index = 1;
	for (int i = 0; i < Configuration.NUMBER_OF_FILLER_ROWS; i++) {
	    for (int j = 0; j < Configuration.NUMBER_OF_TILES_PER_ROW; j++) {
		JButton jb = new JButton();
		jb.setBounds(x0 + ((size+10) * j), yButtonStart + (i * (size+10)), size, size);
		String sT = letters.get(index-1);
		jb.setText(sT);
		jb.setName("JButton" + (index++));
		jb.setFont(recommendedFont);
		jb.addActionListener(new LetterButtonListener());

		buttonMap.put(jb.getName(), jb);
		fourPixContainer.add(jb);
	    }
	}
    }

    /**
     * Generates the letters available for the word set.
     * Includes the letters from the solution word and
     * random filler letters.
     * 
     * @return an ArrayList of the available letters
     */
    private ArrayList<String> generateAvailableLetters() {
        ArrayList<String> letters = new ArrayList<>();
        
        // add the letters from the solution word
        for (String letter : fourPix.getSolutionLetters()){
            letters.add(letter);
        }
        
        // number of random letters needed
        int nAvailableLetters = Configuration.NUMBER_OF_FILLER_ROWS 
        	    * Configuration.NUMBER_OF_TILES_PER_ROW;
        int nRandomLetters = nAvailableLetters - fourPix.getSolutionLength();
        
        if ("consonant".equalsIgnoreCase(Configuration.FILLER_CHARACTERS)) {
            // add consonant letters
            for (int i=0; i<nRandomLetters; i++) {
        	letters.add(generateRandomLetter(CONSONANT_LETTER_TYPE));
            }
        } else if ("vowel".equalsIgnoreCase(Configuration.FILLER_CHARACTERS)) {
            // add vowel letters
            for (int i=0; i<nRandomLetters; i++) {
        	letters.add(generateRandomLetter(VOWEL_LETTER_TYPE));
            }
        } else {
            // add any letters
            for (int i=0; i<nRandomLetters; i++) {
        	letters.add(generateRandomLetter(ANY_LETTER_TYPE));
            }
        }
        
        // shuffle the letters and return
        Collections.shuffle(letters);
        return letters;
    }

    /**
     * Produces a random letter.
     * 
     * @param letterType type of letter needed:
     * consonant, vowel, or any
     * @return a random letter
     */
    private String generateRandomLetter(int letterType){
        Random random = new Random();
        int randomInt;
        String randomLetter;
        
        // get a random integer i from 0 to the length of the
        // available letters, then get the ith character
        // of the available letters
        if (letterType == CONSONANT_LETTER_TYPE) {
            ArrayList<String> consonantLetters = transliteration.getConsonantStrings();
            randomInt = random.nextInt(consonantLetters.size());
            randomLetter = consonantLetters.get(randomInt);
        } else if (letterType == VOWEL_LETTER_TYPE) {
            ArrayList<String> vowelLetters = transliteration.getVowelStrings();
            randomInt = random.nextInt(vowelLetters.size());
            randomLetter = vowelLetters.get(randomInt);
        } else {
            ArrayList<String> allLetters = transliteration.getAllStrings();
            randomInt = random.nextInt(allLetters.size());
            randomLetter = allLetters.get(randomInt);
        }
    
        return randomLetter.toUpperCase();
    }
    
    // TODO
//    /**
//     * Generates the letters available for the word set.
//     * Includes the letters from the solution word and
//     * random filler letters.
//     * 
//     * @return an ArrayList of the available letters
//     */
//    private ArrayList<String> generateAvailableLetters() {
//        ArrayList<String> letters = new ArrayList<>();
//        
//        // add the letters from the solution word
//        String solutionWord = fourPix.getSolutionWord();
//        int swActualLength = fourPix.getSolutionLength();
//        int offset = 0, swCharLength = solutionWord.length();
//	while (offset < swCharLength) {
//	  int curChar = solutionWord.codePointAt(offset);
//	  int endIndex = offset + Character.charCount(curChar);
//	  letters.add(solutionWord.substring(offset, endIndex));
//	  offset = endIndex;
//	}
//        
//        // number of random letters needed
//        int nAvailableLetters = Configuration.NUMBER_OF_FILLER_ROWS 
//        	    * Configuration.NUMBER_OF_TILES_PER_ROW;
//        int nRandomLetters = nAvailableLetters - swActualLength;
//        
//        if ("consonant".equalsIgnoreCase(Configuration.FILLER_CHARACTERS)) {
//            // add consonant letters
//            for (int i=0; i<nRandomLetters; i++) {
//        	letters.add(generateRandomLetter(CONSONANT_LETTER_TYPE));
//            }
//        } else if ("vowel".equalsIgnoreCase(Configuration.FILLER_CHARACTERS)) {
//            // add vowel letters
//            for (int i=0; i<nRandomLetters; i++) {
//        	letters.add(generateRandomLetter(VOWEL_LETTER_TYPE));
//            }
//        } else {
//            // add any letters
//            for (int i=0; i<nRandomLetters; i++) {
//        	letters.add(generateRandomLetter(ENG_ANY_LETTER_TYPE));
//            }
//        }
//        
//        // shuffle the letters and return
//        Collections.shuffle(letters);
//        return letters;
//    }
//
//    /**
//     * Produces a random letter.
//     * 
//     * @param letterType type of letter needed:
//     * consonant, vowel, or any
//     * @return a random letter
//     */
//    private static String generateRandomLetter(int letterType){
//        // prepare the available vowel and consonant letters
//        String vowelLetters = "AEIOU";
//        String consonantLetters = "BCDFGHJKLMNPQRSTVWXYZ";
//        String allLetters = vowelLetters + consonantLetters;
//        
//        Random random = new Random();
//        int randomInt;
//        char randomCharacter;
//        
//        // get a random integer i from 0 to the length of the
//        // available letters, then get the ith character
//        // of the available letters
//        if (letterType == CONSONANT_LETTER_TYPE) {
//            randomInt = random.nextInt(consonantLetters.length());
//            randomCharacter = consonantLetters.charAt(randomInt);
//        } else if (letterType == VOWEL_LETTER_TYPE) {
//            randomInt = random.nextInt(vowelLetters.length());
//            randomCharacter = vowelLetters.charAt(randomInt);
//        } else {
//            randomInt = random.nextInt(allLetters.length());
//            randomCharacter = allLetters.charAt(randomInt);
//        }
//    
//        // return a String representation of the random character
//        return Character.toString(randomCharacter).toUpperCase();
//    }

    /**
     * Action Listener when user clicks on a button of a letter.
     */
    private class LetterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            for (int index = 0; index < jtf.length; index++) {
        	if (jtf[index].getText().trim().equals("")) {
        	    if (!textMap.keySet().contains(jtf[index])) {
        		textMap.put(jtf[index].getName(), jButton.getName());
        	    }
        	    jtf[index].setText(jButton.getText());
        	    jButton.setVisible(false);
        	    break;
        	}
            }
    
            StringBuffer strButBuffer = new StringBuffer();
    
            for (int index = 0; index < jtf.length; index++) {
        	strButBuffer.append(jtf[index].getText());
            }
    
            // check if the solution word has been found
            if (strButBuffer.toString().equalsIgnoreCase(fourPix.getSolutionWord().toUpperCase())) {
        	JOptionPane.showMessageDialog(null, "Conguaration! Run program again and play the next game!", "",
        		JOptionPane.INFORMATION_MESSAGE);
        	dispose();
            }
            
        }
    
    }
    
    /**
     * Mouse Listener when user clicks on a text field of a letter.
     */
    private class LetterTextListener extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent e) {
	    JTextField jTextField = (JTextField) e.getSource();
	    if (jTextField.getText().equals(""))
		return;
	    String jButtonName = textMap.get(jTextField.getName());
	    JButton jButton = buttonMap.get(jButtonName);
	    jButton.setText(jTextField.getText());
	    jButton.setVisible(true);
	    jTextField.setText("");
	}
    }
    
}

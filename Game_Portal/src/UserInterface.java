/*
 * Import statements		
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author Aaron Burke
 * UserInferface class to set properties of the main form
 * and to add controls to the form for viewing
 * Implements SwingConstants to gain access to "LEFT" int object
 */
public class UserInterface	extends JFrame 
							implements SwingConstants{
	/*
	 * Set Variable for use throughout class
	 */
	private int countInt;
	private static final long serialVersionUID = 1L;
	private static final int	displayWidth = 680,
								displayHeight = 480;
	private final String selGameLabelText = "Selected Game:  ";
	private final String selLangLabelText = "Selected Language:  ";
	private JTextArea centerPanelInsert;
	private JLabel selectedGameDisplay;
	private ArrayList<String> registrationList = new ArrayList<String>();
	private JPanel	westPanel,
					westPanelInsert,				
					centerPanel,
					eastPanel,
					eastPanelInsert;

	private String	currentlySelectedGame,
					currentlySelectedLanguage;
	private JButton[]	gameButton,
						languageButton;
	
	/*
	 * Base UserInterface constructor
	 * Uses three methods, one is a super object from JFrame
	 */
	public UserInterface(ArrayList<Games> games, ArrayList<Language> languages) {
		init();
		basicLayout(games, languages);
		repaint();
	} //End UserInterface constructor
	
	/*
	 * Set base settings for the JFrame and defines size of displayed frame.
	 */
	private void init(){
		//Base additions and settings for the form
		setTitle("Registration");
		setSize(displayWidth, displayHeight);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());	
	} //End init
	
	/*
	 * Defines Layout and adds needed object to display the information imported from 
	 *   the calling class
	 * Loops through the people ArrayList and adds a new JLable to the form for each 
	 *   instance object of Person found
	 * Adds final panels to the main Frame 
	 */
	private void basicLayout(ArrayList<Games> games, ArrayList<Language> languages) {
		// Base Form Layout
		selectedGameDisplay = new JLabel(selGameLabelText);
				
		//Creates main display Panels for Frame
		westPanel = new JPanel();
		westPanel.setBorder(BorderFactory.createTitledBorder("Games"));
		westPanel.setPreferredSize(new Dimension(240, displayHeight));
		westPanel.setLocation(0, 0);
		westPanel.setLayout(new GridLayout(1,games.size(),5,5));
	
		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createTitledBorder("Description"));
		centerPanel.setPreferredSize(new Dimension(200, displayHeight));
		centerPanel.setLocation(241, 0);
		centerPanel.setLayout(new GridLayout(1,languages.size(),5,5));
		
		eastPanel = new JPanel();
		eastPanel.setBorder(BorderFactory.createTitledBorder("Languages"));
		eastPanel.setPreferredSize(new Dimension(240, displayHeight));
		eastPanel.setLocation(441, 0);
		eastPanel.setLayout(new GridLayout(1,languages.size(),5,5));
		
		//Creates new gridPanel
		westPanelInsert = new JPanel(new GridLayout(games.size(), 2));
		eastPanelInsert = new JPanel((new GridLayout(languages.size(), 2)));
		centerPanelInsert = new JTextArea("");
		
		//Loop through people(student) list and add to westPanelInsert
		countInt = 0;
		gameButton = new JButton[games.size()];
		for (Object Games : games) {
			if (Games instanceof Games) {
				gameButton[countInt] = (JButton) new GameButton((Games) Games);
				westPanelInsert.add(gameButton[countInt]);
				countInt++;
			}
		}
		
		//Loop through Languages list and add to eastPanelInsert
		countInt = 0;
		languageButton = new JButton[languages.size()];
		for (Object Language : languages) {
			if (Language instanceof Language) {
				languageButton[countInt] = (JButton) new LanguageButton((Language) Language);
				eastPanelInsert.add(languageButton[countInt]);
				countInt++;
			}
		}
		
		//Add panels to frame
		westPanel.add(westPanelInsert);
		eastPanel.add(eastPanelInsert);
		centerPanel.add(centerPanelInsert);
		add(westPanel, BorderLayout.WEST);
		add(centerPanel,BorderLayout.CENTER);
		add(eastPanel,BorderLayout.EAST);
		add(selectedGameDisplay, BorderLayout.SOUTH);
	} //end basicLayout method
	
	@SuppressWarnings("serial")
	private class GameButton extends JButton {
		//private static final long serialVersionUID = 1L;
		public GameButton(Games games) {
			setText(((Games) games).getGameDescription());
			addActionListener(new StudentAction());
		} //End StudentButton Constructor
	} //End StudentButton inner class
	
	private class StudentAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton )e.getSource();
			/*
			 * Shows the user the text of the button that was pressed
			 * Show selected Game in label
			 */
			if (source.isEnabled() == true) {
				selectedGameDisplay.setForeground(Color.black);
				currentlySelectedGame = source.getText();
				selectedGameDisplay.setText(selGameLabelText + currentlySelectedGame);
				for (int i=0; i < gameButton.length; i++) {
					if (gameButton[i].isEnabled() == false) {
						gameButton[i].setEnabled(true);
					}
				}
				source.setEnabled(false);
				repaint();
			} 
		} //End actionPerformed method
	} //End StudentAction class
	
	@SuppressWarnings("serial")
	private class LanguageButton extends JButton {
		//private static final long serialVersionUID = 1L;
		public LanguageButton(Language language) {
			setText(((Language) language).getLangName());
			addActionListener(new LanguageAction());
		} //End LanguageButton Constructor
	} //End LanguageButton inner class
		
	private class LanguageAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			/*
			 * Gets the button text and attempts to register the student and
			 * Display the output to the user 
			 */
			currentlySelectedLanguage = source.getText();
			registration();
			repaint();
		} //End actionPerformed method
	} //End LanguageAction
	
	/*
	 * registration method that takes input from button actions
	 * runs logic checks on student selections, registrations and outputs errors
	 *   if needed
	 */
	private void registration() {
		String	checkString;
		try {
			if (currentlySelectedLanguage != null && currentlySelectedGame != null ) {
				checkString = currentlySelectedLanguage + ":\t" + currentlySelectedGame;
				
				if (registrationList.contains(checkString)) {
					// Ignore the duplicate input
				} else {
					/*
					 * Add Currently selected class and student to ArrayList 
					 *   for registration
					 */
					registrationList.add(checkString);
					//Clear Text area prior to population
					centerPanelInsert.setText("");
					// Loop through ArrayList and display in Text area
					for (String outputString : registrationList) {
						centerPanelInsert.append(outputString + "\r\n");
					} //end for
				} //End if (registrationList)
			} 
			else {
				/*
				 *  Display error if no student has been selected prior to attempted
				 *  registration 
				 */
				selectedGameDisplay.setForeground(Color.red);
				selectedGameDisplay.setText(selGameLabelText +  "Error - No Game Selected!!!");
			} //End if (null check)
		} //End try
		catch (Exception ex) {
			System.out.println("Caught Exception: " + ex.getMessage());
		} // End Catch
	} // End registration method

	@Override
	public void paint(Graphics g) {
		super.paint(g);	
	}//End paint

} //End UserInterface class
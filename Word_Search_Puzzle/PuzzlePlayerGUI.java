


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 * Primary java class for GUI layout and 
 * for user interactions / action listeners
 *  
 * @author Vitaly Sots
 * 
 * NOTE:  HTML used to launch an applet can embed arguments in the URL; 
 * For example "http://...../puzzle.html?id=36" is intendeing to launch a puzzle with ID = "36"
 * 
 * Pseudo Code:
 * 1. Process the URL and set the puzzle ID (if exists) through PuzzlePicker
 * 2. Read the puzzle.txt and populate the PuzzleCollection
 * 4. Get the puzzle to be played based on the puzzleID; if null, get a random puzzle
 * 5. Create a PuzzleTracker object based on the Puzzle
 * 6. Build the GUI gird based on puzzle grid height and width
 * 7. Attach the Listeners to the cells to recognize the MouseUp / MouseDown / MouseMove events
 * 8. Populate the grid content (cells and words) based on puzzle and PuzzleConfig
 * 9. Play the game.
 * 10. Upon completion of the game, put out the gameCompleteMsg and give the option to the user to play the next game
 * 11. Present the next puzzle in the sequence (in the same order the puzzles are present in the input file)
 * 12. Terminate the game if the user doesn't wish to continue or kills the browser 
 * 
 */
public class PuzzlePlayerGUI extends JPanel implements MouseListener, ComponentListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jlbTitle = new JLabel();
	private JLabel jlbTime;
	private JButton[][] jlbGrid;
	private ArrayList<JLabel> jlbWord = new ArrayList<JLabel>();
	private ArrayList<JButton> foundWords = new ArrayList<JButton>();
	private PuzzleTracker tracker;
	private JPanel sidePanel;
	private BackgroundPanel wordsPanel;
	private BackgroundPanel timerPanel;
		
	
	
	private boolean leftButtonIsDown = false;
	Stack<JButton> selectedButtons = new Stack<JButton>();
	private String selectedWord = "";
	
	//a component location on the grid
	int[] xy1 = new int[2];
	int[] xy2 = new int[2];
	
	//directions
	private static final int NORTH = 0;
	private static final int NORTH_WEST = 1;
	private static final int WEST = 2;
	private static final int SOUTH_WEST = 3;
	private static final int SOUTH = 4;
	private static final int SOUTH_EAST = 5;
	private static final int EAST = 6;
	private static final int NORTH_EAST = 7;
	
	//timer		
	private final ClockListener cl = new ClockListener();
	private final Timer time = new Timer(1000, cl);
	private final JLabel jlbTimer = new JLabel("00:00:00");
	
	//a grid size
	private int gridWidth;
	private int gridHeight;
	
	// tracking the puzzles
	Puzzle currentPuzzle;
	Puzzle  nextPuzzle;
	
	public PuzzlePlayerGUI (Puzzle a_puzzle)  {	
		
		// setting the current puzzle
		currentPuzzle = a_puzzle;
		
		Font giduguFont = null;
		try
		{
		//  giduguFont = Font.createFont(Font.TRUETYPE_FONT, 
	    //        new File("src/Gidugu.ttf"));
		  giduguFont = Font.createFont(Font.TRUETYPE_FONT, 
		              		  getClass().getClassLoader().getResourceAsStream("Gidugu.ttf"));
		  
		  giduguFont=giduguFont.deriveFont(20.0f);
		}
	
		catch (FontFormatException x)
		{
			System.out.println("Something has gone wrong while constructing the font - FontFormatException");
		}
		catch (IOException x)
		{
			System.out.println("Something has gone wrong while constructing the font - IOException");
		}
	
		
		
		tracker = new PuzzleTracker(a_puzzle);
		
		gridWidth = a_puzzle.getGridWidth();
		gridHeight = a_puzzle.getGridHeight();
		
		BufferedImage background = null;
		try {
			background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("b10.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Create a gridPanel************************************************************
		JPanel gridPanel = new JPanel();		
		gridPanel.setLayout(new GridLayout(gridHeight, gridWidth, 2, 2));
		gridPanel.setBackground(Color.BLACK);
		gridPanel.addComponentListener(this);
		jlbGrid = new JButton [gridHeight] [gridWidth];
		String[][] grid = a_puzzle.getPuzzleGrid();
			
		
		for(int x = 0; x < gridHeight; x++){
            for(int y = 0; y < gridWidth; y++){
                    
            	jlbGrid[x][y]=new JButton(grid[x][y]); //creates a new label               
                gridPanel.add(jlbGrid[x][y]); //adds labels to the grid
                jlbGrid[x][y].addMouseListener(this);
                jlbGrid[x][y].setFont(giduguFont);
            }
		}
		
		
		//Create a sidePanel************************************************************
		sidePanel = new JPanel();
		sidePanel.setLayout(null);		
		sidePanel.setPreferredSize(new Dimension(300,400));
		
		//Create a titlePanel***********************************************************
		BackgroundPanel titlePanel = new BackgroundPanel(background, 1);		
		jlbTitle.setText(a_puzzle.getTitle());		
		jlbTitle.setFont(giduguFont);
		jlbTitle.setForeground(PuzzleConfig.colorTitle);
		titlePanel.add(jlbTitle);		
		sidePanel.add(titlePanel);
		Insets insets = sidePanel.getInsets();
		titlePanel.setBounds(insets.left, insets.top, 300, 50);		
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0 , 15));
		
		//Create a wordsPanel***********************************************************
		wordsPanel = new BackgroundPanel(background, 1);
		//checks how many columns will be shown
		int colWord = 1;		
		int numWords = a_puzzle.getWordList().size();			
		
		wordsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(1, 1, 1, 20);
		c.fill = GridBagConstraints.VERTICAL;
		
		for(int x = 0; x < numWords ; x++) {
			JLabel label = new JLabel();
			label.setText(a_puzzle.getWordList().get(x));
			label.setFont(giduguFont);
			label.setForeground(PuzzleConfig.colorWords);
			label.setHorizontalAlignment(JLabel.LEFT);			
			jlbWord.add(label);			
			wordsPanel.add(label, c);
			
			if (colWord < PuzzleConfig.maxNumberOfWordsPerColumn)
				c.gridy++;
			else {
				c.gridx ++;
				c.gridy = 0;
				colWord = 0;
			}
			
			colWord++;			
		}
		
		sidePanel.add(wordsPanel);
		wordsPanel.setBounds(0, 50, 300, 280);
		
		//Create a timerPanel***********************************************************
		timerPanel = new BackgroundPanel(background, 1);
		jlbTime = new JLabel("time");
		jlbTime.setForeground(Color.YELLOW);
		jlbTime.setFont(giduguFont);
		jlbTimer.setFont(giduguFont);
		jlbTimer.setForeground(Color.WHITE);
		timerPanel.add(jlbTime);
		timerPanel.add(jlbTimer);				
		sidePanel.add(timerPanel);
		timerPanel.setBounds(0, 330, 300, 70);
		time.start();
		
		
		
		//add panels to the frame
		setLayout(new BorderLayout());
		add(gridPanel, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.EAST);		
		
	}//end constructor PuzzlePlayerGUI	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
		if (leftButtonIsDown){
			
			selectedWord = "";
			unMarkButton();
			
			xy2 = getPosition(e.getComponent());
			getDirection();			
		}		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		leftButtonIsDown = true;
	
		xy1 = getPosition(e.getComponent());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		leftButtonIsDown = false;
		if (isWordAlreadyFound(selectedWord)) {
			unMarkButton();
			return;
		}
		
		//checks is the word in the list
		if (tracker.isWordInTheList(selectedWord)) {
			
			while (selectedButtons.isEmpty() == false) {				
				JButton button = selectedButtons.pop();				
				button.setBackground(PuzzleConfig.cellColorWordFound);
				foundWords.add(button);
			}
			
			for(int i = 0; i < jlbWord.size(); i++) {
				String string = jlbWord.get(i).getText().replaceAll("\\s+","");
				if (string.equals(selectedWord.replaceAll("\\s+",""))) 
					jlbWord.get(i).setForeground(Color.GREEN);				
			}
			// Checks whether all words have been found by the user
			if (tracker.isGameComplete())
				endGame();
		}//end if		
		
		else { 
			unMarkButton();
		}
		
		selectedWord = "";
	}
	
	private int[] getPosition (Component a_component) {
		
		int[] xy = new int[2];
		for(int x = 0; x < gridHeight; x++){
            for(int y = 0; y < gridWidth; y++){
                    
            	if (jlbGrid[x][y] == a_component) {            		
            		xy[0] = x;
            		xy[1] = y;
            	}
            }
		}
		return xy;
	}// end getPosition
	
	private void getDirection () {
		
		int x1,x2, y1, y2;
		x1 = xy1[0];
		y1 = xy1[1];
		x2 = xy2[0];
		y2 = xy2[1];
		
		//horizontal
		if (x1 == x2){
			if (y1 < y2)/*East*/{
				selectButton(x1, y1, x2, y2, EAST);				
			}
			else if (y1 > y2)/*West*/
				selectButton(x1, y1, x2, y2, WEST);
		}
		
		//vertical
		else if (y1 == y2){
			if (x1 < x2)/*South*/
				selectButton(x1, y1, x2, y2, SOUTH);
			else if (x1 > x2)/*North*/
				selectButton(x1, y1, x2, y2, NORTH);
		}
		
		//diagonal
		else if (x1 > x2 && y1 > y2) /*North West*/
			selectButton(x1, y1, x2, y2, NORTH_WEST);
		
		else if (x1 > x2 && y1 < y2) /*North East*/
			selectButton(x1, y1, x2, y2, NORTH_EAST);
		
		else if (x1 < x2 && y1 > y2) /*South West*/
			selectButton(x1, y1, x2, y2, SOUTH_WEST);
		
		else if (x1 < x2 && y1 < y2) /*South East*/
			selectButton(x1, y1, x2, y2, SOUTH_EAST);
	}//end getDirection
	
	private void selectButton (int x1, int y1, int x2, int y2, int a_direction) {
		
		switch (a_direction) {
		case NORTH:
			while (x1 >= x2) {
				markButton(x1, y1);				
				x1--;
			}			
			break;
		case SOUTH:
			while (x1 <= x2) {
				markButton(x1, y1);				
				x1++;
			}			
			break;
		case WEST:
			while (y1 >= y2) {
				markButton(x1, y1);				
				y1--;
			}			
			break;
		case EAST:
			while (y1 <= y2) {
				markButton(x1, y1);			
				y1++;
			}			
			break;
		case NORTH_WEST:
			while (y1 >= y2 && x1 > -1) {
				markButton(x1, y1);				
				x1--; y1--;
			}			
			break;
		case NORTH_EAST:
			while (y1 <= y2 && x1 > -1) {
				markButton(x1, y1);				
				x1--; y1++;
			}			
			break;
		case SOUTH_WEST:
			while (y1 >= y2 && x1 < jlbGrid.length) {
				markButton(x1, y1);				
				x1++; y1--;
			}			
			break;
		case SOUTH_EAST:
			while (y1 <= y2 && x1 < jlbGrid.length) {
				markButton(x1, y1);				
				x1++; y1++;
			}			
			break;
		}//end switch
		
	}//end markButton
	
	private void markButton(int row, int col) {
		
		jlbGrid[row][col].setForeground(PuzzleConfig.textColorSelected);
		jlbGrid[row][col].setBackground(PuzzleConfig.cellbgColorSelected);
		selectedButtons.push(jlbGrid[row][col]);
		selectedWord += jlbGrid[row][col].getText();
		
	}//end markButton
	
	private void unMarkButton () {
		
		while (selectedButtons.isEmpty() == false) {
			
			JButton button = selectedButtons.pop();
			if (isFound(button)){
				button.setForeground(PuzzleConfig.textColorSelected);
				button.setBackground(PuzzleConfig.cellColorWordFound);
			}
			else {
				button.setForeground(PuzzleConfig.textColorUnselected);
				button.setBackground(UIManager.getColor("Button.background"));
			}
		}
		
	}
	
	private boolean isFound (JButton a_button) {
		for (int i =0; i < foundWords.size(); i++){
			if (a_button == foundWords.get(i))
				return true;
		}
		return false;
	}
	
	private void endGame () {
		
		time.stop();
		int answer = JOptionPane.showConfirmDialog(null,tracker.getGameStatusMsg(), "Awesome!!!",	
				JOptionPane.YES_NO_OPTION);
		switch (answer) {
		case JOptionPane.OK_OPTION :
			removeAll();
			//GameTracker gt = new GameTracker();
			Puzzle puzzle = PuzzleCollection.getAllPuzzles().get(PuzzleCollection.nextIndex());						
			add(new PuzzlePlayerGUI(puzzle));
			revalidate();
			repaint();
			break;
		case JOptionPane.NO_OPTION :
			removeAll();
			// TODO: Save the current puzzle ID to a text file so that it can be used to set the puzzle next time
		  	System.exit(0);
			break;
		}//end switch
		
	}//end endGame
	
	public static void errorMessage (String a_string) {
		
		JOptionPane.showMessageDialog(null, a_string, "Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
		
	}
	
public boolean isWordAlreadyFound (String a_word) {
		
		for (int i = 0; i < jlbWord.size(); i++)
		{
			Color color = jlbWord.get(i).getForeground();
			if (color.equals(Color.GREEN) && a_word.equals(jlbWord.get(i).getText().replaceAll("\\s+", "")))
				return true;
		}
		return false;		
	}
	
	
	private class ClockListener implements ActionListener {

	    private int hours;
	    private int minutes;
	    private int seconds;
	    private String hour;
	    private String minute;
	    private String second;
	    private static final int N = 60;	

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        NumberFormat formatter = new DecimalFormat("00");
	        if (seconds == N) {
	            seconds = 00;
	            minutes++;
	        }

	        if (minutes == N) {
	            minutes = 00;
	            hours++;
	        }
	        hour = formatter.format(hours);
	        minute = formatter.format(minutes);
	        second = formatter.format(seconds);
	        jlbTimer.setText(String.valueOf(hour + ":" + minute + ":" + second));
	        seconds++;
	    }
	}
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {		
		
		Dimension sidePanelSize = sidePanel.getSize();		
		wordsPanel.setBounds(0, 50,	sidePanelSize.width, sidePanelSize.height - 50 - 70);
		Dimension wordsPanelSize = wordsPanel.getSize();
		timerPanel.setBounds(0, wordsPanelSize.height + 50, sidePanelSize.width, 70);		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}//end class PuzzlePlayerGUI

 
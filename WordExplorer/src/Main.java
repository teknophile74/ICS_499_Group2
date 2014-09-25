
/*	ICS499
 *  Group 2
 *  Main.java
 *  This is the Main program the starts program and builds GUI
 *  Please make sure jl1.0.1.jar is included in project build path
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javazoom.jl.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class Main implements ActionListener {
	private static final long serialVersionUID = 1L;

	//Creates and grabs instance of BackendSystem
	BackendSystem bS = BackendSystem.getInstance();

	// Strings
	private String englishInEnglish;
	private String langInLang;
	private String englishInLang;
	private String langInEnglish;
	private String imageURI;
	private String infoURI;
	private String soundURIOfEnglish;
	private String soundURIOfLang;
	protected Scanner scanner1;
	private String configSEng, configSLang, configImage, configInfo;
	private String tempS[] = new String[15];
	private String[] themeList = { "All Words" };
	private String[] tempList;
	private List<String> themesInEnglish;

	protected String temp = "";
	protected String themeSelection;
	
	static int curser;
	int counter;

	private File file, picFile;
	
	Image wordPicture;
	Image wordPicture2;
	
	final JFileChooser chooser = new JFileChooser();
	
	WordList wordList;

	// JPanels and JFrames
	JFrame mainFrame;
	JPanel panel1, panel2;
	JPanel topPanel;
	JPanel middlePanel;
	JPanel explorerPanel;
	JPanel leftExplorerPanel, rightExplorerPanel, middleExplorerPanel;

	// JLabels
	JLabel selectFileLabel;
	JLabel selectThemeLabel;
	JLabel blankLabel;
	JLabel picLabel;

	// Buttons

	JButton fileButton, executeButton;
	JButton nextButton, backButton;

	// Fields and combos
	JButton eIEText, lILText;
	JLabel eILText, lIEText;

	JComboBox<String> themeCombo;

	public static void main(String[] args) {

		curser = 0;

		Main m = new Main();

		m.readConfig();

		m.mainFrame();

	}

	public void mainFrame() {

		mainFrame = new JFrame("Word Explorer");
		mainFrame.setLayout(new GridLayout(2, 1));
		mainFrame.setSize(900, 600);

		panel1 = new JPanel(new GridLayout(2, 1));
		panel2 = new JPanel(new GridLayout(1, 1));

		topPanel = new JPanel(new GridLayout(3, 2));
		middlePanel = new JPanel();

		explorerPanel = new JPanel(new GridLayout(1, 3));

		leftExplorerPanel = new JPanel(new GridLayout(3, 1));
		middleExplorerPanel = new JPanel(new BorderLayout());
		rightExplorerPanel = new JPanel(new GridLayout(3, 1));

		selectFileLabel = new JLabel("Select File:");
		selectFileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectThemeLabel = new JLabel("Select Theme:");
		selectThemeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fileButton = new JButton("Choose file");
		themeCombo = new JComboBox<String>(themeList);
		blankLabel = new JLabel("");
		executeButton = new JButton("Execute");
		backButton = new JButton("Back");
		nextButton = new JButton("Next");

		backButton.setVerticalAlignment(SwingConstants.CENTER);

		eIEText = new JButton();

		eIEText.setHorizontalAlignment(SwingConstants.CENTER);
		eIEText.setBorderPainted(false);
		eIEText.setOpaque(false);
		eIEText.setBackground(Color.WHITE);

		lILText = new JButton();

		lILText.setHorizontalAlignment(SwingConstants.CENTER);
		lILText.setBorderPainted(false);
		lILText.setOpaque(false);
		lILText.setBackground(Color.WHITE);

		eILText = new JLabel();
		eILText.setHorizontalAlignment(SwingConstants.CENTER);

		lIEText = new JLabel();
		lIEText.setHorizontalAlignment(SwingConstants.CENTER);

		picLabel = new JLabel();
		picLabel.setHorizontalAlignment(SwingConstants.CENTER);
		picLabel.setSize(200, 200);

		// ActionListeners
		fileButton.addActionListener(this);
		executeButton.addActionListener(this);

		generateFirstRun();

		picFile = new File(configImage + "default.jpg");
		try {
			wordPicture = ImageIO.read(picFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Image resizedImage = wordPicture.getScaledInstance(picLabel.getWidth(),
				picLabel.getHeight(), 0);

		picLabel.setIcon(new ImageIcon(resizedImage));

		// Add to topPanel
		topPanel.add(selectFileLabel);
		topPanel.add(fileButton);
		topPanel.add(selectThemeLabel);
		topPanel.add(themeCombo);
		topPanel.add(blankLabel);
		topPanel.add(executeButton);

		// Add to leftExplorerPanel
		leftExplorerPanel.add(backButton);
		leftExplorerPanel.add(eIEText);
		leftExplorerPanel.add(eILText);

		// Add to rightExplorerPanel
		rightExplorerPanel.add(nextButton);
		rightExplorerPanel.add(lILText);
		rightExplorerPanel.add(lIEText);

		// Add to middelExplorerPanel
		middleExplorerPanel.add(picLabel, BorderLayout.CENTER);

		// Add to explorerPanel
		explorerPanel.add(leftExplorerPanel);
		explorerPanel.add(middleExplorerPanel);
		explorerPanel.add(rightExplorerPanel);

		// Add to panel1+2
		panel1.add(topPanel);
		panel1.add(middlePanel);
		panel2.add(explorerPanel);
		// Add to mainFrame

		mainFrame.add(panel1);
		mainFrame.add(panel2);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public List<String> getThemesEngList() {
		themesInEnglish = bS.getEngList();
		List<String> tempList = new ArrayList<String>();
		tempList.add("All Words");

		int counter = 0;
		while (counter < themesInEnglish.size()) {
			tempList.add(themesInEnglish.get(counter));
			counter++;
		}
		return tempList;

	}

	// Generates Results based on GUI
	public void generateResults() {
		
		Word tempWord = wordList.getWord(curser);

		englishInEnglish = tempWord.getEnglishInEnglish();
		langInLang = tempWord.getLangInLang();
		englishInLang = tempWord.getEnglishInLang();
		langInEnglish = tempWord.getLangInEnglish();
		imageURI = tempWord.getImageURI();
		infoURI = tempWord.getInfoURI();
		soundURIOfEnglish = tempWord.getSoundURIOfEnglish();
		soundURIOfLang = tempWord.getSoundURIOfLang();


		eIEText.removeActionListener(this);

		lILText.removeActionListener(this);

		if (soundURIOfEnglish.length() > 2) {
			eIEText.setText("<HTML><FONT color=\"#000099\"><center><br><u>"
					+ englishInEnglish + "</u></center></FONT></HTML>");
			eIEText.addActionListener(this);
		} else {
			eIEText.setText("<HTML><FONT color=\"#000099\"><center><br>"
					+ englishInEnglish + "</center></FONT></HTML>");

		}

		if (soundURIOfLang.length() > 2) {
			lILText.setText("<HTML><FONT color=\"#000099\"><center><br><U>"
					+ langInLang + "</u></center></FONT></HTML>");
			lILText.addActionListener(this);
		} else {
			lILText.setText("<HTML><FONT color=\"#000099\"><center><br>"
					+ langInLang + "</center></FONT></HTML>");

		}

		lIEText.setText("<HTML><FONT color=\"#000099\"><center><br>"
				+ langInEnglish + "</center></FONT></HTML>");
		eILText.setText("<HTML><FONT color=\"#000099\"><center><br>"
				+ englishInLang + "</center></FONT></HTML>");

	
		if (imageURI.length() > 2) {
			
			if (imageURI.startsWith("http")) {
				try {
					URL imageURL = new URL(imageURI);
					wordPicture2 = ImageIO.read(imageURL);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					picFile = new File(configImage + "notfound.jpg");
					try {
						wordPicture2 = ImageIO.read(picFile);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					
				}
			} else {

				File tempFile = new File(configImage + imageURI);
				try {
					wordPicture2 = ImageIO.read(tempFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (wordPicture2 != null) {
				Image resizedImage = wordPicture2
						.getScaledInstance(200, 200, 0);

				picLabel.setIcon(new ImageIcon(resizedImage));
				picLabel.revalidate();
				picLabel.repaint();
			}

		} else {
			picFile = new File(configImage + "notfound.jpg");
			try {
				wordPicture2 = ImageIO.read(picFile);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Image resizedImage = wordPicture2.getScaledInstance(200, 200, 0);

			picLabel.setIcon(new ImageIcon(resizedImage));
			picLabel.revalidate();
			picLabel.repaint();

		}
		counter = 0;
		MouseAdapter imageM = new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				
				if(infoURI.startsWith("http")){ 
					 try { 
						 URL tempURL = new URL(infoURI); 
						 if(counter < 1){
							 openWebpage(tempURL); 
							 counter++;
						 }
				  } catch(MalformedURLException e) { 
				  e.printStackTrace(); } 
				}else{			  
				  
					  URI tempURI; 
					  try { 
					  tempURI = new URI(configInfo + infoURI);
					  	if(counter < 1){
					  		openWebpage(tempURI);
					  		counter++;
					  	}
					  } catch (URISyntaxException e) { //
			
				  
					  }
				 }
				}
				
				};
				
		picLabel.removeMouseListener(imageM);

		if (infoURI.length() > 2) {
			picLabel.addMouseListener(imageM);
		} else {

		}

		mainFrame.revalidate();
		mainFrame.repaint();

	}

	// Generates Results for first run
	public void generateFirstRun() {


		eIEText.setText("<HTML><FONT color=\"#000099\"><U><center>englishInEnglish</center></U></FONT></HTML>");

		lILText.setText("<HTML><FONT color=\"#000099\"><U><center>langInLang</center></U></FONT></HTML>");

		eILText.setText("englishInLang");
		lIEText.setText("langInEnglish");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<String> themesList = new ArrayList<String>();
		// TODO Auto-generated method stub
		if (e.getSource() == fileButton) {
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Select File");

			int returnVal = chooser.showOpenDialog(mainFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				bS.scanFile(file);
				themesList = getThemesEngList();	
				int counter = 1;
				while (counter < themesList.size()) {
					themeCombo.addItem(themesList.get(counter));
					counter++;
				}

				
			} else {
				System.out.println("Open command cancelled by user.");
			}
	

		} else if (e.getSource() == executeButton) {

			themeSelection = (String) themeCombo.getSelectedItem();
			bS.randomN(themeSelection);
			wordList = bS.getTempList();
			curser = 0;
			backButton.addActionListener(this);
			nextButton.addActionListener(this);
			generateResults();

		} else if (e.getSource() == backButton) {
			if (curser == 0) {
				curser = wordList.getSize() - 1;
			} else {
				curser--;
			}

			generateResults();

		} else if (e.getSource() == nextButton) {

			if (curser >= wordList.getSize() - 1) {
				curser = 0;
			} else {
				curser++;
			}

			generateResults();

		} else if (e.getSource() == eIEText) {
			
			
			FileInputStream fis;
			InputStream is;
	
			if (soundURIOfEnglish.startsWith("http")
					&& soundURIOfEnglish.endsWith(".mp3")) {

				try {

					is = new URL(soundURIOfEnglish).openStream();
					BufferedInputStream bis = new BufferedInputStream(is);

					try {
						Player player = new Player(bis);

						player.play();

					} catch (JavaLayerException jl) {

					}

				} catch (IOException ex) {

				}

			} else if (!soundURIOfEnglish.startsWith("http")
					&& soundURIOfEnglish.endsWith(".mp3")) {

				try {
					File file = new File(configSEng + soundURIOfEnglish);
					fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);

					try {
						Player player = new Player(bis);

						player.play();

					} catch (JavaLayerException jl) {

					}

				} catch (IOException ex) {

				}
			} else if (soundURIOfEnglish.startsWith("http") && soundURIOfEnglish.endsWith(".wav")) {
				try {
						URL url1 = new URL(soundURIOfEnglish);
					 
				        Clip clip1 = AudioSystem.getClip();
				        AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(url1);
				        clip1.open(audioIn1);
				        clip1.start();

				} catch (Exception ex) {
				}
				
				
			} else if (!soundURIOfEnglish.startsWith("http") && soundURIOfEnglish.endsWith(".wav")) {

				try {
					File file2 = new File(configSEng + soundURIOfEnglish);
					 
				        Clip clip2 = AudioSystem.getClip();
				        AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(file2);
				        clip2.open(audioIn2);
				        clip2.start();

				} catch (Exception ex) {
				}

			

		}

		} else if (e.getSource() == lILText) {
		
			FileInputStream fis;
			InputStream is;
			if (soundURIOfLang.startsWith("http")
					&& soundURIOfLang.endsWith(".mp3")) {

				try {

					is = new URL(soundURIOfLang).openStream();
					BufferedInputStream bis = new BufferedInputStream(is);

					try {
						Player player = new Player(bis);

						player.play();

					} catch (JavaLayerException jl) {

					}

				} catch (IOException ex) {

				}

			} else if ((!soundURIOfLang.startsWith("http"))
					&& soundURIOfLang.endsWith(".mp3")) {

				try {
					File file = new File(configSLang + soundURIOfLang);
					fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);

					try {
						Player player = new Player(bis);

						player.play();

					} catch (JavaLayerException jl) {

					}

				} catch (IOException ex) {

				}
			} else if (soundURIOfLang.startsWith("http") && soundURIOfLang.endsWith(".wav")) {
				
				
				try {
						
					
					URL url1 = new URL(soundURIOfLang);
					
					 
			        Clip clip1 = AudioSystem.getClip();
			        AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(url1);
			        clip1.open(audioIn1);
			        clip1.start();
				   
				   
				} catch (Exception ex) {
				}
				
				
			} else if ((!soundURIOfLang.startsWith("http")) && soundURIOfLang.endsWith(".wav")) {

				try {
					File file4 = new File(configSLang + soundURIOfLang);
					 
				        Clip clip4 = AudioSystem.getClip();
				        AudioInputStream audioIn = AudioSystem.getAudioInputStream(file4);
				        clip4.open(audioIn);
				        clip4.start();

				} catch (Exception ex) {
				}

			}

		}

	}

	public void readConfig() {
		Scanner scanner1 = null;
		File file = new File("config.txt");

		try {
			scanner1 = new Scanner(file, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner1.reset();

		int count = 0;
		while (scanner1.hasNext()) {
			tempS[count] = scanner1.next();
			count++;
		}

		configSEng = tempS[2];
		configSLang = tempS[5];
		configImage = tempS[8];
		configInfo = tempS[11];

		System.out.println("Config File:");
		System.out.println(configSEng);
		System.out.println(configSLang);
		System.out.println(configImage);
		System.out.println(configInfo);

	}

	public void selectedFile(File tempFile) {

		bS.scanFile(tempFile);
	}

	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop()
				: null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void openWebpage(URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}

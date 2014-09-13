import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import java.net.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.border.BevelBorder;
import java.util.ArrayList;

public class WordExplorerGUI extends JFrame {

	private static final long serialVersionUID = -4587231172598490588L;
	
	@SuppressWarnings("rawtypes")
	private EntireWordCollection ewc;
	
	private WordCollection themedWordsController;
	
	private Word currentWord;
		
	private JPanel contentPane;
	private JComboBox themesComboBox;
	private JButton btnNext;
	private JButton btnPrevious;
	private JLabel englishInEnglish_Sound_Label;
	private JLabel langInEnglish_Sound_Label;
	private JLabel langInEnglishLabel;
	private JLabel englishInLangLabel;
	private JLabel lblTheme;
	private JLabel picLabel;
	
	private static final String INPUT_FILE_NAME = "src/test_file_1.txt";
	private static final String DEFAULT_IMAGE = "src/Image Not Available.png";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordExplorerGUI frame = new WordExplorerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public WordExplorerGUI() {
		setTitle("Word Explorer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1199, 615);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Build the word collection
		ewc = new EntireWordCollection(INPUT_FILE_NAME);
	
		themesComboBox = new JComboBox(ewc.getUniqueThemesInEnglish().toArray());
		themesComboBox.setBounds(524, 540, 126, 27);
		contentPane.add(themesComboBox);

		btnNext = new JButton("Next==>");
		btnNext.setBounds(1049, 522, 117, 29);
		contentPane.add(btnNext);

		btnPrevious = new JButton("<==Back");
		btnPrevious.setBounds(30, 522, 117, 29);
		contentPane.add(btnPrevious);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(20, 20, 1159, 490);
		contentPane.add(panel);
		panel.setLayout(null);

		englishInEnglish_Sound_Label = new JLabel("English Word and Sound");
		englishInEnglish_Sound_Label.setFont(new Font("Arial", Font.PLAIN, 23));
		englishInEnglish_Sound_Label.setBounds(6, 113, 320, 38);
		panel.add(englishInEnglish_Sound_Label);
		englishInEnglish_Sound_Label.setForeground(Color.BLUE);  
		englishInEnglish_Sound_Label.setCursor(new Cursor(Cursor.HAND_CURSOR));

		englishInLangLabel = new JLabel("English in Lang");
		englishInLangLabel.setFont(new Font("Suguna", Font.PLAIN, 23));
		englishInLangLabel.setBounds(6, 218, 320, 38);
		panel.add(englishInLangLabel);
	//	englishInLangLabel.setFont(new Font("Arial", Font.PLAIN, 23));
		englishInLangLabel.setForeground(Color.RED);  

		langInEnglish_Sound_Label = new JLabel("Language Word and Sound");
		langInEnglish_Sound_Label.setFont(new Font("Suguna", Font.PLAIN, 23));
		langInEnglish_Sound_Label.setBounds(833, 113, 320, 38);
		panel.add(langInEnglish_Sound_Label);
		langInEnglish_Sound_Label.setHorizontalAlignment(JLabel.RIGHT);
		langInEnglish_Sound_Label.setForeground(Color.BLUE);  
		langInEnglish_Sound_Label.setCursor(new Cursor(Cursor.HAND_CURSOR));

		langInEnglishLabel = new JLabel("Language in English");
		langInEnglishLabel.setFont(new Font("Arial", Font.PLAIN, 23));
		langInEnglishLabel.setBounds(833, 218, 320, 38);
		langInEnglishLabel.setForeground(Color.RED); 
		panel.add(langInEnglishLabel);
		langInEnglishLabel.setHorizontalAlignment(JLabel.RIGHT);
		langInEnglishLabel.setFont(new Font("Suguna", Font.PLAIN, 23));

		picLabel = new JLabel("Picture Will be displayed here; \nPlease select a theme");
		picLabel.setBounds(338, 6, 471, 464);
		picLabel.setHorizontalAlignment(JLabel.CENTER);
		picLabel.setVerticalAlignment(JLabel.CENTER);
		panel.add(picLabel);

		lblTheme = new JLabel("Choose a theme:");
		lblTheme.setBounds(534, 522, 144, 16);
		contentPane.add(lblTheme);

	
        // Now add the action Listeners to all GUI components
		themesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				resetIterator((String)cb.getSelectedItem());
			}
		});
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextWord();
			}
		});
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevWord();
			}
		});

		englishInEnglish_Sound_Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playSound(0);
			}
		});

		langInEnglish_Sound_Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playSound(1);
			}
		});

	}
	
	/*
	 * When a theme is selected, the iterator is reset 
	 * to load the themed word collection 
	 */
	public void resetIterator(String theme)
	{
	    themedWordsController = 
	    		new WordCollection(ewc.getWordCollectionForEnglishTheme(theme));
		nextWord();
	}
	
	
	private void setImage() {
		String path = currentWord.getImageURI();
		if(path.indexOf("http") != -1)
		{

			try {
				URL imageURL = new URL (path);
				Image image = ImageIO.read(imageURL);
				image = image.getScaledInstance(471, 408, 1);
				picLabel.setIcon(new ImageIcon(image));
			} catch (IIOException e) {
				showErrorMessage("Cannot display image.");
			} catch (IOException e) {
				showErrorMessage("Cannot display image.");
			}
		}
		else if(path.indexOf("http") == -1){
			try{
				File img = new File(path);
				Image image = ImageIO.read(img);
				image = image.getScaledInstance(471, 408, 1);
				picLabel.setIcon(new ImageIcon(image));
			}catch (Exception e)
			{
				try{
					File img = new File(DEFAULT_IMAGE);
					Image image = ImageIO.read(img);
					image = image.getScaledInstance(471, 408, 1);
					picLabel.setIcon(new ImageIcon(image));
				}catch (Exception e2)
				{
					showErrorMessage("Cannot display image.");
				}
			}
		}

	}
	public void nextWord()
	{
		Word testWord = themedWordsController.getNextWord();
		if(testWord == null)
		{
			showErrorMessage("End of List");
		}
		else currentWord = testWord;
		
		if (currentWord != null)
		{
			setWords();
			setAudioLinks();
			setImage();
		}
	}
	
	public void prevWord()
	{
		Word testWord = themedWordsController.getPrevWord();
		if(testWord == null)
		{
			showErrorMessage("Beginning of List");
		}
		else currentWord = testWord;
	
		if (currentWord != null)
		{
			setWords();
			setAudioLinks();
			setImage();
		}

	}
	
	/*
	 * Settign the labels
	 */
	
	private void setWords() {
		englishInLangLabel.setText(currentWord.getEnglishInLang());
		langInEnglishLabel.setText(currentWord.getLangInEnglish());
		// we need to set the labels for other two as well
		
	}
	
	/** 
	 * Setting the audio links so that clicking 
	 * on the words/labels will play the sound
	 */

	private void setAudioLinks()
	{
		if(!currentWord.getEnglishSoundURI().equals(""))
		{
			englishInEnglish_Sound_Label.setForeground(Color.BLUE);
			englishInEnglish_Sound_Label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			englishInEnglish_Sound_Label.setText(currentWord.getEnglishInEnglish());
		}
		else {
			englishInEnglish_Sound_Label.setForeground(Color.BLACK);
			englishInEnglish_Sound_Label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			englishInEnglish_Sound_Label.setText(currentWord.getEnglishInEnglish());
		}
		
		if(!currentWord.getLangSoundURI().equals(""))
		{
			langInEnglish_Sound_Label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			langInEnglish_Sound_Label.setForeground(Color.BLUE);
			langInEnglish_Sound_Label.setText(currentWord.getLangInLang());

		}
		else { 
			langInEnglish_Sound_Label.setForeground(Color.BLACK);
			langInEnglish_Sound_Label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			langInEnglish_Sound_Label.setText(currentWord.getLangInLang());
		}
	}
	
	/*
	 * Showing a pop-up message (information or error) using Growler
	 */
	private void showErrorMessage(String message)
	{
		Growler myGrowler = new Growler(message, Color.red, Color.red.darker());
		myGrowler.setLocation(this.getWidth()/2, this.getY()*2);
		myGrowler.setVisible(true);
	}
	
	/*
	 * Showing a pop-up message (success) using Growler
	 */
	private void showSuccessMessage(String message)
	{
		Growler myGrowler = new Growler(message, Color.green, Color.green.darker());
		myGrowler.setLocation(this.getWidth()/2, this.getY()*2);
		myGrowler.setVisible(true);
	}

	/*
	 * Playing the sound of the word
	 */
	private void playSound(int which)
	{
		String path = "";
		switch (which)
		{
			case 0: 
				path = currentWord.getEnglishSoundURI();
				break;
			case 1: 
				path = currentWord.getLangSoundURI();
				break;
		}
		
		// if there is no sound URL, do nothing
		if(path.equals(""))
		{
			return;
		}
		
		// otherwise, try to play the sound
		FileChecker myFile = new FileChecker(path);
		if (myFile.testFilePath())
		{
			try{
				Audio myAudio = new Audio(path);
			}catch(Exception e)
			{
				showErrorMessage("Could not play audio file.");
			}
		}
		else
		{
			showErrorMessage("Could not play audio file.");
		}

	}
}

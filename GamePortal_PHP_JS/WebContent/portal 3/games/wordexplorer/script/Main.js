
/*	ICS499
 *  Group 2 <<best.
 *  Main.java
 *  This is the Main program the starts program and builds GUI
 *  Please make sure jl1.0.1.jar is included in project build path
 */

var currentLang='';
var wordList;

function Main()
{
	var serialVersionUID = 1;

	//Creates and grabs instance of BackendSystem
	var bS = BackendSystem();

	// Strings
	var englishInEnglish,langInLang,englishInLang,langInEnglish;
	var imageURI,infoURI;
	var soundURIOfEnglish,soundURIOfLang;
	var tempWord;
	//protected Scanner scanner1;
	var configSEng, configSLang, configImage, configInfo;
	var tempS = []; //new var[15];
	//private String[] themeList = { "All Words" };
	var themeList= []; //{"All words"};
	//private String[] tempList;
	var tempList = [];
	//private List<String> themesInEnglish;
	var themesInEnglish = []; //new Array();
	var temp = null;   //protected String temp = "";
	var themeSelection = null;    //protected var themeSelection;

	var curser;
	var counter;

	var file, picFile; //File

	var wordPicture;
	var wordPicture2;

	var wordList;

	this.getThemeEngList=function() //public List<String> getThemesEngList() 
  {
		themesInEnglish = bS.getEngList();
	//	List<String> tempList = new ArrayList<String>();
		tempList=[];
		tempList.add("All Words");

		counter = 0;
		while (counter < themesInEnglish.size()) {
			tempList.add(themesInEnglish.get(counter));
			counter++;
		}
		return tempList;

	};

	// Generates Results based on GUI
	this.generateResults=function() {

		//Word tempWord = wordList.getWord(curser);

		englishInEnglish = tempWord.getEnglishInEnglish();
		langInLang = tempWord.getLangInLang();
		englishInLang = tempWord.getEnglishInLang();
		langInEnglish = tempWord.getLangInEnglish();
		imageURI = tempWord.getImageURI();
		infoURI = tempWord.getInfoURI();
		soundURIOfEnglish = tempWord.getSoundURIOfEnglish();
		soundURIOfLang = tempWord.getSoundURIOfLang();
  };

	// Generates Results for first run
	//public void generateFirstRun() {
	this.generateFirstRun=function(){

		eIEText.setText("<HTML><FONT color=\"#000099\"><U><center>englishInEnglish</center></U></FONT></HTML>");

		lILText.setText("<HTML><FONT color=\"#000099\"><U><center>langInLang</center></U></FONT></HTML>");

		eILText.setText("englishInLang");
		lIEText.setText("langInEnglish");

	};

//	@Override
	//public void actionPerformed(ActionEvent e) {
	
	// Action Perform, to do what actionlistener asked to do
	this.actionPerformed=function(){
//		List<String> themesList = new ArrayList<String>();
		var themesList=[];
		// TODO Auto-generated method stub
		if (e.getSource() == fileButton) {
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Select File");

			var returnVal = chooser.showOpenDialog(mainFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				bS.scanFile(file);
				themesList = getThemesEngList();
				var counter = 1;
				while (counter < themesList.size()) {
					themeCombo.addItem(themesList.get(counter));
					counter++;
				}


			} else {
				System.out.println("Open command cancelled by user.");
			}


		} else if (e.getSource() == executeButton) {

			themeSelection = themeCombo.getSelectedItem();
			bS.randomN(themeSelection);
			wordList = bS.getTempList();
			curser = 0;
			backButton.addActionListener(this);
			nextButton.addActionListener(this);
			generateResults();

		} else if (e.getSource() == backButton) {
			if (curser === 0) {
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


			//FileInputStream fis;
			//InputStream is;       

			if (soundURIOfEnglish.startsWith("http") && soundURIOfEnglish.endsWith(".mp3")) {

				try {

					is = new URL(soundURIOfEnglish).openStream();
					//BufferedInputStream bis = new BufferedInputStream(is);

					try {
						//Player player = new Player(bis);

						player.play();

					} catch (jl) {

					}

				} catch (ex) {

				}

			} else if (!soundURIOfEnglish.startsWith("http") && soundURIOfEnglish.endsWith(".mp3")) {

				try {
					//File file = new File(configSEng + soundURIOfEnglish);
					fis = new FileInputStream(file);
					//BufferedInputStream bis = new BufferedInputStream(fis);

					try {
						//Player player = new Player(bis);

						player.play();

					} catch (jl) {

					}

				} catch (ex) {

				}
			} else if (soundURIOfEnglish.startsWith("http") && soundURIOfEnglish.endsWith(".wav")) {
				try {
						//URL url1 = new URL(soundURIOfEnglish);

				        //Clip clip1 = AudioSystem.getClip();
				       // AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(url1);
				        clip1.open(audioIn1);
				        clip1.start();

				} catch (ex) {
				}


			} else if (!soundURIOfEnglish.startsWith("http") && soundURIOfEnglish.endsWith(".wav")) {

				try {
					//File file2 = new File(configSEng + soundURIOfEnglish);

				        //Clip clip2 = AudioSystem.getClip();
				        //AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(file2);
				        clip2.open(audioIn2);
				        clip2.start();

				} catch (ex) {
				}



		}

		} /*else*/ if (e.getSource() == lILText) {

			//FileInputStream fis;
			//InputStream is;
			if (soundURIOfLang.startsWith("http")	&& soundURIOfLang.endsWith(".mp3")) {

				try {

					is = new URL(soundURIOfLang).openStream();
					//BufferedInputStream bis = new BufferedInputStream(is);

					try {
						//Player player = new Player(bis);

						player.play();

					} catch (jl) {

					}

				} catch (ex) {

				}

			} else if ((!soundURIOfLang.startsWith("http"))	&& soundURIOfLang.endsWith(".mp3")) {

				try {
					//File file = new File(configSLang + soundURIOfLang);
					fis = new FileInputStream(file);
					//BufferedInputStream bis = new BufferedInputStream(fis);

					try {
						//Player player = new Player(bis);

						player.play();

					} catch (jl) {

					}

				} catch (ex) {

				}
			} else if (soundURIOfLang.startsWith("http") && soundURIOfLang.endsWith(".wav")) {


				try {


					//URL url1 = new URL(soundURIOfLang);


			       // Clip clip1 = AudioSystem.getClip();
			        //AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(url1);
			        clip1.open(audioIn1);
			        clip1.start();


				} catch (ex) {
				}


			} else if ((!soundURIOfLang.startsWith("http")) && soundURIOfLang.endsWith(".wav")) {

				try {
					//File file4 = new File(configSLang + soundURIOfLang);

				       // Clip clip4 = AudioSystem.getClip();
				       // AudioInputStream audioIn = AudioSystem.getAudioInputStream(file4);
				        clip4.open(audioIn);
				        clip4.start();

				} catch (ex) {
				}

			}

		}

	};

}
	//public void selectedFile(File tempFile) {
	this.selectedFile=function(){

		bS.scanFile(tempFile);
	};

//}

window.onload=function newOnload()
{
  init();
};

function init()
{
  
  // set initially needed values
  var englishInEnglish;
  var langInLang;
  var englishInLang;
  var langInEnglish;
  var imageURI;
  var infoURI;
  var soundURIOfEnglish;
  var soundURIOfLang;
  
  var initialLanguage = WordExBaseConfig.intialLang;
  var intialCategory = WordExBaseConfig.intialCategory;
  
  // Call out to global.js for settings
  PopulatePageDropDownSettings();
  //PopulateLanguageDropDowns();
  //if (!(GetLanguageFromQueryString()))
  //{
    var selectBox = document.getElementById('categories');
    for(var i=0; i < selectBox.length; i++)
    {
      if (intialCategory === (selectBox.options[i].text).toLowerCase())
      {
        selectBox.selectedIndex = i;
      }
    }
  //}
  //var newExplorer = new initializeWordExplorer(window.EnglishTeluguFamily);
  //var newExplorer = new initializeWordExplorer(window.EnglishTeluguCountry);
  
  // Call out to get the initially loaded array
  var currentCatArray = GetCategoryArray();
  
  	if (window[currentCatArray])
	{  
		var newExplorer = new initializeWordExplorer(window[currentCatArray]);
		newExplorer.writeInterface();
	}
  //var BS = new BackendSystem(); 
  //BS.createWord(2);
  // initial parameters used to create the game
  //CreateWord(englishInEnglish,langInLang,englishInLang,langInEnglish,imageURI,infoURI,soundURIOfEnglish,soundURIOfLang);
}

function GetCategoryArray()
{
	var catArrayValue;
	var selectBox = document.getElementById('categories');
	var category = selectBox.options[selectBox.selectedIndex].text;

	category = category.replace(/%20+/g, '_')
	
	selectBox = document.getElementById('primaryLang');
	var primaryLang = selectBox.options[selectBox.selectedIndex].text;
	
	selectBox = document.getElementById('secondaryLang');
	var secondaryLang = selectBox.options[selectBox.selectedIndex].text;
	// Build this string EnglishTelugu_Cartoons
	
	catArrayValue = primaryLang+secondaryLang+'_'+category;
	
	return catArrayValue;
}

function triggerUpdate(newUpdate)
{
	if (newUpdate)
	{
		var selectBox;
		var category = newUpdate.replace(" ", "_");
		
		selectBox = document.getElementById('primaryLang');
		var primaryLang = selectBox.options[selectBox.selectedIndex].text;
		
		selectBox = document.getElementById('secondaryLang');
		var secondaryLang = selectBox.options[selectBox.selectedIndex].text;
		// Build this string EnglishTelugu_Cartoons
		
		var catArrayValue = primaryLang+secondaryLang+'_'+category;

	  	if (window[catArrayValue])
		{  
			var newExplorer = new initializeWordExplorer(window[catArrayValue]);
			newExplorer.writeInterface();
		}
	}
	
	// Add function to be able to switch between English to Telugu and Telugu to English
	// Todo dynamically switch the language
	if (newUpdate)
	{
		var selectBox;
		var category = newUpdate.replace(" ", "_");
		
		selectBox = document.getElementById('secondaryLang');
		var secondaryLang = selectBox.options[selectBox.selectedIndex].text;
		// Build this string EnglishTelugu_Cartoons
		selectBox = document.getElementById('primaryLang');
		var primaryLang = selectBox.options[selectBox.selectedIndex].text;
		
		var catArrayValue = secondaryLang+primaryLang+'_'+category;

	  	if (window[catArrayValue])
		{  
			var newExplorer = new initializeWordExplorer(window[catArrayValue]);
			newExplorer.writeInterface();
		}
	}
	return;
}

function triggerSecondaryLangUpdate(someValue)
{
	if (someValue)
	{
		var selectBox;
		var secLanguage = someValue.replace(" ", "_");
		
		//postback to server - get new page
	}	
}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>SILC Kids' Game Portal</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<meta name="google-translate-customization" content="af6b9d7692908c77-3f792cd3e6682ebb-gca4e872ec56a4455-15"></meta>
	<link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>
<div id="sidebg"></div>
<div class="wrapper">
	<div id="header">
		<h1 id="title"><a href="index.php">Game Portal</a></h1>
		<div id="subtitle">Fall 2014 ICS499-02 Group 2</div>
	</div>
</div>
<div id="google_translate_element">
<div id="nav">English(US) | Hindi | Telugu </div></div>

<script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en', includedLanguages: 'ar,en,fa,hi,ko,ru,te,ur,vi,zh-CN', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');
}
</script><script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>


<div class="wrapper" id="content">
	<div id="games">
	<?php
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
		//get the games directories sub-directories
=======
		//<--get the games directorys subdirectories-->
>>>>>>> af97253 Adding Admin portal + updates to 4P1W game files
=======
		//get the games directories sub-directories
>>>>>>> 533201e Updated index.php code
		$path = 'games';
		$games = glob($path . '/*' , GLOB_ONLYDIR);
		
		//count the number of sub-directories found in 'games'
		$gameCount = count($games);
		//echo "Count of games".$gameCount; 

		//loop through the array for games
		for($i = 0; $i < $gameCount; $i++)
                {
			//get the individual game's folder name
			$theGame = explode("/", $games[$i]);
			$theGame = $theGame[1];
			
			//get the individual game's info page
			//$infoPage = $games[$i]."/".$theGame.".php";
			//include($infoPage);

			$description = $theGame." and stuff...";

			//display information for each game
			$gameInfo = "
				<div class=\"gameBox\">
					<a href=\"games/$theGame/\" class=\"gameImg\">
						<img src=\"games/$theGame/images/$theGame.jpg\" alt=\"$theGame\">
					</a>
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
					<h3><a href=\"games/$theGame/\">$theGame</a></h3>
					<p>$description</p>
				</div>";
		        //display the games
		        echo $gameInfo;
=======
					<h3>
						<a href=\"games/$theGame/\">$theGame</a>
					</h3>
=======
					<h3><a href=\"games/$theGame/\">$theGame</a></h3>
>>>>>>> 533201e Updated index.php code
					<p>$description</p>
				</div>";
<<<<<<< Upstream, based on origin/Prod
			
			//display the games
			echo $gameInfo;
>>>>>>> af97253 Adding Admin portal + updates to 4P1W game files
=======
		        //display the games
		        echo $gameInfo;
>>>>>>> 533201e Updated index.php code
		}
	?>
	</div>
</div>
</body>
</html>

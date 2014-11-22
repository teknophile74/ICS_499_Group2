<?php
include('session.php');
?>
<!DOCTYPE>
<html>
<head>
	<title>Game File Upload Page</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<script src="script/global.js"></script>
</head>
<body>
	<div id="gameSelectionControls">
		<label>Game Selection
			<select id="gameSelector">
				<?php
					//<--get the games directorys subdirectories-->
					$path = '../games';
					$games = glob($path . '/*' , GLOB_ONLYDIR);
					
					//count the number of subdirectories found in 'games'
					$gameCount = count($games);
					
					//loop through the array for games
					for($i = 0; $i < $gameCount; $i++)
					{
						//get the individual game's folder name
						$theGame = explode("/", $games[$i]);
						$theGame = $theGame[1];
			
						//display information for each game
						$gameInfo = "<option value=\"$theGame\">$theGame</option>";
						
						//display the games
						echo $gameInfo;
					}
				?>
			</select>
		</label>
	</div>
	<div id="langSelectionControls">
		<label>Primary Language
			<select id="primaryLang"></select>
		</label>
		<label>Secondary Language
			<select id="secondaryLang"></select>
		</label>
	</div>
	<div>
		<form action="scripts/upload.php" method="post" enctype="multipart/form-data" accept-charset='UTF-8'>
		    <label>Select a file to upload:
		    	<input id="fileToUpload" type="file" name="fileToUpload">
		    </label>
		    <input type="submit" value="Upload" name="submit">
		</form>
	</div>
	<div id="profile">
		<div id="welcome"><p>Welcome : <?php echo $login_session; ?><p></div>
		<div id="logout"><a href="logout.php">Log Out</a></div>
	</div>
</body>
</html> 
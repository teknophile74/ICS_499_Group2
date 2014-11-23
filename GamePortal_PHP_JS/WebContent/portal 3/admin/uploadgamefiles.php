<?php
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
	include('scripts/session.php');
	include('scripts/upload.php'); // Includes upload Script
?>
<!DOCTYPE>
<html>
<head>
	<title>Game File Upload Page</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<script src="scripts/countries.js"></script>
	<script src="scripts/ISOV639v2Codes.js"></script>
	<script src="scripts/global.js"></script>
	<script src="scripts/admin.js"></script>
</head>
<body>
	<div id="main">
	<h3>Game Portal - Admin - Game File Upload Page</h3>
	<div id="upload">
		<div id="profile">
			<div id="welcome"><p>Welcome : <?php echo $login_session; ?></p></div>
			<div id="logout"><a href="scripts/logout.php">Log Out</a></div>
		</div>
		<form action="" method="post" enctype="multipart/form-data" accept-charset='UTF-8'>
			<fieldset>
				<legend>Game Info</legend>
				<div id="gameSelectionControls">
					<label>Game Selection
						<select id="gameSelector" name="game" onchange="updateInputDisplay(this.value);">
							<?php
								//<--get the games directorys subdirectories-->
								$path = '../games';
								$games = glob($path . '/*' , GLOB_ONLYDIR);
								
								//count the number of subdirectories found in 'games'
								$gameCount = count($games);
								
								//loop through the array for games
								for($i = 0; $i < $gameCount; $i++) {
									//get the individual game's folder name
									$theGame = explode("/", $games[$i]);
									$theGame = $theGame[2];
						
									//display information for each game
									$gameInfo = "<option value=\"$theGame\">$theGame</option>";
									
									//display the games
									echo $gameInfo;
								}
							?>
						</select>
					</label>
				</div>
			</fieldset>
			<fieldset>
				<legend>Language Information</legend>
				<div id="countrySelectionControls">
					<label>Country Associated to Language
						<select id="country" name="country"></select>
					</label>
				</div>
				<div id="langSelectionControls">
					<label>Primary Language
						<select id="primaryLang" name="primaryLang"></select>
					</label><br />
					<label id="secondaryLangLabel">Secondary Language
						<select id="secondaryLang" name="secondaryLang"></select>
					</label>
				</div>
			</fieldset>
			<fieldset id="categoryNameControl">
				<legend>Category Name</legend>
				<div id="categoryNameDiv">
					<label>User Assigned Category Name (Required)
						<input id="categoryName" type="text" name="categoryName">
					</label>
				</div>
			</fieldset>
			<fieldset>
				<legend>File Location</legend>
				<label>Select a file to upload:
			    	<input id="fileToUpload" type="file" name="fileToUpload">
			    </label>
			    <input type="submit" value="Upload" name="submit">
			</fieldset>
		</form>
		<span id="error"><?php echo $error; ?></span>
		<span id="success"><?php echo $success; ?></span>
		</div>
=======
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
>>>>>>> af97253 Adding Admin portal + updates to 4P1W game files
	</div>
</body>
</html> 
=======
include('session.php');
=======
include('scripts/session.php');
include('scripts/upload.php'); // Includes upload Script
>>>>>>> 1c9feb3 Changes for portal admin and slider
?>
<!DOCTYPE>
<html>
<head>
	<title>Game File Upload Page</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<script src="scripts/ISOV639v2Codes.js"></script>
	<script src="scripts/global.js"></script>
	<script src="scripts/admin.js"></script>
</head>
<body>
	<div id="main">
	<h3>Game Portal - Admin - Game File Upload Page</h3>
	<div id="upload">
		<div id="profile">
			<div id="welcome"><p>Welcome : <?php echo $login_session; ?></p></div>
			<div id="logout"><a href="scripts/logout.php">Log Out</a></div>
		</div>
		<form action="" method="post" enctype="multipart/form-data" accept-charset='UTF-8'>
			<fieldset>
				<legend>Game Info</legend>
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
									$theGame = $theGame[2];
						
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
					</label><br />
					<label>Secondary Language
						<select id="secondaryLang"></select>
					</label>
				</div>
			</fieldset>
			<fieldset>
				<legend>File Info</legend>
				<label>Select a file to upload:
			    	<input id="fileToUpload" type="file" name="fileToUpload">
			    </label>
			    <input type="submit" value="Upload" name="submit">
			</fieldset>
		</form>
		<span id="error"><?php echo $error; ?></span>
		<span id="success"><?php echo $success; ?></span>
		</div>
	</div>
</body>
</html> 
>>>>>>> 2fb107e Adding base admin portal

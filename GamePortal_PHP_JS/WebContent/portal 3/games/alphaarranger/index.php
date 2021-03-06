<!DOCTYPE html>
<html>
<head>
	<title>Alphabet-Arranger</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- Global Site Style -->
	<link rel="stylesheet" type="text/css" href="../../style.css" />
	<!-- Locally needed style objects -->
	<link rel="stylesheet" type="text/css" href="css/slider2.css" />
	<!-- Game Javascript Files -->
	<script id="DefaultLangArray" src="lang/US/en/LangJSON.js"></script>
	<script src="lang/CurrentLangDirs.js"></script>
	<script src="script/baseSettingsJSON.js"></script>
	<!--  Main Game scripts -->
	<script src="script/global.js"></script>
	<script src="script/Slider2Objects.js"></script>
</head>
<body onload="init();">
    <fieldset class="gameSettings">
      <legend>Game Settings</legend>
      	<div id="gameSettings">
      		<span class="nonwraplabels">
	      		<label for="Style">Game Style:</label>
				<select id="Style" name="Style" onchange="changePuzzleStyle(this.value);"></select>
			</span>
			<span class="nonwraplabels">
	      		<label for="Lang">Language:</label>
				<select id="Lang" name="Lang" onchange="changePuzzleLanguage(this.value);"></select>
			</span>
			<span class="nonwraplabels">
				<label for="Offset">Starting Position:</label>
				<input id="Offset" name="Offset" onchange="changePuzzleOffset(this.value)" 
					type="number" min="0" max="20" step="1" value="0" />
      		</span>
      		<span class="nonwraplabels">
	      		<label for="Size">Cells Per Row:</label>
	      		<input id="Size" name="Size" onchange="changePuzzleSize(this.value);" 
	      			type="number" min="3" max="8" step="1" value="4" />
      		</span><br />
      		<input id="reset" type="button" value="Reset Puzzle" onClick="ResetPuzzle();" />
      		<input id="portal" type="button" value="Back to Portal" onclick="history.go(-1);" />
		</div>
	</fieldset>
	<!-- Holds Game Board -->
	<div id="gameHolder"></div>
	<div id="errorDisplay"></div>	
	<div id="counter">
		<span>Moves this game: </span><span id="MoveCounter"></span>
	</div>
</body>
</html>

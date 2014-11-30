<?php

// Get path to uploaded file
// Check for existence of uploaded file
// Get GAME name, Country and Language(s) from POST
// If game was "wordexplorer" gmae chosen,
	// then fill in _USER_DEFINED_ file name with uploaded name
// Get GAME path (Base)
// Get Language directory path from game settings
// Get game upload format (upload.format) in lang dir of game

// Run checks agains expected format and newly uploaded file

	// if errors reject back to user
	// else proceed to conversion
	
//Conversion process
// Get lang file name from game upload.format file
// Load the format and header settings into arrays
/** 
 * Load uploaded file in an array, or read it in line by line,
 * into altername array 
 */ 
/** 
 * Read in expected output file name from upload.format and 
 * get needed info from POST variables
 */
// Establish temp file (in upliad directory) to write out new file info
// For each line (or array element) write out needed syntact to temp file
	// If bad unput found send back error to admin user (also del temp file)
	// If conversion is good create new Country_LANG dir in game directory
		// If cannot create new Country_LANG dir, return to user with error
		// if new Country_LANG is created, copy newly created temp file to
			// Country_LANG dir with correct name
		// if Country_LANG has new file add new country and lang combo to
			// CurrentLangDirs.js file in game lang dir

// Write out success message to admin user
// ELSE Write out failure message to admin user
// Return to upload page

?>
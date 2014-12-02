<?php

/*
 * Getting values from json
 * http://www.academia.edu/4092169/Get_data_from_string_with_JSON_object_in_PHP
 */

function writeJSON() {
	// http://php.net/manual/en/function.json-encode.php
}

function fileConversion() {
	// Get lang file name from game upload.format file
	// Load the format and header settings into arrays
	/*
	 * Load uploaded file in an array, or read it in line by line,
	 * into altername array
	 */
	/*
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
}

function controller($target_file, $game_name, $country_code, 
					$primary_lang_code, $secondary_lang_code, $category_name) {
	// Set return message string
	$return_message = "";
	// Get path to uploaded file
	$upload_dir = "../upload_files/";
	// Get JSON Language Array
	$langfiledata = file_get_contents('../scripts/ISOV639v2Codes.js');
	$langdataarray = json_decode($langfiledata, true);
	// Get JSON Country Array
	$countryfiledata = file_get_contents('../scripts/countries.js');
	$countrydataarray = json_decode($countryfiledata, true);
	// Set games base path
	$game_dir = "../../games/";
	$lang_dir = $game_dir . '/' . $game_name . '/lang/';
	$dest_dir = "";
	// Set temp file name
	$temp_filename = "temp_conversion_file";
	// Set Name of new JSON array and file name
	$JSON_arrayName = "";
	$JSON_fileName = "";
	// Set names of country, prilang and seclang
	$country_name = $countrydataarray[$country_code]['name'];
	$prilang_name = $langdataarray[$primary_lang_code];
	$seclang_name = $langdataarray[$secondary_lang_code];
	// Check for existence of uploaded file
	if (file_exists($target_file)) {
	// Get GAME name, Country and Language(s) from POST
	
	// If game was "wordexplorer" game chosen,
		
		if ($game_name == 'wordexplorer'){
			// then fill in _USER_DEFINED_ file name with uploaded name
			$JSON_arrayName = $prilang_name . $seclang_name . '_' . $category_name;
			$JSON_fileName = $category_name . '.js';
			// Set up base path to lang folder
			$dest_dir = $lang_dir . $country_code . '/' . $primary_lang_code . 
				'/' . $secondary_lang_code . '/';
		} else {
			$JSON_arrayName = $prilang_name;
			$JSON_fileName = 'LangJSON.js';
			// Set up base path to lang folder
			$dest_dir = $lang_dir . $country_code . '/' . $primary_lang_code . '/';
		}
		
		// Get game upload format (upload.format) in lang dir of game
		$uploadFormatFile = $lang_dir . 'upload.format';
		$upload_formatdata = file_get_contents($uploadFormatFile);
		
		// Run checks agains expected format and newly uploaded file
		
			// If errors reject back to user
			// else proceed to conversion
			
		//Conversion process
		fileConversion();


	} else {
		// Write out error stating file was not found
		$return_message = "Sorry, there was an error uploading your file.";
	} // end if (file_exists($target_file))
		
	// Write out success message to admin user
	// ELSE Write out failure message to admin user
	// Return to upload page
	return $return_message;
}

?>
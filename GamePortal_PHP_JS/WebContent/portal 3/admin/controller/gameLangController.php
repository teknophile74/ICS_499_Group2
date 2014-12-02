<?php

<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
/*
 * Getting values from json
 * http://www.academia.edu/4092169/Get_data_from_string_with_JSON_object_in_PHP
 */

function openFile($filename) {
    $file_handle = fopen($filename, "rb");

    while (!feof($file_handle) ) {
        $lines = fgets($file_handle);
    }

    fclose($file_handle);
    return $lines;
}

function writeJSON() {
	// http://php.net/manual/en/function.json-encode.php
    $fp = @fopen($_GET['csv'], 'r');

    $headers = fgetcsv($fp, 2048);

    $data = array();
    while ($row = fgetcsv($fp, 2048)) {
        $item = array();
        foreach ($row as $key => $cell) {
            $item[$headers[$key]] = utf8_encode($cell);
        }
        $data[] = $item;
    }

    //header('Content-type: text/javascript');
    //echo json_encode($data);
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
	$langfiledata = file_get_contents('ISOV639v2Codes.js');
	$langdataarray = json_decode($langfiledata, true);
	// Get JSON Country Array
	$countryfiledata = file_get_contents('countries.js');
	$countrydataarray = json_decode($countryfiledata, true);
	// Set games base path
	$game_dir = "../../games/";
	$lang_dir = $game_dir . $game_name . '/lang/';
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
        //$parts = explode(':', $line_of_text);
		// Run checks against expected format and newly uploaded file
		
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
controller('upload_files/testfile.txt', 'alphaarranger', '', 'CN', '', '');
=======
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
=======
/**
=======
/*
>>>>>>> e2c46f5 Update to login and php pages
 * Getting values from json
 * http://www.academia.edu/4092169/Get_data_from_string_with_JSON_object_in_PHP
>>>>>>> ed5ac9a Updating upload files and conversion settings for admin page
 */

function openFile($filename) {
    $file_handle = fopen($filename, "rb");

    while (!feof($file_handle) ) {
        $lines = fgets($file_handle);
    }

    fclose($file_handle);
    return $lines;
}

function writeJSON() {
	// http://php.net/manual/en/function.json-encode.php
    $fp = @fopen($_GET['csv'], 'r');

    $headers = fgetcsv($fp, 2048);

    $data = array();
    while ($row = fgetcsv($fp, 2048)) {
        $item = array();
        foreach ($row as $key => $cell) {
            $item[$headers[$key]] = utf8_encode($cell);
        }
        $data[] = $item;
    }

    //header('Content-type: text/javascript');
    //echo json_encode($data);
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
	$langfiledata = file_get_contents('ISOV639v2Codes.js');
	$langdataarray = json_decode($langfiledata, true);
	// Get JSON Country Array
	$countryfiledata = file_get_contents('countries.js');
	$countrydataarray = json_decode($countryfiledata, true);
	// Set games base path
	$game_dir = "../../games/";
	$lang_dir = $game_dir . $game_name . '/lang/';
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
        //$parts = explode(':', $line_of_text);
		// Run checks against expected format and newly uploaded file
		
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
<<<<<<< Upstream, based on origin/Prod

>>>>>>> 49fd840 Updating GamePortal Admin site with needed controller info and needed settings Added [origin] country to upload page
?>
=======
controller('upload_files/testfile.txt', 'alphaarranger', '', 'CN', '', '');
?>
>>>>>>> 1bbe591 Conversion Process Updates

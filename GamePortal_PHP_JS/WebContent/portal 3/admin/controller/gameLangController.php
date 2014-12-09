<?php
ini_set('display_errors', 'On');
error_reporting(E_ALL);
// include and instantiate the class
require_once("scripts/PHPDebug.php");
//Set Debug flag
$doDebug=true;
$debug=null;
if ($doDebug) {
	if ($debug==null) {
		$debug = new PHPDebug();
	}
}

/*
 * Getting values from json
 * http://www.academia.edu/4092169/Get_data_from_string_with_JSON_object_in_PHP
 */

function outputDebug($message, $json=null, $type=null) {
	// Write debug info to console
	global $doDebug;
	if ($doDebug) {
		global $debug;
		$debug->debug($message,$json,$type);
	}
}

function testForValidURL($url) {
	$isURL=false;
	
	if(filter_var($url, FILTER_VALIDATE_URL)) {
		//URL is valid
		$isURL=true;
	} else {
		outputDebug($url." is not a valid URL!");// Write debug info
	}
	return $isURL;
}

function testForValidStrings($newString) {
	$isString=true;
	
	if (!is_string($newString)) {
		$isString=false;
		outputDebug($isString." is not a string value!");// Write debug info
	}
	return $isString;
}

function openFile($filename) {
    $file_handle = fopen($filename, "rb");

    while (!feof($file_handle) ) {
        $lines = fgets($file_handle);
    }

    fclose($file_handle);
    return $lines;
}

function readScriptsJSONFiles($filename, $returnArray) {
	$returnArray = file($filename);
	outputDebug("Trying to load: $filename into returnArray"); // Write debug info
	//drop first line - read the rest into the array
	unset($returnArray[0]);
	return $returnArray;
}

function getUploadFormat($filename,$upload_formatName,
	$upload_formatVar,$upload_formatHeaders,$upload_formatFormat) {
	$counter=1;
	$handle = fopen($filename, "r");
	if ($handle) {
		while (($line = fgets($handle)) !== false) {
			// process the line read.
			switch ($counter) {
				case 1:
					$upload_formatName=explode(":", $line);
					return $upload_formatName;
					//var_dump($counter.":".$upload_formatName[1]);
					break;
				case 2:
					$upload_formatVar=explode(":", $line);
					$upload_formatVar=explode("|", $upload_formatVar[1]);
					return $upload_formatVar;
					//var_dump($counter.":");var_dump($upload_formatVar);
					break;
				case 3:
					$upload_formatHeaders=explode(":", $line);
					$upload_formatHeaders=explode("|", $upload_formatHeaders[1]);
					return $upload_formatHeaders;
					//var_dump($counter.":");var_dump($upload_formatHeaders);
					break;
				case 4:
					$upload_formatFormat=explode(":", $line);
					$upload_formatFormat=explode("|", $upload_formatFormat[1]);
					return $upload_formatFormat;
					//var_dump($counter.":");var_dump($upload_formatFormat);
					break;
				default:break;
			}
			$counter++;
		}
	} else {
		// error opening the file.
		outputDebug("Error opening upload format for game: $filename"); // Write debug info
	}
	fclose($handle);
}

function readCSVFile($filename) {
	$csvReturnArray=array();
	$row = 1;
	if (($handle = fopen($filename, "r")) !== FALSE) {
		while (($data = fgetcsv($handle, 1024, "|")) !== FALSE) {
			$num = count($data);
			// Write debug info
			outputDebug("$num fields in line $row");// Write debug info
			$row++;
			for ($c=0; $c < $num; $c++) {
				// Write debug info
				outputDebug("Current array data for colum: $c is $data[$c]");// Write debug info
				array_push($csvReturnArray, $data[$c]);
			}
		}
		fclose($handle);
	}
}

function writeJSON($headers, $inputArray, $destFilename) {
	/**
	 * Concept borrowed from http://php.net/manual/en/function.json-encode.php
	 */ 
    //$fp = @fopen($_GET['csv'], 'r');
    //$headers = fgetcsv($fp, 2048);

    $data = array();
    foreach($inputArray as $row) {
        $item = array();
        foreach ($row as $key => $rowData) {
            $item[$headers[$key]] = utf8_encode($rowData);
        }
        $data[] = $item;
    }
    
    outputDebug("Attempting to encode new JSON file to $destFilename");// Write debug info
    
    file_put_contents($destFilename, implode("",json_encode($data)));
}

function fileConversion($game_name ,$JSON_fileName, $JSON_arrayName, 
		$prilang_name, $seclang_name, $category_name) {
	// Get lang file name from game upload.format file
	// Load the format and header settings into arrays
	/*
	 * Load uploaded file in an array, or read it in line by line,
	 * into altername array
	 */
	// If game was "wordexplorer" game chosen,
	if ($game_name == 'wordexplorer'){
		// then fill in __Category__ js filename with names from categories found in file
		$JSON_arrayName = $prilang_name.$seclang_name.'_'.$category_name;
		$JSON_fileName = $category_name.'.js';
	} else {
		$JSON_arrayName = $prilang_name;
		$JSON_fileName = 'LangJSON.js';
	}
	/*
	 * Read in expected output file name from upload.format and
	 * get needed info from POST variables
	 */
	// Establish temp file (in upload directory) to write out new file info
	// For each line (or array element) write out needed syntact to temp file
	// If bad unput found send back error to admin user (also del temp file)
	// If conversion is good create new Country_LANG dir in game directory
	// If cannot create new Country_LANG dir, return to user with error
	// if new Country_LANG is created, copy newly created temp file to
	// Country_LANG dir with correct name
	// if Country_LANG has new file add new country and lang combo to
	// CurrentLangDirs.js file in game lang dir
	
	//file_put_contents("outfile.txt", implode("", $file_array));
	
	// Write out success message to admin user
	// ELSE Write out failure message to admin user
}

function array_push_assoc($array, $key, $value){
	$array[$key] = $value;
	return $array;
}

function controller($target_file, $game_name, $country_code, 
					$primary_lang_code, $secondary_lang_code) {
	$langfiledata=array(); $countryfiledata=array();
	$langdataarray=array(); $countrydataarray=array();
	// Gets GAME name, Country and Language(s) from POST - in variables
	$return_message = ""; 	// Set return message string
	$upload_dir = "upload_files/"; 	// Get path to uploaded file
	$scripts_dir = "scripts/"; 	// Get path to uploaded file
	
	// Get JSON Language Array
	$langfiledata = readScriptsJSONFiles($scripts_dir.'ISOV639v2Codes.js',$langfiledata);
	foreach ($langfiledata as $row) {
		$notAllowed="{};";
		
		if (!(strpbrk($row,$notAllowed))) {
			$result=explode(":", $row);
			$key=str_replace(array('\'',',','"',' '),'',$result[0]);
			$value=str_replace(array('\'',',','"',' '),'',$result[1]);
			$langdataarray = array_push_assoc($langdataarray,$key,$value);
		}
	}
	
	// Get JSON Country Array
	$countryfiledata = readScriptsJSONFiles($scripts_dir.'countries.js',$countryfiledata);
	//$countrydataarray = json_decode($countryfiledata, true);
	foreach ($countryfiledata as $row) {
		$notAllowed="[];";
	
		if (!(strpbrk($row,$notAllowed))) {
			//$result=explode(":", $row);
			$result=str_replace(array('},'),'}',$row);
			$result=json_decode($result,true);
			array_push($countrydataarray, $result);
		}
	}
	
	// Set games base path
	$game_dir = "../games/";
	$lang_dir = $game_dir.$game_name.'/lang/';
	$dest_dir = null;
	//var_dump($game_dir."<br />".$lang_dir."<br />".$dest_dir);
	
	// Set temp file name
	$temp_filename = "temp_conversion_file";
	
	$upload_formatName='';
	$upload_formatVar=array();
	$upload_formatHeaders=array();
	$upload_formatFormat=array();
	// Get game upload format (upload.format) in lang dir of game
	getUploadFormat($lang_dir.'upload.format',$upload_formatName,$upload_formatVar,$upload_formatHeaders,$upload_formatFormat);
	
	var_dump($upload_formatName);var_dump("<br />");
	var_dump($upload_formatVar);var_dump("<br />");
	var_dump($upload_formatHeaders);var_dump("<br />");
	var_dump($upload_formatFormat);

		
	// Set Name of new JSON array and file name
	$JSON_arrayName = "";
	$JSON_fileName = "";
	// Set names of country, prilang and seclang
	$country_name = $countrydataarray[$country_code]['name'];
	$prilang_name = $langdataarray[$primary_lang_code];
	$seclang_name = $langdataarray[$secondary_lang_code];
	
	outputDebug("Current variables are: $game_dir $lang_dir $dest_dir $upload_formatName $upload_formatVar $upload_formatHeaders $upload_formatFormat");// Write debug info
	die;
	
	// Check for existence of uploaded file
	if (file_exists($target_file)) {
		// Set up base path to lang folder
		if ($game_name == 'wordexplorer'){
			// If game was "wordexplorer" game chosen - special path
			$dest_dir = $lang_dir.$country_code.'/'.$primary_lang_code.'/'.$secondary_lang_code.'/';
		} else {
			$dest_dir = $lang_dir.$country_code.'/'.$primary_lang_code.'/';
		}
		outputDebug("Destination for new file is $dest_dir");// Write debug info
		
		openFile($target_file);
		
        //$parts = explode(':', $line_of_text);
		// Run checks against expected format and newly uploaded file
		
			// If errors reject back to user
			// else proceed to conversion
			
		//Conversion process
		/*
		fileConversion($game_name ,$JSON_fileName, $JSON_arrayName, 
		$prilang_name, $seclang_name, $category_name);
		*/

	} else {
		// Write out error stating file was not found
		$return_message = "Sorry, there was an error uploading your file.";
	} // end if (file_exists($target_file))
		
	// Write out success message to admin user
	// ELSE Write out failure message to admin user
	// Return to upload page
	return $return_message;
}
//controller('upload_files/testfile.txt', 'alphaarranger', '', 'CN', '');
?>
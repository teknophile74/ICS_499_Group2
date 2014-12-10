<?php
ini_set('error_reporting', E_ALL);
// include and instantiate the class
require_once("scripts/PHPDebug.php");
//Set Debug flag
$doDebug=FALSE;
$debug = new PHPDebug();
/*
 * Getting values from json
 * http://www.academia.edu/4092169/Get_data_from_string_with_JSON_object_in_PHP
 */

function array_push_assoc($array, $key, $value){
	$array[$key] = $value;
	return $array;
}

function outputDebug($message, $json=null, $type='LOG') {
	// Write debug info to console
	global $doDebug; global $debug;
	if ($doDebug) {$debug->debug($message,$json,$type);}
}

function testForValidURL($url) {
	$isURL=false;
	
	if(filter_var($url, FILTER_VALIDATE_URL)) {
		//URL is valid
		$isURL=true;
	} else {
		outputDebug($url.' is not a valid URL!',null,'LOG');// Write debug info
	}
	return $isURL;
}

function testForValidStrings($newString) {
	$isString=true;
	
	if (!is_string($newString)) {
		$isString=false;
		outputDebug($isString.' is not a string value!',null,'LOG');// Write debug info
	}
	return $isString;
}

function validateFileData($inputLine, $expectUploadFormat) {
	global $doDebug;
	$isValid = false;
	if ($doDebug) {var_dump("<br/ >validateFileData Variables:<br />");}
	// Begin checks
	if ((is_array($inputLine)) && (is_array($expectUploadFormat))) {
// 		var_dump($expectUploadFormat);
// 		var_dump("<br />");
// 		var_dump($inputLine);
// 		var_dump("<br />");
		$i=0;
		foreach ($expectUploadFormat as $inputCheck) {
			if ($doDebug) {var_dump("1 {$inputCheck}<br />");}
			if (gettype($inputLine[$i]) == $inputCheck) {
				//Check for string data
				if ($inputCheck == "string") {
					if (testForValidStrings($inputLine[$i])) {
						$isValid = true;
						if ($doDebug) {var_dump("2 {$isValid}<br />");}
					} else {
						$isValid = false;
						break;
					}
				}
				// Check for URL data
				if ($inputCheck == "url") {
					if (testForValidURL($inputLine[$i])) {
						$isValid = true;
						if ($doDebug) {var_dump("3 {$isValid}<br />");}
					} else {
						$isValid = false;
						break;
					}
				}
			} else {
				// Check for URL data
				if ($inputCheck == "url") {
					if (is_string($inputLine[$i])) {
						if (testForValidURL($inputLine[$i])) {
							$isValid = true;
							if ($doDebug) {var_dump("4 {$isValid}<br />");}
						} else {
							$isValid = false;
							break;
						}
					}
				}
			}
			$i++;
		}
	}
	if ($doDebug) {var_dump("Final Value {$isValid}<br />");}
	return $isValid;
}


function readScriptsJSONFiles($filename, &$returnArray) {
	outputDebug('Trying to load: '.$filename.' into returnArray',null,'LOG'); // Write debug info
	
	$returnArray = file($filename);
	//drop first line - read the rest into the array
	unset($returnArray[0]);
	return $returnArray;
}

function getUploadFormat($filename, &$upload_formatName,
	&$upload_formatVar, &$upload_formatHeaders, &$upload_formatFormat) {
	//var_dump("Begin getUploadFormat:<br/>", $filename, "<br/>");
	$counter=1;
	try {
		if (($handle = fopen($filename, "r")) !== FALSE) {
			while (($line = fgets($handle)) !== false) {
				// process the line read.
				trim($line);
				switch ($counter) {
					case 1:
						$upload_formatName=explode(":", $line);
						$upload_formatName=trim($upload_formatName[1]," \t\n\r\0\x0B");
						//var_dump($counter, $upload_formatName, "<br/>");
						break;
					case 2:
						$upload_formatVar=explode(":", $line);
						//var_dump($counter, $upload_formatVar, "<br/>");
						$upload_formatVar=explode("|", trim($upload_formatVar[1]," \t\n\r\0\x0B"));
						//var_dump($counter, $upload_formatVar, "<br/>");
						break;
					case 3:
						$upload_formatHeaders=explode(":", $line);
						//var_dump($counter, $upload_formatHeaders, "<br/>");
						$upload_formatHeaders=explode("|", trim($upload_formatHeaders[1]," \t\n\r\0\x0B"));
						//var_dump($counter, $upload_formatHeaders, "<br/>");
						break;
					case 4:
						$upload_formatFormat=explode(":", $line);
						//var_dump($counter, $upload_formatFormat, "<br/>");
						$upload_formatFormat=explode("|", trim($upload_formatFormat[1]," \t\n\r\0\x0B"));
						//var_dump($counter, $upload_formatFormat, "<br/>");
						break;
					default:break;
				}
				$counter++;
			}
			fclose($handle);
		} else {
			// error opening the file.
			outputDebug('Error opening upload format for game: '.$filename,null,'ERROR'); // Write debug info
		}
	}
	catch (Exception $e) {
	    echo 'Caught exception: ', $e->getMessage(), "\n";
	    if ($handle) {fclose($handle);}
	}
}

function readCSVFile($filename, &$csvReturnArray) {
	// Reads in the newly uploaded file and returns an array with the data in each row
	$row = 1;
	ini_set("auto_detect_line_endings", TRUE);
	try {
		if (($handle = fopen($filename, "r")) !== FALSE) {
			while (($data = fgetcsv($handle, 1024, "|")) !== FALSE) {
				// Drop the first row of headers 
				if ($row > 1) {
					$num = count($data);
					// Write debug info
					outputDebug("{$num} fields in line {$row}",null,'LOG'); // Write debug info
					$arrayIndex=$row-2;
					for ($c=0; $c < $num; $c++) {
						// Write debug info
						outputDebug("Current array data for colum: {$c} is {$data[$c]}",null,'LOG'); // Write debug info
						$csvReturnArray[$arrayIndex][$c] = $data[$c];
					}
				}
				$row++;
			}
			fclose($handle);
		} else {
			// error opening the file.
			outputDebug('Error opening upload format for game: '.$filename,null,'ERROR'); // Write debug info
		}
	}
	catch (Exception $e) {
	    echo 'Caught exception: ', $e->getMessage(), "\n";
	    if ($handle) {fclose($handle);}
	}
}

function writeJSON(&$resource, $headers, $inputArray, $destFilename) {
	/**
	 * Concept borrowed from http://php.net/manual/en/function.json-encode.php
	 */ 
	// For each line (or array element) write out needed syntacx to temp file
    $data = array();
    foreach($inputArray as $row) {
        $item = array();
        foreach ($row as $key => $rowData) {
            $item[$headers[$key]] = utf8_encode($rowData);
        }
        $data[] = $item;
    }
    
    outputDebug('Attempting to encode new JSON file to '.$destFilename,null,'LOG');// Write debug info
    //file_put_contents($destFilename, implode("",json_encode($data)));
    fwrite($resource, json_encode($data, JSON_UNESCAPED_SLASHES));
}

function fileConversion($game_name, $prilang_name, $seclang_name, 
			$upload_formatName,	$upload_formatVar, $upload_formatHeaders, 
			$upload_formatFormat, $newFileArray, $temp_filename) {
	global $doDebug;
	$doDebug = FALSE;
	if ($doDebug) {var_dump("JSON Conversion Process Start<br />");}
	// Set Name of new JSON array and file name
	$JSON_arrayName=''; $category_name='';
	$JSON_fileName=array();
	
	/*
	 * Load uploaded file in an array, or read it in line by line,
	 * into altername array
	 */
	$tempArray = array_slice($newFileArray, 0);
	/*
	 * Check to see if game was "wordexplorer" game chosen,
	 * wordexplorer requires special processing to seperate categories
	 * in uploaded files
	 */
	if ($game_name == 'wordexplorer'){
		// then fill in __Category__ js filename with names from categories found in file
		foreach ($upload_formatVar as $varNames) {
			$JSON_arrayName = trim("{$JSON_arrayName}{$varNames}");
		}
		// Get lang file name from game upload.format file
		// Run loop to get all of the categories - add them to an array
		foreach($tempArray as $category_name) {
			array_push($JSON_fileName, "{$category_name[4]}.js");
		}
		
	} else {
		$JSON_arrayName = $prilang_name;
		$JSON_fileName[0] = $upload_formatName;;
	}

	if ($doDebug) {
		var_dump("JSON Conversion Process Variables");
		$varArray = array($game_name,$JSON_fileName,$JSON_arrayName,
				$prilang_name,$seclang_name,$category_name,$upload_formatName,
				$upload_formatVar,$upload_formatHeaders,$upload_formatFormat,
				$newFileArray,$temp_filename,$tempArray);
		foreach ($varArray as $var) {
			var_dump($var, "<br/>");
			outputDebug($var,null,'LOG'); // Write debug info
		}
	}
	
	// Establish temp file (in upload directory) to write out new file info
	for ($i=0; $i < count($JSON_fileName); ++$i) {
		$resource = fopen("{$temp_filename}_{$i}", 'wb+');
		fwrite($resource, "var {$JSON_arrayName} = \n");
		writeJSON($resource,$upload_formatHeaders,$tempArray,"{$temp_filename}_{$i}");
		fclose($resource);
	}
	// Write out failure message to admin user - if process is broken
	if ($doDebug) {var_dump("JSON Conversion Process Finished<br />");}
	return $JSON_fileName;
}

function controller($target_file, $game_name, $country_code, 
					$primary_lang_code, $secondary_lang_code) {
	global $doDebug;
	if ($doDebug) {
		echo "Incoming Parameters<br />";
		var_dump($target_file, $game_name, $country_code, $primary_lang_code, $secondary_lang_code);
		var_dump("<br />");
	}
	
	$langfiledata=array(); $countryfiledata=array();
	$langdataarray=array(); $countrydataarray=array();
	// Gets GAME name, Country and Language(s) from POST - in variables
	$return_message = ""; 	// Set return message string
	$upload_dir = "upload_files/"; 	// Get path to uploaded file
	$scripts_dir = "scripts/"; 	// Get path to uploaded file	
	// Set games base path
	$game_dir = "../games/";
	$lang_dir = "{$game_dir}{$game_name}/lang/";
	$dest_dir = null;
	// Set temp file name
	$temp_filename = "{$upload_dir}temp_conversion_file";
	
	if ($doDebug) {
		echo "Local Variables<br />";
		var_dump($upload_dir, $scripts_dir, $game_dir, $lang_dir, $dest_dir, $temp_filename);
		var_dump("<br />");
	}
	// Get JSON Language Array
	//$langfiledata = readScriptsJSONFiles($scripts_dir.'ISOV639v2Codes.js',$langfiledata);
	readScriptsJSONFiles($scripts_dir.'ISOV639v2Codes.js',$langfiledata);
	foreach ($langfiledata as $row) {
		$notAllowed="{};";
		
		if (!(strpbrk($row,$notAllowed))) {
			$result=explode(":", $row);
			$key=str_replace(array('\'',',','"',' '),'',$result[0]);
			$value=str_replace(array('\'',',','"',' '),'',$result[1]);
			$langdataarray = array_push_assoc($langdataarray,trim($key),trim($value));
		}
	}
	if ($doDebug) {echo "Langfiledata Variables<br />";	var_dump($langdataarray); var_dump("<br />");}
	
	// Get JSON Country Array
	//$countryfiledata = readScriptsJSONFiles($scripts_dir.'countries.js',$countryfiledata);
	readScriptsJSONFiles($scripts_dir.'countries.js',$countryfiledata);
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
	if ($doDebug) {echo "Countryfiledata Variables<br />"; var_dump($countrydataarray); var_dump("<br />");}
	
	$upload_formatName='';	$upload_formatVar=array();
	$upload_formatHeaders=array(); 	$upload_formatFormat=array();
	// Get game upload format (upload.format) in lang dir of game
	getUploadFormat($lang_dir.'upload.format',$upload_formatName,
		$upload_formatVar,$upload_formatHeaders,$upload_formatFormat);
	
	if ($doDebug) {
		echo "UploadFormat Variables<br />";
		var_dump($upload_formatName, "<br/>", 
			$upload_formatVar, "<br/>", 
			$upload_formatHeaders, "<br/>", 
			$upload_formatFormat, "<br/>");
		var_dump("<br />");
	}
	
	// Set names of country, prilang and seclang
	$country_name=null;
	foreach ($countrydataarray as $row) {$country_name = $row['name'];}
	$prilang_name = $langdataarray[$primary_lang_code];
	$seclang_name = $langdataarray[$secondary_lang_code];
	
	if ($doDebug) {
		echo "Local Lang and Country Variables<br />";
		var_dump($country_name, $prilang_name, $seclang_name);
		var_dump("<br />");
	}
	// Echo out the debug variables
	outputDebug("Current variables are: ");
	$varArray = array($game_dir, $lang_dir, $dest_dir, $upload_formatName,
			$upload_formatVar, $upload_formatHeaders, $upload_formatFormat); 
	foreach ($varArray as $var) {
		outputDebug($var,null,'LOG'); // Write debug info
	}
	
	// Check for existence of uploaded file
	if (file_exists($target_file)) {
		// Set up base path to lang folder
		if ($game_name == 'wordexplorer'){
			// If game was "wordexplorer" game chosen - special path
			$dest_dir = $lang_dir.$country_code.'/'.$primary_lang_code.'/'.$secondary_lang_code.'/';
		} else {
			$dest_dir = $lang_dir.$country_code.'/'.$primary_lang_code.'/';
		}
		outputDebug("Destination for new file is {$dest_dir}",null,'LOG');// Write debug info
		
		$newFileArray=array();
		readCSVFile($target_file, $newFileArray); // Get info from new uploaded file
		if ($doDebug) {var_dump("ReadCSVFile<br />:"); var_dump($newFileArray);}
		
		$lineCounter=1;
		$valiatationSuccess=false;
		// Do line operations to validate file
		foreach($newFileArray as $line) {
			$valiatationSuccess=false;
			if ($doDebug) {var_dump($line, "<br/>");}
			// Run checks against expected format and newly uploaded file
			if (!(validateFileData($line, $upload_formatFormat))) {
				// If errors reject back to user
				outputDebug("Destination for new file is {$dest_dir}",null,'LOG');// Write debug info
				$return_message = "There was an error found while converting your file.";
				$return_message = "{$return_message} <br /> The error was found on line {$lineCounter}.";
				if ($doDebug) {var_dump($return_message, "<br/>");}
				break;
			} else {
				$valiatationSuccess = true;
			}
			$lineCounter++;
		}
		
		if ($valiatationSuccess) {
			if ($doDebug) {var_dump("File Validated and ready for JSON Conversion Process");}
			//Conversion process
			$dest_filenames=array();
			$dest_filenames = fileConversion($game_name, $prilang_name, $seclang_name, $upload_formatName,
				$upload_formatVar, $upload_formatHeaders, $upload_formatFormat, 
				$newFileArray, $temp_filename);
	
			if ($dest_filenames) {
				// If conversion is good create new Country_LANG dir in game directory
				if (!file_exists($dest_dir)) {
					if (!mkdir($dest_dir, 0777, true)) {
						// If cannot create new Country_LANG dir, return to user with error
						if ($doDebug) {var_dump("Failed to create {$dest_dir} <br/>");}
						outputDebug("Failed to create {$dest_dir}",null,'LOG');
					}
				} else {
					if ($doDebug) {var_dump("The directory {$dest_dir} already exists.<br/>");}
					outputDebug("The directory {$dest_dir} already exists.<br/>",null,'LOG');
				}
				
				// COPY file(s) to final lang destination directory
				$i=0;
				//$doDebug = TRUE;
				foreach($dest_filenames as $destFileName) {
					/*
					 * if new Country_LANG is created, copy newly created temp file to
					 * Country_LANG dir with correct name
					 */ 
								
					$finalDestFileName = trim("{$dest_dir}{$destFileName}");
					
					if ($doDebug) {var_dump("Copying {$temp_filename}_{$i} to {$finalDestFileName}");}
					
					if (!copy("{$temp_filename}_{$i}", $finalDestFileName)) {
						if ($doDebug) {var_dump("Failed to copy {$destFileName} to the final 
							destination directory of {$dest_dir}!");}
						// Send message back to admin
						$return_message = "Failed to copy {$destFileName} to the final 
							destination directory of {$dest_dir}!<br/>";
						break;
					}
					
					if (is_file($finalDestFileName)) {
						// If debug keep files for evaluation
						if (!$doDebug) {
							// Remove the temp conversion file
							if (is_file("{$temp_filename}_{$i}")) {
								if (!unlink("{$temp_filename}_{$i}")) {
									// Write debug info
									outputDebug("Could not remove the temp 
										conversion file {$temp_filename}_{$i}",null,'LOG');
								}
							}
						}
					}
					++$i;
				}
			}
			/*
			 * if Country_LANG has new file add new country and lang combo to
			 * CurrentLangDirs.js file in game lang dir
			 */
			// !!!MORE WORK NEEDED HERE!!!! Update CurrentLangDirs with new file input.
		}
		
		// If debug keep files for evaluation
		if (!$doDebug) {
			// Remove the uploaded file
			if (!(unlink($target_file))) {
				// Write debug info
				outputDebug("Could not remove the user uploaded file {$target_file}",null,'LOG');
			}
		}
	} else {
		// Write out error stating file was not found
		$return_message = "Sorry, there was an error uploading your file.";
	} // end if (file_exists($target_file))
		
	// Write out failure message to admin user and return to upload page
	// No message indicates success condition
	return $return_message;
}
//controller('upload_files/testfile.txt', 'alphaarranger', '', 'CN', '');
?>
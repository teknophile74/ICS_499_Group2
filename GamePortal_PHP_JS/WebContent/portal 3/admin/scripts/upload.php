<?php
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

$error = ''; // Variable To Store Error Message
$reason = ''; // Var to store reason for failure
$success = ''; // Variable To Store Success Message
$target_dir = "upload_files/";
if( strtolower( $_SERVER[ 'REQUEST_METHOD' ] ) == 'post' && !empty( $_FILES ) ) {
	if ( $_FILES ["fileToUpload"] ["name"] ) {
		$target_file = $target_dir . basename ( $_FILES ["fileToUpload"] ["name"] );
		$uploadOk = 1;
		$imageFileType = pathinfo ( $target_file, PATHINFO_EXTENSION );
		// Check if file already exists
		if (file_exists ( $target_file )) {
			$reason = "The file already exists.";
			$uploadOk = 0;
		}
		// Check file size
		if ($_FILES ["fileToUpload"] ["size"] > 500000) {
			$reason = "Sorry, your file is too large.";
			$uploadOk = 0;
		}
		// Allow certain file formats
		if ($imageFileType != "csv" && $imageFileType != "txt") {
			$reason = "Sorry, only CSV and TXT files are allowed.";
			$uploadOk = 0;
		}
		// Check if $uploadOk is set to 0 by an error
		if ($uploadOk == 0) {
			$error = "Sorry, your file was not uploaded.<br /> $reason";
			// if everything is ok, try to upload file
		} else {
			if (move_uploaded_file ( $_FILES ["fileToUpload"] ["tmp_name"], $target_file )) {
				if(isset($_POST['submit'])){
					//Get postback values for form
					$game_name = (isset($_POST['game'])) ? $_POST ['game'] : "";
					$country_code = (isset($_POST['country'])) ? $_POST ['country'] : "";
					$primary_lang_code = (isset($_POST['primaryLang'])) ? $_POST ['primaryLang'] : "";
					$secondary_lang_code = (isset($_POST['secondaryLang'])) ? $_POST ['secondaryLang'] : "";
					$category_name = (isset($_POST['categoryName'])) ? $_POST ['categoryName'] : "";
					if ($doDebug) {$debug->debug("Running upload controller withthe following variables: ",null,'LOG');}
					if ($doDebug) {$debug->debug("$target_file, $game_name, $country_code, 
							$primary_lang_code, $secondary_lang_code",null,'LOG');}
					include('controller/gameLangController.php');
					$error = controller($target_file, $game_name, $country_code, 
							$primary_lang_code, $secondary_lang_code);
				}
				
				if ($error == "") {
					$success = "The file " . basename ( $_FILES ["fileToUpload"] ["name"] ) . " has been uploaded.";
				} else {
					$error = "Sorry, there was an error converting your file.";
				}
			} else {
				$error = "Sorry, there was an error uploading your file.";
			}
		}
	} else {
		$error = "There is no file selected to upload.";
	}
}
?> 
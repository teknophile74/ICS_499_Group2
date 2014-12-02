<?php
include('controller/gameLangController.php');

$error = ''; // Variable To Store Error Message
$success = ''; // Variable To Store Success Message
$target_dir = "upload_files/";
if ( $_FILES ["fileToUpload"] ["name"] ) {
	$target_file = $target_dir . basename ( $_FILES ["fileToUpload"] ["name"] );
	$uploadOk = 1;
	$imageFileType = pathinfo ( $target_file, PATHINFO_EXTENSION );
	// Check if file already exists
	if (file_exists ( $target_file )) {
		$error = "Sorry, file already exists.";
		$uploadOk = 0;
	}
	// Check file size
	if ($_FILES ["fileToUpload"] ["size"] > 500000) {
		$error = "Sorry, your file is too large.";
		$uploadOk = 0;
	}
	// Allow certain file formats
	if ($imageFileType != "csv" && $imageFileType != "txt") {
		$error = "Sorry, only CSV and TXT files are allowed.";
		$uploadOk = 0;
	}
	// Check if $uploadOk is set to 0 by an error
	if ($uploadOk == 0) {
		$error = "Sorry, your file was not uploaded.";
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
				$error = controller($target_file, $game_name, $country_code, 
						$primary_lang_code, $secondary_lang_code, $category_name);
			}
			
			if ($error == "") {
				$success = "The file " . basename ( $_FILES ["fileToUpload"] ["name"] ) . " has been uploaded.";
			} else {
				$error = "Sorry, there was an error uploading your file.";
			}
		} else {
			$error = "Sorry, there was an error uploading your file.";
		}
	}
} else {
	$error = "There is no file selected to upload.";
}
?> 
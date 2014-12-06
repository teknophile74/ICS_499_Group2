
<?php
session_start (); // Starting Session
$error = ''; // Variable To Store Error Message
if (isset ( $_POST ['submit'] )) {
	if (empty ( $_POST ['username'] ) || empty ( $_POST ['password'] )) {
		$error = "Username or Password is invalid";
	} else {
		// Define $username and $password
		$username = $_POST ['username'];
		$password = $_POST ['password'];
		// Establishing Connection with Server by passing server_name, user_id and password as a parameter
		$conn=null;
		try {
			$user='hzwxuoyr_admin'; $pass='PASSWORD_GOES_HERE';
			$dbname='hzwxuoyr_adminlogin'; $host='localhost';
			$login_session='';

			// Selecting Database
			$conn = new PDO("mysql:host=$host;dbname=$dbname", $user, $pass);
			//if ($mysqli->connect_errno) {
			//	$error = "Failed to connect to MySQL: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
			//}
			// To protect MySQL injection for Security purpose
			$username = stripslashes ( $username );
			$password = stripslashes ( $password );
			$username = mysql_real_escape_string ( $username );
			$password = mysql_real_escape_string ( $password );

			// SQL query to fetch information of registerd users and finds user match.
			$nRows = $conn->query("select count(*) from login where password='$password' AND username='$username'")->fetchColumn();
			// Check for a row that matches user\pass combo
			if ($nRows == 1) {
				$_SESSION ['login_user'] = $username; // Initializing Session
				header ( "location: uploadgamefiles.php" ); // Redirecting To Other Page
			} else {
				$error = "Username or Password is invalid";
			}

			$conn=null;  // Closing DB Connection
		}
		catch(PDOException $e) {
			echo "Error Connection to database - Please contact the Administrator";
			//echo $e->getMessage();
		}
		finally {
			$conn=null;  // Closing DB Connection
		}
	}
}
?>
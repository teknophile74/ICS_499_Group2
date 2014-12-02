
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
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
		$connection = mysql_connect ( "localhost", "hzwxuoyr_admin", "PASSWORD_GOES_HERE" );
		//$mysqli = new mysqli("localhost", "hzwxuoyr_admin", "PASSWORD_GOES_HERE", "hzwxuoyr_adminlogin");
		//if ($mysqli->connect_errno) {
		//	$error = "Failed to connect to MySQL: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
		//}
<<<<<<< Upstream, based on origin/Prod
=======
		$connection = mysql_connect ( "localhost", "hzwxuoyr_admin", "PASSWORD_GOES_HERE" );
>>>>>>> 49fd840 Updating GamePortal Admin site with needed controller info and needed settings Added [origin] country to upload page
=======
>>>>>>> e2c46f5 Update to login and php pages
		// To protect MySQL injection for Security purpose
		$username = stripslashes ( $username );
		$password = stripslashes ( $password );
		$username = mysql_real_escape_string ( $username );
		$password = mysql_real_escape_string ( $password );
		// Selecting Database
		$db = mysql_select_db ( "hzwxuoyr_adminlogin", $connection );
=======
		$connection = mysql_connect ( "localhost", "root", "" );
		// To protect MySQL injection for Security purpose
		$username = stripslashes ( $username );
		$password = stripslashes ( $password );
		$username = mysql_real_escape_string ( $username );
		$password = mysql_real_escape_string ( $password );
		// Selecting Database
		$db = mysql_select_db ( "company", $connection );
>>>>>>> af97253 Adding Admin portal + updates to 4P1W game files
		// SQL query to fetch information of registerd users and finds user match.
		$query = mysql_query ( "select * from login where password='$password' AND username='$username'", $connection );
		$rows = mysql_num_rows ( $query );
		if ($rows == 1) {
			$_SESSION ['login_user'] = $username; // Initializing Session
			header ( "location: uploadgamefiles.php" ); // Redirecting To Other Page
		} else {
			$error = "Username or Password is invalid";
		}
		mysql_close ( $connection ); // Closing Connection
	}
}
?>
=======
		$connection = mysql_connect ( "localhost", "root", "" );
		// To protect MySQL injection for Security purpose
		$username = stripslashes ( $username );
		$password = stripslashes ( $password );
		$username = mysql_real_escape_string ( $username );
		$password = mysql_real_escape_string ( $password );
		// Selecting Database
		$db = mysql_select_db ( "company", $connection );
		// SQL query to fetch information of registerd users and finds user match.
		$query = mysql_query ( "select * from login where password='$password' AND username='$username'", $connection );
		$rows = mysql_num_rows ( $query );
		if ($rows == 1) {
			$_SESSION ['login_user'] = $username; // Initializing Session
			header ( "location: uploadgamefiles.php" ); // Redirecting To Other Page
		} else {
			$error = "Username or Password is invalid";
		}
		mysql_close ( $connection ); // Closing Connection
	}
}
?>
>>>>>>> 2fb107e Adding base admin portal
=======
		$connection = mysql_connect ( "localhost", "hzwxuoyr_admin", "PASSWORD_GOES_HERE" );
		//$mysqli = new mysqli("localhost", "hzwxuoyr_admin", "PASSWORD_GOES_HERE", "hzwxuoyr_adminlogin");
		//if ($mysqli->connect_errno) {
		//	$error = "Failed to connect to MySQL: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
		//}
		// To protect MySQL injection for Security purpose
		$username = stripslashes ( $username );
		$password = stripslashes ( $password );
		$username = mysql_real_escape_string ( $username );
		$password = mysql_real_escape_string ( $password );
		// Selecting Database
		$db = mysql_select_db ( "hzwxuoyr_adminlogin", $connection );
		// SQL query to fetch information of registerd users and finds user match.
		$query = mysql_query ( "select * from login where password='$password' AND username='$username'", $connection );
		$rows = mysql_num_rows ( $query );
		if ($rows == 1) {
			$_SESSION ['login_user'] = $username; // Initializing Session
			header ( "location: uploadgamefiles.php" ); // Redirecting To Other Page
		} else {
			$error = "Username or Password is invalid";
		}
		mysql_close ( $connection ); // Closing Connection
	}
}
?>
>>>>>>> 872cb0e Merge to master

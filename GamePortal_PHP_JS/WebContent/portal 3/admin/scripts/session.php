<?php
// Establishing Connection with Server by passing server_name, user_id and password as a parameter
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
$connection = mysql_connect ( "localhost", "hzwxuoyr_admin", "PASSWORD_GOES_HERE" );
=======
$connection = mysql_connect ( "localhost", "hzwxuoyr_admin", "P@ssword!" );
>>>>>>> 1c9feb3 Changes for portal admin and slider
// Selecting Database
$db = mysql_select_db ( "hzwxuoyr_adminlogin", $connection );
session_start (); // Starting Session
                 // Storing Session
$user_check = $_SESSION ['login_user'];
// SQL Query To Fetch Complete Information Of User
$ses_sql = mysql_query ( "select username from login where username='$user_check'", $connection );
$row = mysql_fetch_assoc ( $ses_sql );
$login_session = $row ['username'];
if (! isset ( $login_session )) {
	mysql_close ( $connection ); // Closing Connection
	header ( 'location: ../login.php' ); // Redirecting To Home Page
<<<<<<< Upstream, based on origin/Prod
=======
$connection = mysql_connect ( "localhost", "root", "" );
// Selecting Database
$db = mysql_select_db ( "gamePortal", $connection );
session_start (); // Starting Session
                 // Storing Session
$user_check = $_SESSION ['login_user'];
// SQL Query To Fetch Complete Information Of User
$ses_sql = mysql_query ( "select username from login where username='$user_check'", $connection );
$row = mysql_fetch_assoc ( $ses_sql );
$login_session = $row ['username'];
if (! isset ( $login_session )) {
	mysql_close ( $connection ); // Closing Connection
	header ( 'Location: login.php' ); // Redirecting To Home Page
>>>>>>> af97253 Adding Admin portal + updates to 4P1W game files
}
?>
=======
$connection = mysql_connect ( "localhost", "root", "" );
// Selecting Database
$db = mysql_select_db ( "gamePortal", $connection );
session_start (); // Starting Session
                 // Storing Session
$user_check = $_SESSION ['login_user'];
// SQL Query To Fetch Complete Information Of User
$ses_sql = mysql_query ( "select username from login where username='$user_check'", $connection );
$row = mysql_fetch_assoc ( $ses_sql );
$login_session = $row ['username'];
if (! isset ( $login_session )) {
	mysql_close ( $connection ); // Closing Connection
	header ( 'Location: login.php' ); // Redirecting To Home Page
=======
>>>>>>> 1c9feb3 Changes for portal admin and slider
}
?>
>>>>>>> 2fb107e Adding base admin portal

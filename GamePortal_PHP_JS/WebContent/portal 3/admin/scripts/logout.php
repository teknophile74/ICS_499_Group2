<?php
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
session_start ();
if (session_destroy ()) // Destroying All Sessions
{
	header ( "Location: ../login.php" ); // Redirecting To Home Page
=======
session_start();
if(session_destroy()) // Destroying All Sessions
{
header("Location: login.php"); // Redirecting To Home Page
>>>>>>> af97253 Adding Admin portal + updates to 4P1W game files
}
?>
=======
session_start();
if(session_destroy()) // Destroying All Sessions
=======
session_start ();
if (session_destroy ()) // Destroying All Sessions
>>>>>>> ed5ac9a Updating upload files and conversion settings for admin page
{
	header ( "Location: ../login.php" ); // Redirecting To Home Page
}
?>
>>>>>>> 2fb107e Adding base admin portal
=======
session_start ();
if (session_destroy ()) // Destroying All Sessions
{
	header ( "Location: ../login.php" ); // Redirecting To Home Page
}
?>
>>>>>>> 872cb0e Merge to master

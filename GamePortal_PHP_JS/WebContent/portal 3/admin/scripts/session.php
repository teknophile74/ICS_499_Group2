<?php
	// Establishing Connection with Server by passing server_name, user_id and password as a parameter
	$conn=null;
	try {
		$user='hzwxuoyr_admin'; $pass='PASSWORD_GOES_HERE';
		$dbname='hzwxuoyr_adminlogin'; $host='localhost';
		$login_session='';
		
		$conn = new PDO("mysql:host=$host;dbname=$dbname", $user, $pass);
		// Selecting Database
		session_start (); // Starting Session
		                 // Storing Session
		// SQL Query To Fetch Complete Information Of User
		$qry = $conn->query("select username from login where username='{$_SESSION ['login_user']}'");
		$qry->setFetchMode(PDO::FETCH_ASSOC);
		while ($row = $qry->fetch()) {
			$login_session = $row['username'];
		}
		
		if (! isset ( $login_session )) { 
			$conn=null; // Closing DB Connection
			header ( 'location: ../login.php' ); // Redirecting To Home Page
		}
	}
	catch(PDOException $e) {
		echo "Error Connection to database - Please contact the Administrator";
	    //echo $e->getMessage();
	}
	finally {
		$conn=null; // Closing DB Connection
	}
?>
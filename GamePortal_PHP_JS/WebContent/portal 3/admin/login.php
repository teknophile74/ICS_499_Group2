<?php
include ('scripts/login.php'); // Includes Login Script
?>
<!DOCTYPE html>
<html>
<head>
<<<<<<< Upstream, based on origin/Prod
<title>Game Portal Admin Login</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="main">
		<h1>Game Portal Admin Login</h1>
=======
<title>Game Portal Login</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="main">
		<h1>Game Portal Login</h1>
>>>>>>> af97253 Adding Admin portal + updates to 4P1W game files
		<div id="login">
			<h2>Login Form</h2>
			<form action="" method="post">
				<label>UserName : 
					<input id="name" name="username" placeholder="username" type="text">
				</label>
				<label>Password : 
					<input id="password" name="password" placeholder="**********" type="password">
				</label>
				<input name="submit" type="submit" value=" Login ">
				<span><?php echo $error; ?></span>
			</form>
		</div>
	</div>
</body>
</html>

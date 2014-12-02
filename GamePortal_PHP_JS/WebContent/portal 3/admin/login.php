<?php
include ('scripts/login.php'); // Includes Login Script
?>
<!DOCTYPE html>
<html>
<head>
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
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
=======
<title>Game Portal Login</title>
=======
<title>Game Portal Admin Login</title>
>>>>>>> e2c46f5 Update to login and php pages
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="main">
		<h1>Game Portal Admin Login</h1>
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
>>>>>>> 2fb107e Adding base admin portal
=======
<title>Game Portal Admin Login</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="main">
		<h1>Game Portal Admin Login</h1>
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
>>>>>>> 872cb0e Merge to master

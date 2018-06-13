<<?php 
	
	$con = mysqli_connect("localhost","root","","daro_points");

	$email = $_GET['email'];
	$password = $_GET['password'];

	$sql = "SELECT `email` FROM `users` WHERE `email` = $email AND `password` = $password";

	$result = mysqli_query($con,$sql);

	if (!mysqli_num_rows($result)>0) {
		
		$status = "failed";
		echo json_encode("response">$status);
	}
	else
	{
		$row = mysqli_fetch_assoc($result);
		$email = $row['email'];
		$status = "ok";
		echo json_encode("response">$status, "email">$email);
	}

	mysqli_close($con);
 ?>
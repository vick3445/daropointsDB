<<?php 
	
	$con = mysqli_connect("localhost","root","","daro_points");

	$firstname = $_GET['firstname'];
	$lastname = $_GET['lastname'];
	$username = $_GET['username'];
	$password = $_GET['password'];
	$id_number = $_GET['id_number'];

	$sql = "SELECT * FROM `users` where `username` = '$username'";

	$result = mysqli_query($con,$sql);

	if (mysqli_num_rows($result)>0) 
	{
		$status = "exist";
	}
	else
	{
		$query = "INSERT INTO `users`(`id`, `first_name`, `last_name`, `username`, `password`, `id_number`) VALUES (null,'$firstname','$lastname','$username','$password','$id_number')";

		if (mysqli_query($con,$query)) 
		{
			$status = "ok";
		}
		else
		{
			$status = "error";
		}
	}
	
	echo json_encode(array("response"=>$status));

	mysqli_close($con);
 ?>
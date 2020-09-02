<?php
	define('HOST','213.171.200.84');
	define('USER','w1480440');
	define('PASS','Mohammed1');
	define('DB','FYPDB');
	


	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

		$username = $_POST['username'];
	
		$points = $_POST['points'];
	
	$sql = "UPDATE fyp_students SET points = points + '$points' WHERE username='$username'";
	
   mysqli_query($con,$sql);
   
   echo "success";
	
   
  

?>
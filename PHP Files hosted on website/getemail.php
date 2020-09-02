<?php
	define('HOST','213.171.200.84');
	define('USER','w1480440');
	define('PASS','Mohammed1');
	define('DB','FYPDB');
	


	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

		$username = $_POST['username'];
	
	$sql= "SELECT email FROM fyp_students WHERE username='$username'";
	
	$compare = mysqli_query($con,$sql);
	
	
 while ($row = mysqli_fetch_assoc($compare)) {
    echo $row['email'];
}

 
?>
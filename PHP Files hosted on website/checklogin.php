<?php
	define('HOST','213.171.200.84');
	define('USER','w1480440');
	define('PASS','Mohammed1');
	define('DB','FYPDB');
	


	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

		
		$username = $_POST['username'];
		$password = $_POST['password'];
	
	$sql2= "SELECT * FROM fyp_students WHERE username='$username' AND password='$password'";
	
	$compare = mysqli_query($con,$sql2);
	
	
 if(mysqli_num_rows($compare) > 0)
   {
	   
	   while($row = mysqli_fetch_assoc($compare)) {
    echo $row['classroomid'];
   }
   }
   
   else {
		
		 echo "failed";
   }

?>
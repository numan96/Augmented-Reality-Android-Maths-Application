<?php
	define('HOST','213.171.200.84');
	define('USER','w1480440');
	define('PASS','Mohammed1');
	define('DB','FYPDB');
	


	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

		$fullname = $_POST['fullname'];
		$username = $_POST['username'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		$classroomid = $_POST['classroomid'];
	
	$sql2= "SELECT * FROM fyp_students WHERE username='$username'";
	
	$compare = mysqli_query($con,$sql2);
	
	
 if(mysqli_num_rows($compare) > 0)
   {
    echo "name already exists";
   }
   
   else {
		
		$sql = "INSERT INTO fyp_students (fullname, username, password, classroomid, email) VALUES ('$fullname','$username','$password', '$classroomid','$email')";
		
   if(mysqli_query($con,$sql)){
			echo "Successfully Registered";
		}else{
			echo "Could not register";

		}
   }

?>
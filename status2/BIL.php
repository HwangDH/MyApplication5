<?php

if(isset($_GET['param1'])){

	$user = "";
	$pass = "";
	$host = "";
	$dbname = "";
	
	$red = $_GET['param1'];
	$green = $_GET['param2'];
	$blue = $_GET['param3'];
	$con = mysqli_connect($host,$user,$pass,$dbname);
	$stat = 0;
	
	if(!$con){
		die('could not connect:'.mysql_error());
	}
	
	if($green > 212){
   		if($blue >196){
      			$stat = 1;
   		}
		else{
      			$stat = 2;
		}
	}

	else{
   		if($green > 186 || $blue >162){
      			$stat = 3;
		}
   		else{
      			$stat = 4;
		}
	}
	echo "$stat";
	$sql = "INSERT INTO scv0319healthResult (BIL) VALUES ('$stat')";
	//echo "$sql";
	mysqli_query($con, $sql);  
	
	mysqli_close($con);
}
 	
else{
	echo "error";
}

?>
<?php

if(isset($_GET['param7'])){

	$user = "";
	$pass = "";
	$host = "";
	$dbname = "";
	
	$red = $_GET['param7'];
	$green = $_GET['param8'];
	$blue = $_GET['param9'];
	$con = mysqli_connect($host,$user,$pass,$dbname);
	$stat = 0;
	
	if($red > 249 && $green > 207 && $blue > 180)
   		$stat = 1;

	else if($red > 220 && $green > 150 && $blue > 167)
   		$stat = 2;
   
	else if($red > 157 && $green > 89 && $blue > 125)
 	  	$stat = 3;

	else if($red > 104 && $green > 49 && $blue > 81)
	  $stat = 4;

	else
 	  echo "error";
	
	$sql = "update scv0319healthResult set KET = '$stat' where KET=0";
	echo "$stat";
	mysqli_query($con, $sql);  
	
	mysqli_close($con);
}
 	
else{
	echo "error";
}

?>
<?php

if(isset($_GET['param10'])){

	$user = "";
	$pass = "";
	$host = "";
	$dbname = "";
	
	$red = $_GET['param10'];
	$green = $_GET['param11'];
	$blue = $_GET['param12'];
	$con = mysqli_connect($host,$user,$pass,$dbname);
	$stat = 0;
	
	if($red > 249 && $green > 241 && $blue > 230)
   		$stat = 1;

	else if($red > 244 && $green > 225 && $blue > 213)
   		$stat = 2;
   
	else if($red > 231 && $green > 197 && $blue > 209)
  	 	$stat = 3;

	else if($red > 200 && $green > 153 && $blue > 173)
  		$stat = 4;

	else
   		echo "error";
	
	$sql = "update scv0319healthResult set LEU = '$stat' where LEU=0";
	echo "$stat";
	mysqli_query($con, $sql);  
	
	mysqli_close($con);
}
 	
else{
	echo "error";
}

?>
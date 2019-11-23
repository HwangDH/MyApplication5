<?php

if(isset($_GET['param4'])){

	$user = "";
	$pass = "";
	$host = "";
	$dbname = "";
	
	$red = $_GET['param4'];
	$green = $_GET['param5'];
	$blue = $_GET['param6'];
	$con = mysqli_connect($host,$user,$pass,$dbname);
	$stat = 0;

	echo "asd";
	
	if(!$con){
		die('could not connect:'.mysql_error());
	}

	if($red > 235){
		$stat = 1;
	}

	else if($red >160){
		if($blue <119){
			$stat = 2;
		}
		else{
			$stat = 3;
		}
	}

	else if($red < 120 && $red > 80){
		$stat = 4;
	}

	else if($red < 70){
		$stat = 5;
	}

	else{
		echo "error";
	}
	
	$sql = "update scv0319healthResult set GLU = '$stat' where GLU=0";
	echo "$sql";
	mysqli_query($con, $sql);  
	
	mysqli_close($con);
}
 	
else{
	echo "error";
}

?>
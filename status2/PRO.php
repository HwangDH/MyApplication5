<?php

if(isset($_GET['param16'])){

	$user = "";
	$pass = "";
	$host = "";
	$dbname = "";
	
	$red = $_GET['param16'];
	$green = $_GET['param17'];
	$blue = $_GET['param18'];
	$con = mysqli_connect($host,$user,$pass,$dbname);
	$stat = 0;
	
	if($red > 226){
		if($green > 238 || $blue > 143)
			$stat = 1;
		else
			$stat = 2;
	} 

	else if($red >183 && $red < 216){
		$stat = 3;
	}

	else if($red > 146 && $red < 183){
		$stat = 4;
	}

	else if($red <146){
		$stat = 5;
	}

	else{
		echo "error";
	}
	
	$sql = "update scv0319healthResult set PRO = '$stat' where PRO=0";
	echo "$sql";
	mysqli_query($con, $sql);  
	
	mysqli_close($con);
}
 	
else{
	echo "error";
}

?>
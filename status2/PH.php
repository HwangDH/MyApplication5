<?php

if(isset($_GET['param13'])){

	$user = "";
	$pass = "";
	$host = "";
	$dbname = "";
	
	$red = $_GET['param13'];
	$green = $_GET['param14'];
	$blue = $_GET['param15'];
	$con = mysqli_connect($host,$user,$pass,$dbname);
	$stat = 0;
	
	if($red < 96){
   		$stat=5;
	}

	else if($red > 230){
 		if($green >188)
  			$stat = 2;
   		else
      			$stat = 1;
	}

	else if($red < 230 && $red > 178){
  		$stat = 3;
	}   

	else if($blue > 140){
  		if($red > 97)
      			$stat = 4;
	}	

	else
   		echo "error";
	
	$sql = "update scv0319healthResult set PH = '$stat' where PH=0";
	//echo "$sql";
	mysqli_query($con, $sql);  
	
	mysqli_close($con);
}
 	
else{
	echo "error";
}

?>
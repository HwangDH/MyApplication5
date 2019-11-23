<?php

if(isset($_GET['param100'])){

	$user = "";
	$pass = "";
	$host = "";
	$dbname = "";
	
	$weekend = $_GET['param100'];
	$today = date("Y-m-d");
	
	$con = mysqli_connect($host,$user,$pass,$dbname);
	
	
	if(!$con){
		die('could not connect:'.mysql_error());
	}
	$sql = "select testcheck from dogRegister where userid='scv0319'";

	$res = mysqli_query($con, $sql);  
   
  
	while($row= mysqli_fetch_array($res)){
		$str = strcmp($row[testcheck], $today);
      		if($str)
		{
			echo "false";
		}
		else{
			echo "true";
		}

   	}   
  	mysqli_close($con);
}

	

	
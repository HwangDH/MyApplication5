<?php

if(isset($_GET['param19'])){

	$user = "";
	$pass = "";
	$host = "";
	$dbname = "";
	
	$red = $_GET['param19'];
	$green = $_GET['param20'];
	$blue = $_GET['param21'];
	$con = mysqli_connect($host,$user,$pass,$dbname);
	$stat = 0;
	
	if($green+$blue > 415){
 	     $stat = 1;
	}

	else if($green+$blue<415 && $green+$blue>=371){
  	    $stat = 2;
	}   

	else if($green+blue<371 && $green+blue>=328){
 	     $stat = 3;
	}   
	
	else if($green+blue<328 && $green+blue>279){
	      	$stat = 4;
	}   


	else if($green+$blue<279 ){
   	   	$stat = 5;
	}   

	else
   		echo "error";
	
	$sql = "update scv0319healthResult set URO = '$stat' where URO=0";
	echo "$sql";
	mysqli_query($con, $sql);  
	
	mysqli_close($con);
}
 	
else{
	echo "error";
}

?>
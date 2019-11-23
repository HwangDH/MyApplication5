<?php

$userid = $_POST['userid'];
$username = $_POST['username'];
$userpassword = $_POST['userpassword'];
$userage = $_POST['userage'];

$user = "";
$pass = "";
$host= "";
$dbname="";

$con = mysqli_connect($host,$user,$pass,$dbname);

$sql = "update signup set userpassword='".$userpassword."', username = '".$username."', userage='".$userage."' where userid = '$userid';";

echo $sql;

if(mysqli_query($con,$sql)){
	echo  "success";
	
}else{
	echo "failed";
}

?>
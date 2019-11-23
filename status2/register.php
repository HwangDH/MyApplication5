<?php


$userid = $_POST['userid'];
$userpassword = $_POST['userpassword'];
$username = $_POST['username'];
$userage = $_POST['userage'];

$user = "";
$pass = "";
$host= "";
$dbname="";

$con = mysqli_connect($host,$user,$pass,$dbname);
$sql="insert into signup(userid,userpassword,username,userage) values('".$userid."','".$userpassword."','".$username."','".$userage."');";
if(mysqli_query($con,$sql)){
	echo  "success";
	
}else{
	echo "failed";
}

?>
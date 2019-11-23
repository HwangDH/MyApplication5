<?php
$dogname = $_POST['dogname'];
$dogage = $_POST['dogage'];
$doggender = $_POST['doggender'];
$dogweight = $_POST['dogweight'];
$dogbirth = $_POST['dogbirth'];
$testcheck = $_POST['testcheck'];
$userid = $_POST['userid'];

$user = "";
$pass = "";
$host= "";
$dbname="";

$con = mysqli_connect($host,$user,$pass,$dbname);

$sql = "update dogRegister set dogname='".$dogname."', dogage = '".$dogage."', doggender='".$doggender."', dogweight='".$dogweight."', dogbirth='".$dogbirth."', testcheck='".$testcheck."' where userid = '$userid';";

echo $sql;

if(mysqli_query($con,$sql)){
	echo  "success";
	
}else{
	echo "failed";
}

?>
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
$sql="insert into dogRegister(userid, dogname, dogage, doggender, dogweight, dogbirth, testcheck) values('".$userid."','".$dogname."','".$dogage."','".$doggender."','".$dogweight."','".$dogbirth."','".$testcheck."');";
$sql2 = "CREATE TABLE $userid.$dogname"."healthResult ( `id` INT NOT NULL AUTO_INCREMENT, `BIL` INT NOT NULL DEFAULT '10' , `BLO` INT NOT NULL DEFAULT '10' , `GLU` INT NOT NULL DEFAULT '10' , `KET` INT NOT NULL DEFAULT '10' , `LEU` INT NOT NULL DEFAULT '10' , `NIT` INT NOT NULL DEFAULT '10' , `PH` INT NOT NULL DEFAULT '10' , `PRO` INT NOT NULL DEFAULT '10' , `SG` INT NOT NULL DEFAULT '10' , `URO` INT NOT NULL DEFAULT '10' , PRIMARY KEY (`id`)) ENGINE = InnoDB;";

echo $sql2;

if(mysqli_query($con, $sql) && mysqli_query($con, $sql2)){
	echo  "success";
	
}else{
	echo "failed";
}

?>


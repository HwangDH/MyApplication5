<?php
//DB연결

$userid2 = $_GET['userid'];
$con = mysqli_connect('', '', '', '');

if(mysqli_connect_error($con)){
	echo "DB connect error : ". mysqli_connect_error();
}

//Query문
$sql = "select userid, dogname, dogage, doggender, testcheck, urinecount from dogRegister where userid = '$userid2'";

$result = mysqli_query($con, $sql);
$response = array();

$row = mysqli_fetch_array($result);
	array_push($response, array("userid"=>$row[0], "dogname"=>$row[1], "dogage"=>$row[2], "doggender"=>$row[3], "testcheck"=>$row[4], "urinecount"=>$row[5]));


echo json_encode(array("response"=>$response));
mysqli_close($con);
?>

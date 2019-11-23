<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $userid  = $_GET['userid'];
 $userpassword  = $_GET['userpassword'];
 
$user = "";
$pass = "";
$host= "";
$dbname="";

$con = mysqli_connect($host,$user,$pass,$dbname);
 
 $sql = "SELECT * FROM signup WHERE userid='".$userid."' and userpassword ='".$userpassword."'";
 

 $r = mysqli_query($con,$sql);
 
 $res = mysqli_fetch_array($r);
 
  $result = array();
 
 if(sizeof($res)>0){
	 
    array_push($result,array(	
 	"userid"=>$res['userid'],
 	"userpassword"=>$res['userpassword'],
 	"username"=>$res['username'],
 	"userage"=>$res['userage']
 ));
 
 echo json_encode(array("result"=>$result));
 
	 
 }else {
	 
	 array_push($result,array(
     "error"=>'error',
 
 )
 );
 
 echo json_encode(array("result"=>$result));
 }
 
 mysqli_close($con);
 
 }
 
?>
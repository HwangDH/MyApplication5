<?php 
 session_start(); // 세
 
$user = "";
$pass = "";
$host= "";
$dbname="";

$con = mysqli_connect($host,$user,$pass,$dbname);

 $userid  = $_POST['userid'];
 $userpassword  = $_POST['userpassword'];
 
 $sql = "SELECT * FROM box_register WHERE userid='$userid' and userpassword = '$userpassword'";
 $result = mysqli_query($con,$sql);
 $row = mysqli_fetch_array($result);
if($userid==$row['userid'] && $userpassword==$row['userpassword']){ // id와 pw가 맞다면 login

   $_SESSION['userid']=$row['userid'];
   $_SESSION['userpassword']=$row['userpassword'];
   echo "<script>location.href='test.html';</script>";

}else{ // id 또는 pw가 다르다면 login 폼으로
echo $userid;
echo $userpassword;
   echo "<script>window.alert('invalid username or password');</script>"; // 잘못된 아이디 또는 비빌번호 입니다
   echo "<script>location.href='login.html';</script>";

}
 
?>
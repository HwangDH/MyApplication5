<?php 
 session_start(); // ��
 
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
if($userid==$row['userid'] && $userpassword==$row['userpassword']){ // id�� pw�� �´ٸ� login

   $_SESSION['userid']=$row['userid'];
   $_SESSION['userpassword']=$row['userpassword'];
   echo "<script>location.href='test.html';</script>";

}else{ // id �Ǵ� pw�� �ٸ��ٸ� login ������
echo $userid;
echo $userpassword;
   echo "<script>window.alert('invalid username or password');</script>"; // �߸��� ���̵� �Ǵ� �����ȣ �Դϴ�
   echo "<script>location.href='login.html';</script>";

}
 
?>
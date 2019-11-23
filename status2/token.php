<?php 
function send_notification ($tokens, $data)
{
	$url = 'https://fcm.googleapis.com/fcm/send';
	//� ������ data/notification payload�� ����Ұ������� ���� ������ �˸��� ����� �޶� �� �� �ִ�.
	$msg = array(
		'title'	=> $data["title"],
		'body' 	=> $data["body"]
        );

	//data payload�� ������ ���� ��׶����̵� ���׶����̵� ������ �˸��� ������ ����.
	$fields = array(
		'registration_ids' => $tokens,
		'data' => $msg
	);
	
	//����Ű�� config.php�� ����Ǿ� �ִ�.
	  define('GOOGLE_API_KEY', 'AAAA4Hwbbuc:APA91bEFxQ_PDBkPtpmc1ZugHLS4Ev2gdSTgLmwAR56-rxjYSK5n4k06hUiPKof5kjPK6gklYbbMi1RRFEs4vCTW90XqVqfbCPjYyzNVf7WqhXW3cER3G2GI94q1PPm5B_hMGlruV6J8');
   //����Ű�� config.php�� ����Ǿ� �ִ�.
   $headers = array(
      'Authorization:key =' .GOOGLE_API_KEY,
      'Content-Type: application/json'
   );


	$ch = curl_init();
	curl_setopt($ch, CURLOPT_URL, $url);
       	curl_setopt($ch, CURLOPT_POST, true);
       	curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
       	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       	curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);  
       	curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
       	curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
       	$result = curl_exec($ch);           
       	if ($result == FALSE) {
       	    die('Curl failed: ' . curl_error($ch));
       	}
       	curl_close($ch);
       	return $result;
}


	//�̰� �ٽ��̴�.

	//�����ͺ��̽� ����� ��ū�� array������ ��� ��´�.
	$user = "";
	$pass = "";
	$host= "";
	$dbname="";

	$con = mysqli_connect($host,$user,$pass,$dbname);

	$sql = "Select token From hipas_member;";

	$result = mysqli_query($con,$sql);
	$tokens = array();
	if(mysqli_num_rows($result) > 0 ){
		//DB���� ������ ��ū�� ��� $tokens�� ��� �д�.
		while ($row = mysqli_fetch_assoc($result)) {
			$tokens[] = $row["token"]; 
		}
	}
	mysqli_close($con);

	//������������ ������ �Է��� ������� ������ �����Ѵ�.
	$mTitle = $_POST['title'];
	if($mTitle == '') {
		$mtitle="���������Դϴ�.";
	}
    	$mMessage = $_POST['message']; 
	//�˸��� ������� �����ؼ� $data�� ��� ��´�. ������Ʈ �ǵ��� ���� �ٸ��� �� ���� �� �ִ�.
	$inputData = array("title" => $mTitle, "body" => $mMessage);

	//�������� �˸��� ������ �Լ��� �����ϰ� �� ����� ȭ�鿡 ����� �ش�.
	$result = send_notification($tokens, $inputData);
	echo $result;

 ?>

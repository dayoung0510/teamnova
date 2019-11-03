<?php
	include "../db.php";
	include "../password.php";

	$userid = $_POST['userid'];
	$userpw = password_hash($_POST['userpw'], PASSWORD_DEFAULT);
	$userpwCheck = password_hash($_POST['userpwCheck'] , PASSWORD_DEFAULT);




	$username = $_POST['name'];
	$sex = $_POST['sex'];
	$email = $_POST['email'].'@'.$_POST['emadress'];
	$postal = $_POST['postal'];
	$address1 = $_POST['address1'];
	$address2 = $_POST['address2'];
	$address3 = $_POST['address3'];
	$phone = $_POST['phone'];








//if($userpw != $userpwCheck) {
//
//    header("Content-Type: text/html; charset=UTF-8");
//    echo "<script>alert('비밀번호가 맞지 않습니다.');history.back();</script>";
//    exit;
//
//
//} else
//    ;


$sql = mq("insert into member (id,pw,pwc,name,sex,email,postal,address1, address2, address3, phone) values('".$userid."','".$userpw."','".$userpwCheck."','".$username."','".$sex."','".$email."', '".$postal."', '".$address1."', '".$address2."', '".$address3."', '".$phone."')");




if($sql)
{
 $msg='회원가입이 완료되었습니다.';
}

else
{
    $msg='실패';
}


?>

<script>
    alert("<?php echo $msg?>");
</script>

<meta charset="utf-8" />
<meta http-equiv="refresh" content="0 url=/year/htdocs/index.php">
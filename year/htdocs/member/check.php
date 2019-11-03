<?php
	include "../db.php";

	$uid = $_GET["userid"];

	$sql = mq("select * from member where id='".$uid."'");
	$member = $sql->fetch_array();



	if($member==0)
	{
?>
	<div ><?php echo $uid; ?>는 사용가능한 아이디입니다.</div>
<?php 
	}else{
?>
	<div style='font-family:"malgun gothic"; color:#761c19;'><?php echo $uid; ?> 는 이미 존재하는 아이디 입니다.<div>
<?php
	}
?>





<button value="닫기" onclick="window.close()">닫기</button>
<script>
</script>
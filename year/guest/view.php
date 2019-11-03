<?php
	require_once("dbconfig.php");
	$bNo = $_GET['bno'];

	if(!empty($bNo) && empty($_COOKIE['board_free_' . $bNo])) {
		$sql = 'update board_free set b_hit = b_hit + 1 where b_no = ' . $bNo;
		$result = $db->query($sql); 
		if(empty($result)) {
			?>
			<script>
				alert('오류가 발생했습니다.');
				history.back();
			</script>
			<?php 
		} else {
			setcookie('board_free_' . $bNo, TRUE, time() + (60 * 60 * 24), '/');
		}
	}
	
	$sql = 'select b_title, b_content, b_date, b_hit, b_id from board_free where b_no = ' . $bNo;
	$result = $db->query($sql);
	$row = $result->fetch_assoc();

require_once("../htdocs/db.php");

include('../head.php');


?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title> Q&A </title>
		<link rel="stylesheet" href="../../../assets/css/main.css" />
<!-- 	<link rel="stylesheet" href="normalize.css" /> -->
	<link rel="stylesheet" href="board.css" />
	<script src="jquery-2.1.3.min.js"></script>
</head>
<body>
	<article style="color: #394C59" class="boardArticle">
		<h3> &nbsp; </h3>
		<div id="boardView" style="margin-left: 50px">
			<h3 id="boardTitle"><?php echo $row['b_title']?></h3>
			<div id="boardInfo">
				<span id="boardID">작성자: <?php echo $row['b_id']?></span>
				<span id="boardDate">작성일: <?php echo $row['b_date']?></span>
				<span id="boardHit">조회: <?php echo $row['b_hit']?></span>
			</div>
			<div id="boardContent"><?php echo $row['b_content']?></div>
			<div class="btnSet">
				<a href="./write.php?bno=<?php echo $bNo?>">수정</a>
				<a href="./delete.php?bno=<?php echo $bNo?>">삭제</a>
				<a href="./">목록</a>
			</div>
		<div id="boardComment">
			<?php require_once('./comment.php')?>
		</div>
		</div>
	</article>
</body>
</html>
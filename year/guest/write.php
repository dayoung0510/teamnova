<?php
require_once("../htdocs/db.php");

include('../head.php');

	//$_GET['bno']이 있을 때만 $bno 선언
	if(isset($_GET['bno'])) {
		$bNo = $_GET['bno'];
	}
		 
	if(isset($bNo)) {
		$sql = 'select b_title, b_content, b_id from board_free where b_no = ' . $bNo;
		$result = $db->query($sql);
		$row = $result->fetch_assoc();
	}

echo "<b>"; echo $user_id; echo "</b>";



function popup($vMsg,$vDestination) {
    echo("<html>\n");
    echo("<head>\n");
    echo("<title>System Message</title>\n");
    echo("<meta http-equiv=\"Content-Type\" content=\"text/html;
cht=iso-8859-1\">\n");

    echo("<script language=\"JavaScript\" type=\"text/JavaScript\">\n");
    echo("alert('$vMsg');\n");
    echo("window.location = ('$vDestination');\n");
    echo("</script>\n");
    echo("</head>\n");
    echo("<body>\n");
    echo("</body>\n");
    echo("</html>\n");
    exit;
}



if(isset($_SESSION['userid'])   ) {
    ;
}

else

    popup('회원만 이용 가능합니다.','/year/guest/index.php');


?>





<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title> Q&A </title>






	<link rel="stylesheet" href="board.css" />
</head>
<body>



	<article class="boardArticle" style="color: #394C59">
		<h3> 글 작성</h3>
		<div id="boardWrite">
			<form action="./write_update.php" method="post">
				<?php
				if(isset($bNo)) {
					echo '<input type="hidden" name="bno" value="' . $bNo . '">';
				}
				?>
				<table id="boardWrite">
					<caption class="readHide" style="color: #394C59"> 글 작성</caption>
					<tbody style="color: #394C59">
						<tr>
							<th scope="row"><label for="bID">아이디</label></th>
							<td class="id" type="text" name="bID" id="bID">

                                <?php
                                if(isset($_SESSION['userid'])){
                                    echo "{$_SESSION['userid']}";

                                }else{
                                    ;
                                }
                                ?>


<!--								--><?php
//								if(isset($bNo)) {
//									echo $row['b_id'];
//								} else { ?>
<!--									<input type="text" name="bID" id="bID">-->
<!--								--><?php //} ?>




							</td>
						</tr>
						<tr>
							<th scope="row"><label for="bPassword">비밀번호</label></th>
							<td class="password"><input type="password" name="bPassword" id="bPassword"></td>
						</tr>
						<tr>
							<th scope="row"><label for="bTitle">제목</label></th>
							<td class="title"><input type="text" name="bTitle" id="bTitle" value="<?php echo isset($row['b_title'])?$row['b_title']:null?>"></td>
						</tr>
						<tr>
							<th scope="row"><label for="bContent">내용</label></th>
							<td class="content"><textarea name="bContent" id="bContent"><?php echo isset($row['b_content'])?$row['b_content']:null?></textarea></td>
						</tr>
					</tbody>
				</table>
				<div class="btnSet">
					<button type="submit" class="btnSubmit btn">
						<?php echo isset($bNo)?'수정':'작성'?>
					</button>
					<a href="./index.php" class="btnList btn">목록</a>
				</div>
			</form>
		</div>
	</article>
</body>
</html>
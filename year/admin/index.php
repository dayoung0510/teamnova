<?php
include("check_session.php");
include("connection.php");
include ("../htdocs/db.php");
include ("../head.php");

$user_id=$_SESSION['user_id'];





if(isset($_GET['delete_product'])){
    include("delete_product.php");
}



if(isset($_GET['edit_product'])){
    include("edit_product.php");
}







?>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 관리자 페이지 </title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="style/css/bootstrap.min.css" rel="stylesheet">
<link href="style/css/k.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>


   	<div class="container-fluid main-container">
	<?php include("include/side_bar.php");?>

	<div class="col-md-9 content" style="margin-left:20px">
  	<div class="panel panel-default" style="margin-left:70px">
	<div class="panel-heading">
	</div><br>
	<div class="panel-body">
		<h3>
<?php  //success message
if(isset($_POST['success'])) {
$success = $_POST["success"];
echo "<h1 style='color:#0C0'> 상품이 성공적으로 등록되었습니다. &nbsp;&nbsp;  <span class='glyphicon glyphicon-ok'></h1></span>";
}
?></h3>
	</div>
</div></div></div>
<?php include("include/js.php"); ?>
</body>
</html>
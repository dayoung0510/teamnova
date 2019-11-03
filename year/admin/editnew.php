<?php


include("connection.php");
include ("../htdocs/db.php");
include ("../head.php");



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


<?php include("include/js.php"); ?>



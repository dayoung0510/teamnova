<?php


if($_SERVER['REQUEST_METHOD'] == 'POST') {

    $id = $_POST['id'];
    $nick = $_POST['nick'];
    $password = $_POST['password'];

    $password = password_hash($password, PASSWORD_DEFAULT);

    require_once 'connect.php';


    $sql = "INSERT INTO users_table(id, nick, password) VALUES ('$id', '$nick', '$password')";

    if( mysqli_query($conn, $sql) ) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);
    }


}




?>
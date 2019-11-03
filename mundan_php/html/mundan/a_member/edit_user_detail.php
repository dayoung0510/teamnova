<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $no = $_POST['no'];
    $nick = $_POST['nick'];

//    $id = $_POST['id'];
//    $password = $_POST['password'];

    require_once 'connect.php';


    $sql = "UPDATE users_table SET nick = '$nick' WHERE no ='$no' ";


    if(mysqli_query($conn, $sql)) {

        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);

    }
}


else
{

$result["success"] = "0";
$result["message"] = "Error!";
echo json_encode($result);

mysqli_close($conn);

}



?>
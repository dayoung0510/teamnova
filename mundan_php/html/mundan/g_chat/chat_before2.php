<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $saved = $_POST['saved'];
    $no = $_POST['no'];

    require_once '../connect.php';


    $sql = "UPDATE chat_before SET saved = '$saved' WHERE no ='$no' ";


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
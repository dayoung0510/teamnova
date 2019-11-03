<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $no = $_POST['no'];
    $note = $_POST['note'];
    $category = $_POST['category'];

    require_once ("../connect.php");

    $query = "UPDATE notes_image SET note = '$note' , category = '$category' WHERE no = '$no' ";



    if(mysqli_query ($conn, $query))
    {
        $response['success'] = 'true';
        $response['message'] = 'successfully';
    }

    else
    {
        $response['success'] = 'false';
        $response['message'] = 'failure!';
    }

}
else
{
    $response['success'] = 'false';
    $response['message'] = 'Error!';
}

echo json_encode($response);


?>
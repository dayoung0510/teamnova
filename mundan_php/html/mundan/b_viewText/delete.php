<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $no = $_POST['no'];

    require_once ("../connect.php");

    $query = "DELETE FROM notes_text WHERE no = '$no' ";



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
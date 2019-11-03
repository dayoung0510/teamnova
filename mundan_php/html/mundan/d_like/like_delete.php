<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $no = $_POST['post_idx'];
    $like_id = $_POST['like_id'];
    $jdg = $_POST['jdg'];

    require_once ("../connect.php");

    $query = "UPDATE user_like SET jdg = '$jdg' WHERE post_idx = '$no' AND like_id = '$like_id' ";



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
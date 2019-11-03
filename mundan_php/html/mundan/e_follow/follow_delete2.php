<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $to_nick = $_POST['to_nick'];
    $from_nick = $_POST['from_nick'];

    require_once ("../connect.php");

    $query = "DELETE FROM user_follow WHERE to_nick = '$to_nick' and from_nick = '$from_nick' ";



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
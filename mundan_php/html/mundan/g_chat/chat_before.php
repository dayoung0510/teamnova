<?php




if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $to_nick = $_POST['to_nick'];
    $from_nick = $_POST['from_nick'];
    $content = $_POST['content'];

    require_once ("../connect.php");

    $query = "INSERT INTO chat_before (to_nick, from_nick, content) 
                VALUES ('$to_nick', '$from_nick', '$content' )";

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
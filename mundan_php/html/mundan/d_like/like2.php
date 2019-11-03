<?php




if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $post_idx = $_POST['post_idx'];
    $like_id = $_POST['like_id'];
    $jdg = $_POST['jdg'];

    require_once ("../connect.php");


    $query = "INSERT INTO user_like2 (post_idx, like_id, jdg) VALUES ('$post_idx', '$like_id', '$jdg' ) ";

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
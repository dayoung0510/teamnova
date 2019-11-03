<?php






if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $id = $_POST['id'];
    $nick = $_POST['nick'];
    $note = $_POST['note'];
    $category = $_POST['category'];
    $picture = $_POST['picture'];
    $origin = $_POST['origin'];


    require_once ("../connect.php");

    $query = "INSERT INTO notes_image (id, nick, note, category, picture, origin) 
    VALUES ('$id', '$nick', '$note', '$category', '$picture', '$origin' )";

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
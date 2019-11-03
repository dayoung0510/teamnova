<?php




if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $cat = $_POST['cat'];
    $origin = $_POST['origin'];
    $picture = $_POST['picture'];

    require_once ("../connect.php");

    $query = "INSERT INTO admin_noti_image_ongoing (cat, origin, picture) 
                VALUES ('$cat', '$origin', '$picture')";

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
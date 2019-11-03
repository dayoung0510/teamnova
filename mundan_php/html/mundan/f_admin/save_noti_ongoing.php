<?php




if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $cat = $_POST['cat'];
    $subject = $_POST['subject'];
    $origin = $_POST['origin'];

    require_once ("../connect.php");

    $query = "INSERT INTO admin_noti_ongoing (cat, subject, origin) 
                VALUES ('$cat', '$subject', '$origin')";

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
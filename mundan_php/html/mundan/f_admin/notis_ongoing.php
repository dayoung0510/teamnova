<?php

header("Content-type:application/json");

require_once ('../connect.php');



$query = "SELECT * FROM admin_noti_ongoing  ORDER BY date DESC";
$result= mysqli_query($conn, $query);
$response = array();
while ( $row = mysqli_fetch_assoc($result) ) {
    array_push($response,
        array (
            'cat' => $row['cat'],
            'subject' => $row['subject'],
            'origin' => $row['origin']

        )
    );
}

echo json_encode($response);







?>


<?php
header("Content-type:application/json");


require_once ('../connect.php');


$post_idx = $_GET['post_idx'];


if (isset($_GET['post_idx']) ) {


    $query = "SELECT * FROM user_like WHERE post_idx = '$post_idx' AND jdg = '1' ";

    $result = mysqli_query($conn, $query);
    $response = array();

    while ($row = mysqli_fetch_assoc($result)) {


        array_push($response,
            array(

                'like_id' => $row['like_id'],
                'jdg' => $row['jdg']

            )
        );


    }
    echo json_encode($response);

}


mysqli_close($conn)



?>
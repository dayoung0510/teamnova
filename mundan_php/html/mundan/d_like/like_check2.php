<?php
header("Content-type:application/json");


require_once ('../connect.php');



if (isset($_GET['like_id']) ) {

    $like_id = $_GET['like_id'];
    $query = "SELECT * FROM user_like2 WHERE like_id LIKE '%$like_id%' ";

    $result = mysqli_query($conn, $query);
    $response = array();

    while ($row = mysqli_fetch_assoc($result)) {


        array_push($response,
            array(

                'post_idx' => $row['post_idx'],
                'jdg' => $row['jdg']

            )
        );


    }
    echo json_encode($response);

}


mysqli_close($conn)



?>
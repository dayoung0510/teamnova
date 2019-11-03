<?php
header("Content-type:application/json");


require_once ('../connect.php');



if (isset($_GET['to_nick']) ) {

    $to_nick = $_GET['to_nick'];
    $query = "SELECT * FROM user_follow WHERE to_nick = '$to_nick' ";

    $result = mysqli_query($conn, $query);
    $response = array();

    while ($row = mysqli_fetch_assoc($result)) {


        array_push($response,
            array(

                'from_nick' => $row['from_nick'],

            )
        );


    }
    echo json_encode($response);

}


mysqli_close($conn)



?>
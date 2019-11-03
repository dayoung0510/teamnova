<?php
header("Content-type:application/json");


require_once ('../connect.php');



if (isset($_GET['from_nick']) ) {

    $from_nick = $_GET['from_nick'];

    $query = "SELECT * FROM user_follow WHERE from_nick = '$from_nick' ";

    $result = mysqli_query($conn, $query);
    $response = array();

    while ($row = mysqli_fetch_assoc($result)) {


        array_push($response,
            array(

                'to_nick' => $row['to_nick'],
                'jdg' => $row['jdg']

            )
        );


    }
    echo json_encode($response);

}


mysqli_close($conn)



?>
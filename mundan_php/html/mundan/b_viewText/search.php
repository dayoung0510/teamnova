<?php

define('host', 'localhost');
define('name', 'pmauser');
define('pass', 'ekdud91');
define('dbase', 'mundan');

$conn = mysqli_connect(host, name, pass, dbase) or die('unable to connect');



if (isset($_GET['key']) )
{

    $key = $_GET['key'];
    $query = "SELECT * FROM notes_text WHERE note LIKE '%$key%' ";
    $result = mysqli_query($conn, $query);
    $response = array();
    while ( $row = mysqli_fetch_assoc($result) ) {

        array_push($response,
            array (
                'id' => $row['id'],
                'nick'=>$row['nick'],
                'note'=>$row['note'],
                'date'=>$row['date'],
                'subject'=>$row['subject'],
                'category'=>$row['category'],
                'origin'=>$row['origin'] )
        );

    }
    echo json_encode($response);
}

else
{

//    $query = "SELECT * FROM notes_text";
//    $result= mysqli_query($conn, $query);
//    $response = array();
//    while ( $row = mysqli_fetch_assoc($result) ) {
//        array_push($response,
//            array (
//                'id' => $row['id'],
//                'nick'=>$row['nick'],
//                'note'=>$row['note'],
//                'date'=>$row['date'],
//                'subject'=>$row['subject'],
//                'category'=>$row['category'],
//                'origin'=>$row['origin']
//            )
//        );
//    }
//
//    echo json_encode($response);

}


mysqli_close($conn)



?>
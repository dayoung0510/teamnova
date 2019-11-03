<?php

header("Content-type:application/json");

require_once('../connect.php');


if (isset($_GET['rb']) ) {

    $rb = $_GET['rb'];



    $query = mysqli_query($conn, "SELECT * FROM notes_image WHERE category LIKE '%$rb%' order by date DESC");

    $response = array();
    while ($row = mysqli_fetch_assoc($query))
    {

        array_push($response,
            array(

                'id' => $row['id'],
                'nick' => $row['nick'],
                'note' => $row['note'],
                'category' => $row['category'],
                'date' => $row['date'],
                /////
                'picture' => $row['picture'],
                'origin' => $row['origin'],
                ////
                'no' => $row['no']
            )
        );
    }



    echo json_encode($response);

}




else {

    $query = "SELECT * FROM notes_image order by date DESC";
    $result= mysqli_query($conn, $query);
    $response = array();
    while ( $row = mysqli_fetch_assoc($result) ) {
        array_push($response,
            array (
                'id' => $row['id'],
                'nick' => $row['nick'],
                'note' => $row['note'],
                'category' => $row['category'],
                'date' => $row['date'],
                /////
                'picture' => $row['picture'],
                'origin' => $row['origin'],
                ////
                'no' => $row['no'],
            )
        );
    }

    echo json_encode($response);


}

?>
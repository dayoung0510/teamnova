<?php

header("Content-type:application/json");

require_once('../connect.php');


if (isset($_GET['rb']) ) {

    $rb = $_GET['rb'];




//        $query = "SELECT * FROM notes_text LEFT JOIN user_like ON notes_text.no = user_like.post_idx
//                WHERE like_id='$like_id' ORDER BY date DESC";

    $query = mysqli_query($conn, "SELECT * FROM notes_text WHERE category LIKE '%$rb%' order by date DESC");

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
                    'subject' => $row['subject'],
                    'origin' => $row['origin'],
                    ////
                    'no' => $row['no']
                )
            );
        }



    echo json_encode($response);

}




else {

    $query = "SELECT * FROM notes_text order by date DESC";
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
                'subject' => $row['subject'],
                'origin' => $row['origin'],
                ////
                'no' => $row['no'],
            )
        );
    }

    echo json_encode($response);


}

?>
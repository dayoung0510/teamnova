<?php

header("Content-type:application/json");

require_once('../connect.php');




//    $query = mysqli_query($conn, "SELECT * FROM notes_text WHERE room LIKE '1' order by time ASC");
    $query = mysqli_query($conn, "SELECT * FROM chat_before order by time ASC");

    $response = array();
    while ($row = mysqli_fetch_assoc($query))
    {

        array_push($response,
            array(

                'to_nick' => $row['to_nick'],
                'from_nick' => $row['from_nick'],
                'content' => $row['content'],
                'time' => $row['time'],
                'no' => $row['no'],
                'room' => $row['room'],
                'saved' => $row['saved']
            )
        );
    }



    echo json_encode($response);






?>
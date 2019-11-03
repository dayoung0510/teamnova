<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST' ) {

    $id = $_POST['id'];
    $password = $_POST['password'];

    require_once  'connect.php';

    $sql = "SELECT * FROM users_table WHERE id = '$id' ";
    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();


    if (mysqli_num_rows($response) === 1) {

        $row = mysqli_fetch_assoc($response);
        if ( password_verify ($password, $row['password'])) {


            $index['no'] = $row ['no'];
            $index['id'] = $row ['id'];
            $index['nick'] = $row['nick'];
            $index['password'] = $row['password'];

            array_push($result['login'], $index);

            $result['success'] = "1";
            echo json_encode($result);

            mysqli_close($conn);
        }

        else
            {

                $result['success'] = "0";
                $result['message'] = "error";
                echo json_encode($result);

                mysqli_close($result);
                mysqli_close($conn);

        }

    }

}


?>
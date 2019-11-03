<?php




if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $cat = $_POST['cat'];
    $origin = $_POST['origin'];
    $picture = $_POST['picture'];

    require_once ("../connect.php");

    $query = "INSERT INTO admin_noti_image_reservation (cat, origin)
                VALUES ('$cat', '$origin')";



    if ( mysqli_query($conn, $query) ){

            $id = mysqli_insert_id($conn);
            $path = "admin_picture/$id.jpeg";
            $finalPath = "/mundan/f_admin/".$path;

            $insert_picture = "UPDATE admin_noti_image_reservation SET picture='$finalPath' WHERE no='$id' ";

            if (mysqli_query($conn, $insert_picture))
            {

                if ( file_put_contents( $path, base64_decode($picture) ) )
                {
                    $result["value"] = "1";
                    $result["message"] = "Success!";

                    echo json_encode($result);
                    mysqli_close($conn);

                }

                else
                {
                    $response["value"] = "0";
                    $response["message"] = "Error! ".mysqli_error($conn);
                    echo json_encode($response);

                    mysqli_close($conn);
                }

            }


    }

    else
    {
        $response["value"] = "0";
        $response["message"] = "Error! ".mysqli_error($conn);
        echo json_encode($response);

        mysqli_close($conn);
    }
}

?>
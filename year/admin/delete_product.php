<?php

require_once ('../htdocs/db.php');
include ('connection.php');



    if(isset($_GET['delete_product'])){

        $delete_id = $_GET['delete_product'];

        $delete_pro = "delete from products where product_id='$delete_id'";

        $run_delete = mysqli_query($con,$delete_pro);

        if($run_delete){

            echo "<script>alert('삭제되었습니다.')</script>";

            echo "<script>window.open('index.php?view_products','_self')</script>";

        }

        else {
            echo "<script>alert('실패했습니다')</script>";
        }

    }

?>





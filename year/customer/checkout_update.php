<?php

include ("../htdocs/db.php");
include ("../admin/connection.php");


$userid = $_SESSION['userid'];

$user_phone = $_POST['user_phone'];
$user_address1 = $_POST['user_address1'];
$user_address2 = $_POST['user_address2'];
$user_postal = $_POST['user_postal'];

$invoice_no = mt_rand();












$select_cart = "select * from cart where uid='$userid'";
$run_cart = mysqli_query($con,$select_cart);

while($row_cart = mysqli_fetch_array($run_cart)){

    $pro_id = $row_cart['p_id'];
    $pro_qty = $row_cart['qty'];

    $get_products = "select * from products where product_id='$pro_id'";
    $run_products = mysqli_query($con,$get_products);



    while($row_products = mysqli_fetch_array($run_products)){


        $sub_total = $row_products['product_price']*$pro_qty;

        $insert_order = "insert into user_order (uid, phone, address1, address2, postal, pid, qty, subtotal, rand) values('" . $userid . "','" . $user_phone . "','" . $user_address1 . "','" . $user_address2 . "', '" . $user_postal . "', '" . $pro_id . "', '" . $pro_qty . "', '" . $sub_total . "','" . $invoice_no . "')";
        $run_order = mysqli_query($con,$insert_order);

    }

    $total += $sub_total;





    session_start();
    $_SESSION['total_won'] = $total +2500;


}









if($run_order){

    echo "<script>window.open('pay.php','_self')</script>";








}

else {
    echo "<script>alert('실패')</script>";
}





?>




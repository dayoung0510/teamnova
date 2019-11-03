<?php

require_once ('../htdocs/db.php');
include ('../admin/connection.php');
include ('../head.php');



$pro_id = $_GET['product_id'];





?>





<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> 관리자 페이지</title>







    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="style/css/bootstrap.min.css" rel="stylesheet">
    <link href="style/css/k.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>

<div class="container-fluid main-container">
    <?php include("include/side_bar.php");?>

    <div class="col-md-9 content" style="margin-left:70px">
        <div class="panel-heading" style="background-color:#e0e0e0">
            <h1> 주문리스트 <?php echo $page;?> </h1></div><br>
        <div class='table-responsive'>
            <div style="overflow-x:scroll;">
                <table class="table  table-hover table-striped" style="font-size:18px">

                    <th>주문자ID</th>
                    <th>주문번호</th>
                    <th>연락처</th>
                    <th>주소</th>
                    <th>우편번호</th>
                    <th>상품명</th>
                    <th>수량</th>
                    <th>금액</th>
                    <th>결제상태</th>
                    <th>주문시간</th>




                    <?php


                    if(!isset($_GET['p_cat'])){
                        if(!isset($_GET['cat'])){

                            $per_page=6;

                            if(isset($_GET['page'])){
                                $page = $_GET['page'];

                            }else{
                                $page=1;
                            }

                            $start_from = ($page-1) * $per_page;

                            $get_orders = "select * from user_order order by 1 DESC LIMIT $start_from,$per_page";

                            $run_orders = mysqli_query($con,$get_orders);

                            while($row_orders=mysqli_fetch_array($run_orders)){

                                $user_id = $row_orders['uid'];
                                $rand = $row_orders['rand'];
                                $phone = $row_orders['phone'];
                                $address1 = $row_orders['address1'];
                                $address2 = $row_orders['address2'];
                                $postal = $row_orders['postal'];

                                $pro_id = $row_orders['pid'];
                                $qty = $row_orders['qty'];
                                $subtotal = $row_orders['subtotal'];
                                $status = $row_orders['status'];
                                $time = $row_orders['time'];



                                $get_products = "select * from products where product_id='$pro_id'";
                                $run_products = mysqli_query($con,$get_products);
                                while($row_products = mysqli_fetch_array($run_products)){

                                    $product_title = $row_products['product_title'];
                                }

                                echo "
                                

                                    <tr>
                                    
                                    <td>$user_id</td>
                                    <td>$rand</td>
                                    <td>$phone</td>
                                    <td>$address1 $address2</td>
                                    <td>$postal</td>
                                    
                                    <td>$product_title</td>
                                    <td>$qty</td>
                                    <td>$subtotal</td>
                                    <td>$status</td>
                                    <td>$time</td>
                                    
                                    
                                    
                                    

                                    </tr>



                                                       
                                ";
                            }

                            ?>



                            <?php


                        }
                    } ?>


                </table>
            </div></div>






    </div><!-- col-md-9 Finish -->

</div><!-- container Finish -->
</div><!-- #content Finish -->



</div><!-- container Finish -->
</div><!-- #content Finish -->

















</div></div>
<?php include("include/js.php");?>
</body>
</html>
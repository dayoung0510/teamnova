<?php
include('db.php');

include ('../head.php');

require_once ('../htdocs/db.php');
include ('../admin/connection.php');

$userid = $_SESSION['userid'];

?>



<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> DY's store </title>




</head>




<body>
<span style="color: #5e5e5e">

    <div class="container-fluid">

        <h3>    <?php echo "{$_SESSION['userid']}"; ?> 의 마이페이지 </h3> <br><br>

</div>
</span>






<div class="container-fluid main-container">

    <div class="col-md-9 content" style="margin-left:70px">
        <div class="panel-heading" style="background-color:#e0e0e0">
            <h1> 나의 주문 <?php echo $page;?> </h1></div><br>
        <div class='table-responsive'>
            <div style="overflow-x:scroll;">
                <table class="table  table-hover table-striped" style="font-size:18px">


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

                            $get_orders = "select * from user_order where uid='$userid'";

                            $run_orders = mysqli_query($con,$get_orders);

                            while($row_orders=mysqli_fetch_array($run_orders)){


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
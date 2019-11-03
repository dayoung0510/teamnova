<?php 

    $active='Account';
    include("../header.php");
    include ("../head.php");
    include ("../htdocs/db.php");


include ("../admin/connection.php");



function popup($vMsg,$vDestination) {
    echo("<html>\n");
    echo("<head>\n");
    echo("<title>System Message</title>\n");
    echo("<meta http-equiv=\"Content-Type\" content=\"text/html;
cht=iso-8859-1\">\n");

    echo("<script language=\"JavaScript\" type=\"text/JavaScript\">\n");
    echo("alert('$vMsg');\n");
    echo("window.location = ('$vDestination');\n");
    echo("</script>\n");
    echo("</head>\n");
    echo("<body>\n");
    echo("</body>\n");
    echo("</html>\n");
    exit;
}



if(isset($_SESSION['userid'])){
    ;

}else{
    popup('회원만 이용 가능합니다.','/year/htdocs/index.php');
}






$ip_add = getRealIpUser();

//유저별로 장바구니 구분하면서 끼워넣은 것
$uid = $_SESSION['userid'];

$select_cart = "select * from cart where uid='$uid'";

$run_cart = mysqli_query($con,$select_cart);

$count = mysqli_num_rows($run_cart);






//회원정보 받아오는 부분

$select_user = "select * from member where id='{$_SESSION['userid']}'";
$run_user = mysqli_query($con,$select_user);
$row_user = mysqli_fetch_array($run_user);


$user_name = $row_user['name'];
$user_email = $row_user['email'];
$user_postal = $row_user['postal'];
$user_address1 = $row_user['address1'];
$user_address2 = $row_user['address2'];
$user_phone = $row_user['phone'];




?>


<div class="table-responsive"><!-- table-responsive Begin -->

    <table class="table"><!-- table Begin -->

        <thead><!-- thead Begin -->

        <tr><!-- tr Begin -->

            <th colspan="1"> 상품이미지  </th>
            <th colspan="1"> 상품명 </th>
            <th> 수량 </th>
            <th>가격(개당) </th>
            <!--                                       <th>Size</th>-->
            <th colspan="1"> 총액 </th>

        </tr><!-- tr Finish -->

        </thead><!-- thead Finish -->

        <tbody><!-- tbody Begin -->

        <?php

        $total = 0;

        while($row_cart = mysqli_fetch_array($run_cart)){

        $pro_id = $row_cart['p_id'];

        $pro_qty = $row_cart['qty'];

        $get_products = "select * from products where product_id='$pro_id'";

        $run_products = mysqli_query($con,$get_products);

        while($row_products = mysqli_fetch_array($run_products)){

        $product_title = $row_products['product_title'];

        $product_img1 = $row_products['product_img1'];

        $only_price = $row_products['product_price'];

        $sub_total = $row_products['product_price']*$pro_qty;

        $total += $sub_total;

        ?>

        <tr><!-- tr Begin -->

            <td>

                <img class="img-responsive" src="../admin/product_images/<?php echo $product_img1; ?>" alt="Product 3a" style='width: 100px; height: 100px;'>

            </td>

            <td>

                <a href="../details.php?pro_id=<?php echo $pro_id; ?>"> <?php echo $product_title; ?> </a>

            </td>

            <td id="qty">

                <?php echo $pro_qty; ?>

            </td>

            <td>

                <?php echo $only_price; ?>

            </td>



            <td>

                <?php echo number_format($sub_total); ?> 원

            </td>

        </tr><!-- tr Finish -->


        <?php } } ?>

        </tbody><!-- tbody Finish -->



    </table><!-- table Finish -->

</div><!-- table-responsive Finish -->
















       <div class="container well">
           <center><h2> 받으시는 분 </h2></center>
           <hr>
           <form method="post" action="checkout_update.php" class="form-horizontal">



               <div class="row">
                   <div class="col-md-9">
                       <h3> 구매 정보 </h3>
                       <div class="form-group">
                           <div class="input-group">
                               <div class="input-group-addon addon-diff-color">
                                   <span class="fas fa-user"></span>
                               </div>
                               <input class="form-control" type="text" id="user_name" name="user_name" placeholder="<?php echo $user_name; ?>" value="<?php echo $user_name; ?>">
                           </div>
                       </div>

                       <div class="form-group">
                           <div class="input-group">
                               <div class="input-group-addon addon-diff-color">
                                   <span class="fas fa-envelope"></span>
                               </div>
                               <input class="form-control" type="text" id="user_email" name="user_email" placeholder="<?php echo $user_email; ?>" value="<?php echo $user_email; ?>">
                           </div>
                       </div>

                       <div class="form-group">
                           <div class="input-group">
                               <div class="input-group-addon addon-diff-color">
                                   <span class="fas fa-phone"></span>
                               </div>
                               <input class="form-control" type="text" id="user_phone" name="user_phone" placeholder="<?php echo $user_phone; ?>" value="<?php echo $user_phone; ?>">
                           </div>
                       </div>


                       <div class="form-group">
                           <div class="input-group">
                               <div class="input-group-addon addon-diff-color">
                                   <span class="fas fa-home"></span>
                               </div>
                               <input class="form-control" type="text" id="user_address1" name="user_address1" placeholder="<?php echo $user_address1; ?>" value="<?php echo $user_address1; ?>">
                           </div>
                       </div>


                       <div class="row">
                           <div class="col-md-5">
                               <div class="form-group">
                                   <div class="input-group">
                                       <div class="input-group-addon addon-diff-color">
                                           <span class="fas fa-home"></span>
                                       </div>
                                       <input class="form-control" type="text" id="user_address2" name="user_address2" placeholder="<?php echo $user_address2; ?>" value="<?php echo $user_address2; ?>">
                                   </div>
                               </div>
                           </div>
                           <div class="col-md-5 col-md-offset-2">
                               <div class="form-group">
                                   <div class="input-group">
                                       <div class="input-group-addon addon-diff-color">
                                           <span class="fas fa-home"></span>
                                       </div>
                                       <input class="form-control" type="text" id="user_postal" name="user_postal" placeholder="<?php echo $user_postal; ?>" value="<?php echo $user_postal; ?>">
                                   </div>
                               </div>
                           </div>
                       </div>
                   </div>

                   <table class="table"><!-- table Begin -->

                       <tbody><!-- tbody Begin -->



                       <tr><!-- tr Begin -->

                           <td> 상품금액 </td>
                           <th> <?php echo number_format($total); ?> 원 </th>

                       </tr><!-- tr Finish -->

                       <tr><!-- tr Begin -->

                           <td> 배송비 </td>
                           <td> 2,500 원 </td>

                       </tr><!-- tr Finish -->



                       <tr class="total"><!-- tr Begin -->

                           <td> 총계 </td>
                           <th> <?php echo number_format($total + 2500); ?> 원 </th>
                           <input type="hidden" name="total" value="<?php echo $total ?> ">


                       </tr><!-- tr Finish -->

                       </tbody><!-- tbody Finish -->

                   </table><!-- table Finish -->











                   <div class="text-right">
                       <input type="submit" value="결제하기" class="btn btn-default" style="background-color: #394C59; color: #FFFFFF">
                   </div>

               </div>
           </form>
       </div>








   


    <script src="../styles/js/jquery-331.min.js"></script>
    <script src="../styles/js/bootstrap-337.min.js"></script>
<script src="../styles/style.css"></script>
    
    
</body>
</html>
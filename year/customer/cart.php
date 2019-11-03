<?php 

    $active='Cart';
    include("../header.php");
    include ("../htdocs/db.php");
    include ("../admin/connection.php");
    include ("../head.php");



?>
   
<!--   <div id="content">-->
<!--       <div class="container">-->

           
           <div id="cart" class="col-md-9"><!-- col-md-9 Begin -->
               
               <div class="box"><!-- box Begin -->
                   
                   <form action="cart.php" method="post" enctype="multipart/form-data"><!-- form Begin -->
                       
                       <h1> 장바구니 </h1>
                       
                       <?php 
                       
                       $ip_add = getRealIpUser();

                       //유저별로 장바구니 구분하면서 끼워넣은 것
                       $uid = $_SESSION['userid'];
                       
                       $select_cart = "select * from cart where uid='$uid'";
                       
                       $run_cart = mysqli_query($con,$select_cart);
                       
                       $count = mysqli_num_rows($run_cart);
                       
                       ?>
                       
                       <p class="text-muted"> <strong> <?php echo $count; ?> 개의 상품을 담았습니다. </strong></p>
                       
                       <div class="table-responsive"><!-- table-responsive Begin -->
                           
                           <table class="table"><!-- table Begin -->
                               
                               <thead><!-- thead Begin -->
                                   
                                   <tr><!-- tr Begin -->

                                       <th colspan="1"> 상품이미지  </th>
                                       <th colspan="1"> 상품명 </th>
                                       <th> 수량 </th>
                                       <th>가격(개당) </th>
                                       <th colspan="1"> 총액 </th>
                                       
                                   </tr><!-- tr Finish -->
                                   
                               </thead><!-- thead Finish -->
                               
                               <tbody><!-- tbody Begin -->
                                  
                                  <?php 
                                   
                                   $total = 0;
                                   
                                   while($row_cart = mysqli_fetch_array($run_cart)){
                                       
                                     $pro_id = $row_cart['p_id'];
                                       
//                                     $pro_size = $row_cart['size'];
                                       
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
                                       
                                       <td>


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
                               
                               <tfoot><!-- tfoot Begin -->
                                   
                                   <tr><!-- tr Begin -->
                                       
                                       <th colspan="4">Total</th>
                                       <th colspan="1"><?php echo number_format($total); ?> 원 </th>
                                       
                                   </tr><!-- tr Finish -->
                                   
                               </tfoot><!-- tfoot Finish -->
                               
                           </table><!-- table Finish -->
                           
                       </div><!-- table-responsive Finish -->
                       
                       <div class="box-footer"><!-- box-footer Begin -->
                           
                           <div class="pull-left"><!-- pull-left Begin -->
                               
                               <a href="index.php" class="btn btn-default"><!-- btn btn-default Begin -->
                                   
                                   <i class="fa fa-chevron-left"></i> 쇼핑 계속하기
                                   
                               </a><!-- btn btn-default Finish -->
                               
                           </div><!-- pull-left Finish -->
                           
                           <div class="pull-right"><!-- pull-right Begin -->
                               

                               <a href="checkout.php" class="btn btn-primary" style="background-color: #394C59; color: #FFFFFF">
                                   
                                   결제하기  <i class="fa fa-chevron-right"></i>
                                   
                               </a>

                           </div><!-- pull-right Finish -->
                           
                       </div><!-- box-footer Finish -->
                       
                   </form><!-- form Finish -->
                   
               </div><!-- box Finish -->




               <?php 
               
                function update_cart(){
                    
                    global $con;

                    //update 버튼의 값을 포스트 방식으로 받아와서 동작함
                    if(isset($_POST['update'])){
                        
                        foreach($_POST['remove'] as $remove_id){
                            
                            $delete_product = "delete from cart where p_id='$remove_id'";
                            
                            $run_delete = mysqli_query($con,$delete_product);
                            
                            if($run_delete){
                                
                                echo "<script>window.open('cart.php','_self')</script>";
                                
                            }
                            
                        }
                        
                    }
                    
                }
               
               echo @$up_cart = update_cart();
               
               ?>













               <br><br><br>
               <div class="col-md-5"><!-- col-md-3 Begin -->

                   <div id="order-summary" class="box"><!-- box Begin -->




                       <div class="table-responsive"><!-- table-responsive Begin -->

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

                               </tr><!-- tr Finish -->

                               </tbody><!-- tbody Finish -->

                           </table><!-- table Finish -->

                       </div><!-- table-responsive Finish -->

                   </div><!-- box Finish -->

               </div><!-- col-md-3 Finish -->


               </div><!-- #row same-heigh-row Finish -->



           </div><!-- col-md-9 Finish -->






<br>
           





















           
       </div><!-- container Finish -->
   </div><!-- #content Finish -->
   


    
    <script src="../styles/js/jquery-331.min.js"></script>
    <script src="../styles/js/bootstrap-337.min.js"></script>
    
    
</body>
</html>
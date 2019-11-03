<?php 

    $active='Cart';
    include("header.php");
    include("head.php");
    include ("htdocs/db.php");




?>





<html>
<head>



    <title> DY'shop </title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link rel="stylesheet" href="styles/bootstrap337.min.css">









</head>






<div id="content"><!-- #content Begin -->
    <div class="container"><!-- container Begin -->


        <div class="col-md-3"><!-- col-md-3 Begin -->

            <?php

            include("includes/sidebar.php");

            ?>

        </div><!-- col-md-3 Finish -->

        <div class="col-md-9"><!-- col-md-9 Begin -->
            <div id="productMain" class="row"><!-- row Begin -->
                <div class="col-sm-6"><!-- col-sm-6 Begin -->
                    <div id="mainImage"><!-- #mainImage Begin -->
                        <div id="myCarousel" class="carousel slide" data-ride="carousel"><!-- carousel slide Begin -->


                            <div class="carousel-inner">
                                <div class="item active">
                                    <center><img class="img-responsive" src="admin/product_images/<?php echo $pro_img1; ?>" alt="Product 3-a"></center>
                                </div>


                            </div>



                        </div><!-- carousel slide Finish -->
                    </div><!-- mainImage Finish -->
                </div><!-- col-sm-6 Finish -->

                <div class="col-sm-6"><!-- col-sm-6 Begin -->
                    <div class="box"><!-- box Begin -->
                        <h1 class="text-center"> <?php echo $pro_title; ?> </h1>

                        <p class="price"> <?php echo number_format($pro_price);?> 원 </p>

                        <?php add_cart(); ?>

                        <form action="details.php?add_cart=<?php echo $product_id; ?>" class="form-horizontal" method="post"><!-- form-horizontal Begin -->
                            <div class="form-group"><!-- form-group Begin -->
                                <label for="" class="col-md-5 control-label"> 수량 </label>

                                <div class="col-md-7"><!-- col-md-7 Begin -->
                                    <select name="product_qty" id="" class="form-control"><!-- select Begin -->
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select><!-- select Finish -->


                                    <br>
                                    <p class="text-center buttons"><button class="btn btn-primary"  style="color: #FFFFFF; background-color: #394C59"> <span class=" i fa fa-shopping-cart"> 장바구니 </button></p>





                                </div><!-- col-md-7 Finish -->



                            </div><!-- form-group Finish -->




                        </form><!-- form-horizontal Finish -->

                    </div><!-- box Finish -->


                </div><!-- col-sm-6 Finish -->


            </div><!-- row Finish -->

            <br><br><br>
            <div class="box" id="details"><!-- box Begin -->

                <h4> 상품 상세 </h4>

                <p>

                    <br>
                    <?php echo $pro_desc; ?>
                    <br><br>
                    <img class="col-md-pull-9" src="admin/product_images/<?php echo $pro_img2; ?>">


                </p>





            </div><!-- box Finish -->














<br><br><br><br><br><br><br><br><br>
            <div id="row same-heigh-row"><!-- #row same-heigh-row Begin -->
                <div class="col-md-3 col-sm-6"><!-- col-md-3 col-sm-6 Begin -->
                    <div class="box same-height headline"><!-- box same-height headline Begin -->
                        <h4 class="text-center"> 다른 상품 둘러보기 </h4>
                    </div><!-- box same-height headline Finish -->
                </div><!-- col-md-3 col-sm-6 Finish -->



                <?php





                $get_products = "select * from products order by rand() LIMIT 0,3";

                $run_products = mysqli_query($con,$get_products);

                while($row_products=mysqli_fetch_array($run_products)){

                    $pro_id = $row_products['product_id'];

                    $pro_title = $row_products['product_title'];

                    $pro_img1 = $row_products['product_img1'];

                    $pro_price = $row_products['product_price'];

                    echo "
                       
                        <div class='col-md-3 col-sm-6 center-responsive'>
                        
                            <div class='product same-height'>
                            
                                <a href='details.php?pro_id=$pro_id'>
                                
                                    <img class='img-responsive' src='admin/product_images/$pro_img1'>
                                
                                </a>
                                
                                <div class='text'>
                                
                                    <h3> <a href='details.php?pro_id=$pro_id'> $pro_title </a> </h3>
                                    
                                    <p class='price'> $pro_price 원 </p>
                                
                                </div>
                            
                            </div>
                        
                        </div>
                       
                       ";

                }

                ?>

            </div><!-- #row same-heigh-row Finish -->

        </div><!-- col-md-9 Finish -->

    </div><!-- container Finish -->
</div><!-- #content Finish -->

<?php

include("includes/footer.php");

?>


















<script src="js/jquery-331.min.js"></script>
<script src="js/bootstrap-337.min.js"></script>


</body>
</html>
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
    <title>Admin Panel</title>







    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="style/css/bootstrap.min.css" rel="stylesheet">
    <link href="style/css/k.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>

<div class="container-fluid main-container">
    <?php include("include/side_bar.php");?>

    <div class="col-md-9 content" style="margin-left:70px">
        <div class="panel-heading" style="background-color:#c4e3f3">
            <h1> 상품리스트 <?php echo $page;?> </h1></div><br>
        <div class='table-responsive'>
            <div style="overflow-x:scroll;">
                <table class="table  table-hover table-striped" style="font-size:18px">

                    <th>상품이미지</th>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>삭제</th>
                    <th>수정</th>





                            <?php


                            if(!isset($_GET['p_cat'])){
                            if(!isset($_GET['cat'])){

                            $per_page=5;

                            if(isset($_GET['page'])){
                                $page = $_GET['page'];

                            }else{
                                $page=1;
                            }

                            $start_from = ($page-1) * $per_page;

                            $get_products = "select * from products order by 1 DESC LIMIT $start_from,$per_page";

                            $run_products = mysqli_query($con,$get_products);

                            while($row_products=mysqli_fetch_array($run_products)){

                                $pro_id = $row_products['product_id'];
                                $pro_title = $row_products['product_title'];
                                $pro_price = $row_products['product_price'];
                                $pro_img1 = $row_products['product_img1'];

                                echo "
                                

                                    <tr>
                                    <td><img src='product_images/$pro_img1' style='width:70px; height:70px; border:groove #000'></td>
                                    <td>$pro_title</td>
                                    <td>$pro_price</td>
                                    
                                    
                                    
                                    
                                    <td><a href=\"index.php?delete_product=$pro_id\" onclick=\"if(!confirm('삭제하시겠습니까?')){return false;}\"> 삭제 </a></td>
                                    
                                    
                                    
                                    
                                    <td><a href=\"editnew.php?edit_product=$pro_id\"> 수정 </a></td>
                                    </tr>



                                                       
                                ";
                            }

                            ?>




            <?php


            }
            } ?>


                </table>



                <!-- 페이지네이션 -->
                <center>
                    <ul class="pagination">
                        <?php

                        $query = "select * from products";
                        $result = mysqli_query($con,$query);
                        $total_records = mysqli_num_rows($result);
                        $total_pages = ceil($total_records / $per_page);

                        echo "                        
                            <li>                            
                                <a href='product_list.php?page=1' style='color: black'> ".'처음'." </a>                            
                            </li>                        
                        ";

                        for($i=1; $i<=$total_pages; $i++){

                            echo "                        
                            <li>                            
                                <a href='product_list.php?page=".$i."'> ".$i." </a>                            
                            </li>                        
                        ";
                        };

                        echo "                        
                            <li>                            
                                <a href='product_list.php?page=$total_pages' style='color: black'> ".'끝'." </a>                            
                            </li>                        
                        ";


                        ?>

                    </ul><!-- 페이지네이션 Finish -->
                </center>




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
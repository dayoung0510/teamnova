<?php

include ("../htdocs/db.php");
include ("../head.php");
include("connection.php");


?>



    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> 관리자 페이지 </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="style/css/bootstrap.min.css" rel="stylesheet">
        <link href="style/css/k.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    </head>
    <body>


    <div class="container-fluid main-container">
        <?php include("include/side_bar.php");?>

        <div class="col-md-9 content" style="margin-left:20px">
            <div class="panel panel-default" style="margin-left:70px">

                <div class="panel-heading">
                    <h1><span class="glyphicon glyphicon-tag"></span> 상품추가 페이지  </h1></div>



                <div class="row"><!-- row Begin -->

                    <div class="col-lg-12"><!-- col-lg-12 Begin -->

                        <div class="panel panel-default"><!-- panel panel-default Begin -->


                            <div class="panel-body"><!-- panel-body Begin -->

                                <form method="post" class="form-horizontal" enctype="multipart/form-data"><!-- form-horizontal Begin -->

                                    <div class="form-group"><!-- form-group Begin -->

                                        <label class="col-md-3 control-label"> 상품명 </label>

                                        <div class="col-md-6"><!-- col-md-6 Begin -->

                                            <input name="product_title" type="text" class="form-control" required>

                                        </div><!-- col-md-6 Finish -->

                                    </div><!-- form-group Finish -->





                                    <div class="form-group">

                                        <label class="col-md-3 control-label"> 카테고리 </label>

                                        <div class="col-md-6"><!-- col-md-6 Begin -->

                                            <select name="product_cat" class="form-control">
                                                <option> 카테고리를 선택해주세요 </option>

                                                <option value="1"> 카테고리1 </option>
                                                <option value="2"> 카테고리2 </option>
                                                <option value="3"> 카테고리3 </option>


                                            </select><!-- form-control Finish -->

                                        </div><!-- col-md-6 Finish -->

                                    </div><!-- form-group Finish -->










                                    <div class="form-group"><!-- form-group Begin -->

                                        <label class="col-md-3 control-label"> 썸네일 이미지</label>

                                        <div class="col-md-6"><!-- col-md-6 Begin -->

                                            <input name="product_img1" type="file" class="form-control" required>

                                        </div><!-- col-md-6 Finish -->

                                    </div><!-- form-group Finish -->





                                    <div class="form-group">
                                        <label class="col-md-3 control-label"> 상세 이미지 </label>
                                        <div class="col-md-6">
                                            <input name="product_img2" type="file" class="form-control" required>
                                        </div>
                                    </div>




                                    <div class="form-group"><!-- form-group Begin -->

                                        <label class="col-md-3 control-label"> 가격 </label>

                                        <div class="col-md-6"><!-- col-md-6 Begin -->

                                            <input name="product_price" type="text" class="form-control" required>

                                        </div><!-- col-md-6 Finish -->

                                    </div><!-- form-group Finish -->






<!--                                    <div class="form-group">-->
<!--                                        <label class="col-md-3 control-label"> 키워드 </label>-->
<!--                                        <div class="col-md-6">-->
<!--                                            <input name="product_keywords" type="text" class="form-control" required>-->
<!--                                        </div>-->
<!--                                    </div>-->





                                    <div class="form-group"><!-- form-group Begin -->

                                        <label class="col-md-3 control-label"> 상세설명 </label>

                                        <div class="col-md-6"><!-- col-md-6 Begin -->

                                            <textarea name="product_desc" cols="19" rows="6" class="form-control"></textarea>

                                        </div><!-- col-md-6 Finish -->

                                    </div><!-- form-group Finish -->

                                    <div class="form-group"><!-- form-group Begin -->

                                        <label class="col-md-3 control-label"></label>

                                        <div class="col-md-6"><!-- col-md-6 Begin -->

                                            <input name="submit" value="상품등록" type="submit" style="background-color: #394C59; color: #FFFFFF" class="btn btn-primary form-control">

                                        </div><!-- col-md-6 Finish -->

                                    </div><!-- form-group Finish -->

                                </form><!-- form-horizontal Finish -->

                            </div><!-- panel-body Finish -->

                        </div><!-- canel panel-default Finish -->

                    </div><!-- col-lg-12 Finish -->

                </div><!-- row Finish -->

                <script src="js/jquery-331.min.js"></script>
                <script src="js/bootstrap-337.min.js"></script>
                <script src="js/tinymce/tinymce.min.js"></script>
                <script>tinymce.init({ selector:'textarea'});</script>
    </body>
    </html>


<?php

if(isset($_POST['submit'])){

    $product_title = $_POST['product_title'];
    $product_cat = $_POST['product_cat'];
    $product_price = $_POST['product_price'];
    $product_desc = $_POST['product_desc'];

    $product_img1 = $_FILES['product_img1']['name'];
    $product_img2 = $_FILES['product_img2']['name'];

    $temp_name1 = $_FILES['product_img1']['tmp_name'];
    $temp_name2 = $_FILES['product_img2']['tmp_name'];

    move_uploaded_file($temp_name1,"product_images/$product_img1");
    move_uploaded_file($temp_name2,"product_images/$product_img2");

    $insert_product = "insert into products (p_cat_id,date,product_title,product_img1,product_img2,product_price,product_desc) values ('$product_cat',NOW(),'$product_title','$product_img1','$product_img2','$product_price','$product_desc')";

    $run_product = mysqli_query($con,$insert_product);

    if($run_product){

        echo "<script>alert('상품이 성공적으로 등록되었습니다.')</script>";
        echo "<script>window.open('insert_product.php','_self')</script>";

    }

    header("location: index.php");


}

?>
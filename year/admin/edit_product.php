<?php




    if(isset($_GET['edit_product'])){
        
        $edit_id = $_GET['edit_product'];
        
        $get_p = "select * from products where product_id='$edit_id'";
        
        $run_edit = mysqli_query($con,$get_p);
        
        $row_edit = mysqli_fetch_array($run_edit);
        
        $p_id = $row_edit['product_id'];
        
        $p_title = $row_edit['product_title'];
        
        $p_cat = $row_edit['p_cat_id'];

        $p_image1 = $row_edit['product_img1'];
        
        $p_image2 = $row_edit['product_img2'];

        $p_price = $row_edit['product_price'];

        $p_desc = $row_edit['product_desc'];
        
    }
        
        $get_p_cat = "select * from product_categories where p_cat_id='$p_cat'";
        
        $run_p_cat = mysqli_query($con,$get_p_cat);
        
        $row_p_cat = mysqli_fetch_array($run_p_cat);
        
        $p_cat_title = $row_p_cat['p_cat_title'];

        $run_cat = mysqli_query($con,$get_cat);
        
        $row_cat = mysqli_fetch_array($run_cat);
        
        $cat_title = $row_cat['cat_title'];

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
                <h1><span class="glyphicon glyphicon-tag"></span> 상품수정 페이지  </h1></div>



            <div class="row"><!-- row Begin -->

                <div class="col-lg-12"><!-- col-lg-12 Begin -->

                    <div class="panel panel-default"><!-- panel panel-default Begin -->


                        <div class="panel-body"><!-- panel-body Begin -->

                            <form method="post" class="form-horizontal" enctype="multipart/form-data"><!-- form-horizontal Begin -->

                                <div class="form-group"><!-- form-group Begin -->

                                    <label class="col-md-3 control-label"> 상품명 </label>



                      <div class="col-md-6"><!-- col-md-6 Begin -->
                          
                          <input name="product_title" type="text" class="form-control" required value="<?php echo $p_title; ?>">
                          
                      </div><!-- col-md-6 Finish -->
                       
                   </div><!-- form-group Finish -->



                                <div class="form-group">

                                    <label class="col-md-3 control-label"> 카테고리 </label>

                                    <div class="col-md-6"><!-- col-md-6 Begin -->

                                        <select name="product_cat" class="form-control">
                                            <option> 카테고리를 선택해주세요 </option>

                                            <option value="1"> 카테고리 1 </option>
                                            <option value="2"> 카테고리 2 </option>
                                            <option value="3"> 카테고리 3 </option>


                                        </select><!-- form-control Finish -->

                                    </div><!-- col-md-6 Finish -->

                                </div><!-- form-group Finish -->













                                <div class="form-group"><!-- form-group Begin -->

                                    <label class="col-md-3 control-label"> 상품 썸네일 </label>

                                    <div class="col-md-6"><!-- col-md-6 Begin -->

                                        <input name="product_img1" type="file" class="form-control" required>

                                        <br>

                                        <img width="70" height="70" src="product_images/<?php echo $p_image1; ?>" alt="<?php echo $p_image1; ?>">

                                    </div><!-- col-md-6 Finish -->

                                </div><!-- form-group Finish -->









                                <div class="form-group">
                                    <label class="col-md-3 control-label"> 상세 이미지 </label>
                                    <div class="col-md-6">
                                        <input name="product_img2" type="file" class="form-control" required>

                                        <br>

                                        <img width="70" height="70" src="product_images/<?php echo $p_image2; ?>" alt="<?php echo $p_image2; ?>">
                                    </div>
                                </div>







                                <div class="form-group"><!-- form-group Begin -->

                                    <label class="col-md-3 control-label"> 가격 </label>

                                    <div class="col-md-6"><!-- col-md-6 Begin -->

                                        <input name="product_price" type="text" class="form-control" required value="<?php echo $p_price; ?>">

                                    </div><!-- col-md-6 Finish -->

                                </div><!-- form-group Finish -->












                                <div class="form-group"><!-- form-group Begin -->

                                    <label class="col-md-3 control-label"> 상세설명 </label>

                                    <div class="col-md-6"><!-- col-md-6 Begin -->

                                        <textarea name="product_desc" cols="19" rows="6" class="form-control">

                                            <?php echo $p_desc; ?>

                          </textarea>

                                    </div><!-- col-md-6 Finish -->

                                </div><!-- form-group Finish -->




                   

                   
                   <div class="form-group"><!-- form-group Begin -->
                       
                      <label class="col-md-3 control-label"></label> 
                      
                      <div class="col-md-6"><!-- col-md-6 Begin -->
                          
                          <input name="update" value="상품 수정" type="submit" class="btn btn-primary form-control" style="background-color: #394C59">
                          
                      </div><!-- col-md-6 Finish -->
                       
                   </div><!-- form-group Finish -->

               </form><!-- form-horizontal Finish -->
               
           </div><!-- panel-body Finish -->
            
        </div><!-- canel panel-default Finish -->
        
    </div><!-- col-lg-12 Finish -->
    
</div><!-- row Finish -->
   
    <script src="style/tinymce/tinymce.min.js"></script>
    <script>tinymce.init({ selector:'textarea'});</script>
</body>
</html>



<?php 

if(isset($_POST['update'])){
    
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
    
    $update_product = "update products set p_cat_id='$product_cat',date=NOW(),product_title='$product_title',product_img1='$product_img1',product_img2='$product_img2',product_desc='$product_desc',product_price='$product_price' where product_id='$p_id'";
    
    $run_product = mysqli_query($con,$update_product);
    
    if($run_product){
        
       echo "<script>alert('상품이 수정되었습니다.')</script>";
        
       echo "<script>window.open('index.php?view_products','_self')</script>"; 
        
    }
    
}

?>


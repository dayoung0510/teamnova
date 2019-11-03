<?php

require_once ('htdocs/db.php');
include('head.php');
include ('admin/connection.php');
include ('functions.php');
include('chat/database_connection.php');


$active='Shop';




if(isset($_GET['delete_product'])){
    include("delete_product.php");
}








?>



<!DOCTYPE html>


<html>
<head>



    <title> DY'shop </title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link rel="stylesheet" href="styles/bootstrap337.min.css">


    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>








    <script language="javascript">
        // 쿠키가 있나 찾습니다
        function getCookie( name ){
            var nameOfCookie = name + "=";
            var x = 0;
            while ( x <= document.cookie.length )
            {
                var y = (x+nameOfCookie.length);
                if ( document.cookie.substring( x, y ) == nameOfCookie ) {
                    if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
                        endOfCookie = document.cookie.length;
                    return unescape( document.cookie.substring( y, endOfCookie ) );
                }
                x = document.cookie.indexOf( " ", x ) + 1;
                if ( x == 0 )
                    break;
            }
            return "";
        }
        if ( getCookie( "g" ) != "done" ) {
            window.open('/year/noti_popup.php','_blank','width=380,height=380,top=50,left=150');
        }
    </script>



</head>









<body style="color:#000000;">




<div class="col-md-1">
<div id="user_details"></div>
<div id="user_model_details"></div>
</div>



<div class="container" id="slider"><!-- container Begin -->




<?php
$active='Shop';
?>





    <br><br><br><br>
    <div id="col-md-12"><!-- #content Begin -->

        <div class="col-md-12"><!-- col-md-9 Begin -->



            <div class="row" ><!-- row Begin -->

                <?php

                if(!isset($_GET['p_cat'])){
                if(!isset($_GET['cat'])){

                $per_page=3;

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
                                
                                
            <div class='col-md-4'>

                <div class='product same-height'>
                                        
                                            <a href='details.php?pro_id=$pro_id'>
                                            
                                                <img class='img-responsive' src='admin/product_images/$pro_img1' style='width: 250px; height: 250px;'>
                                            
                                            </a>
                                            
                                            <div class='text'>
                                            
                                                <h3>                                                
                                                    <a href='details.php?pro_id=$pro_id'> $pro_title </a>                                                
                                                </h3>
                                            
                                                <p class='price'>
                                                   "?> <?php echo number_format($pro_price) ?>     <?php echo "원
                                                </p>


                                            
                                            </div>
                                        
                                        </div>
                                    
                                    </div>                                
                                ";
                }

                ?>

            </div><!-- row Finish -->

            <center>
                <ul class="pagination"><!-- pagination Begin -->
                    <?php

                    $query = "select * from products";
                    $result = mysqli_query($con,$query);
                    $total_records = mysqli_num_rows($result);
                    $total_pages = ceil($total_records / $per_page);

                    echo "                        
                            <li>                            
                                <a href='index.php?page=1' style='color: black'> ".'처음'." </a>                            
                            </li>                        
                        ";

                    for($i=1; $i<=$total_pages; $i++){

                        echo "                        
                            <li>                            
                                <a href='index.php?page=".$i."'> ".$i." </a>                            
                            </li>                        
                        ";
                    };

                    echo "                        
                            <li>                            
                                <a href='index.php?page=$total_pages' style='color: black'> ".'끝'." </a>                            
                            </li>                        
                        ";
                    }
                    }

                    ?>

                </ul><!-- pagination Finish -->
            </center>

            <?php

            getpcatpro();
            getcatpro();

            ?>

        </div><!-- col-md-9 Finish -->

    </div><!-- container Finish -->
</div><!-- #content Finish -->



</div><!-- container Finish -->
</div><!-- #content Finish -->











<script>
    $(document).ready(function(){

        fetch_user();

        setInterval(function(){
            update_last_activity();
            fetch_user();
            update_chat_history_data();
        }, 500);

        function fetch_user()
        {
            $.ajax({
                url:"chat/fetch_user.php",
                method:"POST",
                success:function(data){
                    $('#user_details').html(data);
                }
            })
        }

        function update_last_activity()
        {
            $.ajax({
                url:"chat/update_last_activity.php",
                success:function()
                {

                }
            })
        }

        function make_chat_dialog_box(to_user_id, to_user_name)
        {
            var modal_content = '<div id="user_dialog_'+to_user_id+'" class="user_dialog" title=" 관리자 채팅 문의 :  '+to_user_name+'">';
            modal_content += '<div style="height:400px; border:1px solid #ccc; overflow-y: scroll; margin-bottom:24px; padding:16px;" class="chat_history" data-touserid="'+to_user_id+'" id="chat_history_'+to_user_id+'">';
            modal_content += fetch_user_chat_history(to_user_id);
            modal_content += '</div>';
            modal_content += '<div class="form-group">';
            modal_content += '<textarea name="chat_message_'+to_user_id+'" id="chat_message_'+to_user_id+'" class="form-control"></textarea>';
            modal_content += '</div><div class="form-group" align="right">';
            modal_content+= '<button type="button" name="send_chat" id="'+to_user_id+'" class="btn btn-info send_chat">Send</button></div></div>';
            $('#user_model_details').html(modal_content);
        }

        $(document).on('click', '.start_chat', function(){
            var to_user_id = $(this).data('touserid');
            var to_user_name = $(this).data('tousername');
            make_chat_dialog_box(to_user_id, to_user_name);
            $("#user_dialog_"+to_user_id).dialog({
                autoOpen:false,
                width:400
            });
            $('#user_dialog_'+to_user_id).dialog('open');
        });

        $(document).on('click', '.send_chat', function(){
            var to_user_id = $(this).attr('id');
            var chat_message = $('#chat_message_'+to_user_id).val();
            $.ajax({
                url:"chat/insert_chat.php",
                method:"POST",
                data:{to_user_id:to_user_id, chat_message:chat_message},
                success:function(data)
                {
                    $('#chat_message_'+to_user_id).val('');
                    $('#chat_history_'+to_user_id).html(data);
                }
            })
        });

        function fetch_user_chat_history(to_user_id)
        {
            $.ajax({
                url:"chat/fetch_user_chat_history.php",
                method:"POST",
                data:{to_user_id:to_user_id},
                success:function(data){
                    $('#chat_history_'+to_user_id).html(data);
                }
            })
        }

        function update_chat_history_data()
        {
            $('.chat_history').each(function(){
                var to_user_id = $(this).data('touserid');
                fetch_user_chat_history(to_user_id);
            });
        }

        $(document).on('click', '.ui-button-icon', function(){
            $('.user_dialog').dialog('destroy').remove();
        });

    });
</script>










<!-- Scripts -->




<script src="../folder/js/jquery-331.min.js"></script>
<script src="../folder/js/bootstrap-337.min.js"></script>




</body>
</html>


<?php

include ("../head.php");
include ("../htdocs/db.php");
include ("../admin/connection.php");


$userid = $_SESSION['userid'];


$delete_cart = "delete from cart where uid='$userid'";
$run_delete = mysqli_query($con,$delete_cart);


?>

<div class="container">
    <br><br><br><br><br>
    <h3>결제가 완료되었습니다.</h3>

    <br><br>
    <a href="/year/index.php"> <h4>홈으로</h4> </a>
</div>




<!-- 결제상태 false에서 true로 바꾸는 부분-->

<?php

$update_status = "update user_order set status='결제완료' where uid='$userid'";
$run_status = mysqli_query($con,$update_status);





    ?>
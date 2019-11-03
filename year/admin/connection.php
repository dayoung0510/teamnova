<?php
$con=mysqli_connect("localhost","root","admin","userdb");
/*check connection*/
if(mysqli_connect_errno())
{
echo"Connection Fail". mysqli_connect_error();
}
?>

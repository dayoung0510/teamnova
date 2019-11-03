 <?php
  session_start();
  
  $db = new mysqli("localhost","root","admin","userdb");
  $db->set_charset("utf8");

  function mq($sql){
    global $db;
    return $db->query($sql);
  }






//세션 타임아웃
// $timeout = 10 ; // Set timeout minutes
// $logout_redirect_url = "/year/index.php" ; // Set logout URL
// $timeout = $timeout * 60 ; // Converts minutes to seconds
// if (isset( $_SESSION [ 'start_time' ])) {
//   $elapsed_time = time () - $_SESSION [ 'start_time' ];
//   if ( $elapsed_time >= $timeout ) {
//     session_destroy ();
//     header ( "Location: $logout_redirect_url" );
//
//   }
// }
// $_SESSION [ 'start_time' ] = time ();










 ?>

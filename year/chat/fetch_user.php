<?php

include('database_connection.php');

session_start();

$query = "
SELECT * FROM member 
WHERE user_id != '".$_SESSION['user_id']."' 
";

$statement = $connect->prepare($query);
$statement->execute();
$result = $statement->fetchAll();



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




$output = '
<table class="table table-bordered table-striped">

';

if($result)
{
    $status = '';
    $current_timestamp = strtotime(date("Y-m-d H:i:s") . '- 10 second');
    $current_timestamp = date('Y-m-d H:i:s', $current_timestamp);
    $user_last_activity = fetch_user_last_activity($row['user_id'], $connect);

    if(isset($_SESSION['userid'])) {
    if($_SESSION['userid']!='admin')
    {
        $output .='
        <tr>
    <button type="button" class="btn btn-danger btn-lg start_chat" data-touserid="'.'2'.'" data-tousername="'.admin.'">
    <span class="fas fa-comment"> <span style="color:white"> &nbsp; 채팅 문의 </span></span> '.count_unseen_message($row['user_id'], $_SESSION['user_id'], $connect).' </button></tr>
        
        ';
    }
    }

    else
    {
        $output .= '';
    }



}


echo $output;




?>




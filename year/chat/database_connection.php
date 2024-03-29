<?php

//database_connection.php

$connect = new PDO("mysql:host=localhost;dbname=userdb", "root", "admin");

date_default_timezone_set('Asia/Kolkata');




function fetch_user_last_activity($user_id, $connect)
{
 $query = "
 SELECT * FROM login_details 
 WHERE user_id = '$user_id' 
 ORDER BY last_activity DESC 
 LIMIT 1
 ";
 $statement = $connect->prepare($query);
 $statement->execute();
 $result = $statement->fetchAll();
 foreach($result as $row)
 {
  return $row['last_activity'];
 }
}



function fetch_user_chat_history($from_user_id, $to_user_id, $connect)
{
    $query = "
 SELECT * FROM chat_message 
 WHERE (from_user_id = '".$from_user_id."' 
 AND to_user_id = '".$to_user_id."') 
 OR (from_user_id = '".$to_user_id."' 
 AND to_user_id = '".$from_user_id."') 
 ORDER BY timestamp ASC
 ";
    $statement = $connect->prepare($query);
    $statement->execute();
    $result = $statement->fetchAll();
    $output = '<ul class="list-unstyled">';
    foreach($result as $row)
    {
        $user_name = '';
        if($row["from_user_id"] == $from_user_id)
        {
            $user_name = '<b class="text-success">' .$_SESSION['userid']. ' (나) </b>';
        }
        else
        {
            $user_name = '<b class="text-danger">'.get_user_name($row['from_user_id'], $connect).'</b>';
        }
        $output .= '
  <li style="border-bottom:1px dotted #ccc">
   <p>'.$user_name.' - '.$row["chat_message"].'
    <div align="right">
     - <small><em>'.$row['timestamp'].'</em></small>
    </div>
   </p>
  </li>
  ';
    }
    $output .= '</ul>';


    $query = "
 UPDATE chat_message 
 SET status = '0' 
 WHERE from_user_id = '".$to_user_id."' 
 AND to_user_id = '".$from_user_id."' 
 AND status = '1'
 ";
    $statement = $connect->prepare($query);
    $statement->execute();



    return $output;
}

function get_user_name($user_id, $connect)
{
    $query = "SELECT id FROM member WHERE user_id = '$user_id'";
    $statement = $connect->prepare($query);
    $statement->execute();
    $result = $statement->fetchAll();
    foreach($result as $row)
    {
        return $row['id'];
    }
}


function count_unseen_message($from_user_id, $to_user_id, $connect)
{
    $query = "
 SELECT * FROM chat_message 
 WHERE from_user_id = '2' 
 AND to_user_id = '$to_user_id' 
 AND status = '1'
 ";
    $statement = $connect->prepare($query);
    $statement->execute();
    $count = $statement->rowCount();
    $output = '';
    if($count > 0)
    {
        $output = '<span class="label label-success">'.$count.'</span>';
    }
    return $output;
}



?>

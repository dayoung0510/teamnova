<?php   include "db.php";



include('../head.php');




function move($vDestination) {
    echo("<html>\n");
    echo("<head>\n");
    echo("<title>System Message</title>\n");
    echo("<meta http-equiv=\"Content-Type\" content=\"text/html;
cht=iso-8859-1\">\n");

    echo("<script language=\"JavaScript\" type=\"text/JavaScript\">\n");
    echo("window.location = ('$vDestination');\n");
    echo("</script>\n");
    echo("</head>\n");
    echo("<body>\n");
    echo("</body>\n");
    echo("</html>\n");
    exit;
}







if(isset($_SESSION['userid'])   ) {
    move('/year/htdocs/mypage.php');
}

else

 ;






?>


<!DOCTYPE html>
<head>
	<meta charset="utf-8" />
	<title>회원가입 및 로그인</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
</head>
<body>

	<div id="login_box">
		<h1 style="color: #394C59">로그인</h1>
			<form method="post" action="member/login_ok.php">
				<table align="center" border="0" cellspacing="0" width="300">




        			<tr>
            			<td width="130" colspan="1">
                		<input type="text" name="userid" class="inph" value="<?php if(isset($_COOKIE["userid"])) { echo $_COOKIE["userid"]; } ?>" style="color: #424242">
            		</td>


                        <td rowspan="2" align="center" width="100" >
                            <button type="submit" id="btn" style="background-color: #394C59; color: #FFFFFF">로그인</button>
                        </td>


        	    	</tr>




        		    <tr>
            		    <td width="130" colspan="1">
               	    	 <input type="password" name="userpw" class="inph" value="<?php if(isset($_COOKIE["userpw"])){ echo $_COOKIE["userpw"]; } ?>" style="color: #424242">
            	        </td>
                    </tr>


                    <tr>
                    <td>
                        <input type="checkbox" name="remember" id="remember" />
                            <label for="remember-me"> 아이디저장 </label>
                    </td>
                    </tr>



                    <tr>
           		<td colspan="3" align="center" class="mem">
              	<a href="member/member.php" style="color: #394C59"> <br><br><br> 회원가입 하시겠습니까?</a>
                </td> </tr>

    </table>
  </form>
</div>







</body>
</html>

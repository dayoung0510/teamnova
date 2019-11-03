<html>
<head>
    <title>회원가입</title>
    <script language="javascript" src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
    <script>

        function checkz() {

            //아이디 공백 확인
            if($("#tbID").val() == ""){
                alert("아이디 입력바람");
                $("#tbID").focus();
                return false;
            }




            //비밀번호 똑같은지
            if($("#tbPwd").val() != ($("#cpass").val())){
                alert("비밀번호가 틀렸네용.");
                $("#tbPwd").val("");
                $("#cpass").val("");
                $("#tbPwd").focus();
                return false;
            }



            return true;
        }



    </script>
</head>


<body>

<form onsubmit="return checkz()" method="post" action="111" enctype="text/plain" >
    <h2 align="center">회원가입</h2>

    <table align="center" border="3" cellspacing="0"  >
        <tr>
            <td colspan="5" height="30" align="center" bgcolor=#000000" span style="color:white;">회원기본정보</td>

        </tr>
        <tr>
            <td align="left">아이디:</td>
            <td colspan="4"><input type="text" name="id" maxlength="12" id="tbID" > 4~12자리의 영문 대소문자와 숫자로만 입력</td>
        </tr>

        <tr>
            <td >비밀번호:</td>
            <td colspan="4"><input type="password" maxlength="12" id="tbPwd" > 4~12자리의 영문 대소문자와 숫자로만 입력</td>
        </tr>
        <tr>
            <td >비밀번호확인:</td>
            <td colspan="5"><input type="password" id="cpass" maxlength="12"></td>

        </tr>




    </table>

    <p align="center">
        <input type="submit" value="회원가입">
        <input type="reset" value="다시입력">
    </p>

</form>
</body>


</html>
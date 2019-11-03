<html>

<table>

<body topmargin=10px leftmargin=10px rightmargin="10px" style="background-color: #ffffff; color: #424242">

<img src="admin/noti.jpg" width="350px" height="350px">




    <td style='padding-left:10px' bgcolor="#E6E6E6" height='24px'>
        <INPUT onclick=javascript:history.onclick=closeWin() type=checkbox CHECKED value="0" name=g>
        <FONT face=돋움 color=black size=2> <strong> 오늘 그만보기</strong></FONT>
    </td>


    <td style='padding-right:10px' bgcolor="#E6E6E6">
        <A href="javascript:history.onclick=closeWin()"> &nbsp; 닫기 </A>
    </td>


</table>




<script language="JavaScript">
    // 쿠키를 만듭니다. 아래 closeWin() 함수에서 호출됩니다
    function setCookie( name, value, expiredays )
    {
        var todayDate = new Date();
        todayDate.setDate( todayDate.getDate() + expiredays );
        document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
    }

    // 체크후 닫기버튼을 눌렀을때 쿠키를 만들고 창을 닫습니다
    function closeWin()
    {
        if ( document.all.g.checked )
            setCookie( "g", "done" , 2); // 오른쪽 숫자는 쿠키를 유지할 기간을 설정합니다
        self.close();
    }
    function openhref(pPage) {
        opener.location.href = pPage
    }
</script>
</body>
</html>
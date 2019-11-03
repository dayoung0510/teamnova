<?php
include "../db.php";
include "../../head.php";

?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>회원가입</title>


    <!-- 아이디 중복체크 -->
    <script>
        function checkid(){
            var userid = document.getElementById("uid").value;
            if(userid)
            {
                url = "check.php?userid="+userid;
                window.open(url,"chkid","width=300,height=100");
            }else{
                alert("아이디를 입력하세요");
            }
        }

    </script>





    <!-- 아이디, 비밀번호 공란 확인 -->
    <script>
        function checkz() {
            //아이디 공백 확인
            if($("#uid").val() == ""){
                alert("아이디를 입력해주세요.");
                $("#uid").focus();
                return false;
            }

            if($("#userpw").val() == ""){
                alert("비밀번호를 입력해주세요.");
                $("#uid").focus();
                return false;
            }

            if($("#userpwCheck").val() == ""){
                alert("비밀번호 확인을 입력해주세요.");
                $("#uid").focus();
                return false;
            }

            //비밀번호 똑같은지
            if($("#userpw").val() != ($("#userpwCheck").val())){
                alert("비밀번호를 다시 확인해주세요.");
                $("#userpw").val("");
                $("#userpwCheck").val("");
                $("#userpw").focus();
                return false;
            }

            //이름 공란인지
            if($("#name").val() == ""){
                alert("성함을 입력해주세요.");
                $("#name").focus();
                return false;
            }


            return true;
        }

    </script>







</head>
<body>
<form onsubmit="return checkz()" method="post" action="member_ok.php" style="color: #394C59">
    <h1>회원가입</h1>
    <fieldset>
        <legend> (*)필수정보 </legend>
        <table style="color: #394C59">
            <tr>
                <td>아이디*</td>
                <td><input type="text" size="35" name="userid" id="uid" placeholder="아이디"></td>
                <td><input type="button" value="중복검사" onclick="checkid();" /></td>
                <td><input type="hidden" value="0" name="chs" /></td>

            </tr>
            <tr>
                <td>비밀번호*</td>
                <td><input type="password" size="35" name="userpw" id="userpw" placeholder="비밀번호"></td>
            </tr>


            <tr>
                <td>비밀번호 확인* &nbsp; </td>
                <td><input type="password" size="35" name="userpwCheck" id="userpwCheck" placeholder="비밀번호확인"></td>
            </tr>


            <tr>
                <td>이름*</td>
                <td><input type="text" size="35" name="name" id="name" placeholder="이름"></td>
            </tr>

            <tr>
                <td>성별</td>
                <td>남<input type="radio" name="sex" value="남"> 여<input type="radio" name="sex" value="여"></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><input type="text" name="email">@<select name="emadress"><option value="naver.com">naver.com</option><option value="nate.com">nate.com</option>
                        <option value="hanmail.com">hanmail.com</option></select></td>
            </tr>






            <tr>
                <td>주소</td>
                <td>
                    <input type="text" id="postal" name="postal" placeholder="우편번호">
                    <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                </td>
            </tr>

            <tr>
                <td></td>
                <td>
                    <input type="text" id="address1" name="address1" placeholder="주소"><br>
                </td>
            </tr>

            <tr>
                <td></td>
                <td>
                    <input type="text" id="address2" name="address2" placeholder="상세주소">
                    <input type="text" id="address3" name="address3" placeholder="참고항목">
                </td>
            </tr>

            <tr>
                <td>연락처</td>
                <td><input type="text" size="35" name="phone" placeholder="연락처"></td>
            </tr>










            <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
            <script>
                function sample6_execDaumPostcode() {
                    new daum.Postcode({
                        oncomplete: function(data) {
                            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                            var addr = ''; // 주소 변수
                            var extraAddr = ''; // 참고항목 변수


                            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                addr = data.roadAddress;
                            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                addr = data.jibunAddress;
                            }

                            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                            if(data.userSelectedType === 'R'){
                                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                                    extraAddr += data.bname;
                                }
                                // 건물명이 있고, 공동주택일 경우 추가한다.
                                if(data.buildingName !== '' && data.apartment === 'Y'){
                                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                                }
                                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                                if(extraAddr !== ''){
                                    extraAddr = ' (' + extraAddr + ')';
                                }
                                // 조합된 참고항목을 해당 필드에 넣는다.
                                document.getElementById("address3").value = extraAddr;

                            } else {
                                document.getElementById("address2").value = '';
                            }

                            // 우편번호와 주소 정보를 해당 필드에 넣는다.
                            document.getElementById('postal').value = data.zonecode;
                            document.getElementById("address1").value = addr;
                            // 커서를 상세주소 필드로 이동한다.
                            document.getElementById("address2").focus();
                        }
                    }).open();
                }


            </script>






            </table>

            <br><br>





            <input type="submit" value="가입하기" />
        <input type="reset" value="다시쓰기" />

                </fieldset>
                </form>
                </body>
                </html>
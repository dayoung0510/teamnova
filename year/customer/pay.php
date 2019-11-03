<?php

include ("../htdocs/db.php");
include ("../admin/connection.php");


$userid = $_SESSION['userid'];


session_start();
$total = $_SESSION['total_won'];



?>

<!DOCTYPE html>
<html>
<head>
</head>
<body>



<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script>
    var IMP = window.IMP;
    IMP.init('imp06598537');

    IMP.request_pay({
        pg : 'kakaopay', // version 1.1.0부터 지원.
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : '<?php echo $userid ?>',
        amount : '<?php echo $total ?>',
        buyer_email : 'iamport@siot.do',
        buyer_name : '구매자이름',
        buyer_tel : '010-1234-5678',
        buyer_addr : '서울특별시 강남구 삼성동',
        buyer_postcode : '123-456',
        m_redirect_url : 'https://www.yourdomain.com/payments/complete'
    }, function(rsp) {
        if ( rsp.success ) {
            var msg = '결제가 완료되었습니다.';
            // msg += '고유ID : ' + rsp.imp_uid;
            // msg += '상점 거래ID : ' + rsp.merchant_uid;
            // msg += '결제 금액 : ' + rsp.paid_amount;
            // msg += '카드 승인번호 : ' + rsp.apply_num;

            window.open('complete.php','_self')




        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            window.open('cart.php','_self')
        }
        alert(msg);
    });
</script>
</body>
</html>


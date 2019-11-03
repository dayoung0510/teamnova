<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> DY's store </title>

    <!-- 스타일 시트들과 연결 -->
    <link rel="stylesheet" href="/year/styles/bootstrap.min.css">
    <link rel="stylesheet" href="/year/styles/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

</head>


<body>


<!--최상단 청록색 부분 시작 -->
<div id="top">
    <div class="navbar1">
        <ul class="nav navbar-nav">


            <a class="navbar-brand"> <dyFont> &nbsp;&nbsp;&nbsp; 환영합니다. &nbsp;




                    <?php

                if(isset($_SESSION['userid'])){
                echo "{$_SESSION['userid']} 님";
                ?>
                </dyFont>

                        <a href="/year/htdocs/member/logout.php"><input style="color: black" type="button" value="로그아웃" /></a>

                        <?php
                    }
                 ?>





                <?php
                if($_SESSION['userid']=='admin')
                {
                ?>

                <?php
                }
                else { ;
                } ?>



            </a>

        </ul>








        <ul class="menu">
            <ul class="menu">
<!--                <li><a href="/year/customer_register.php"><dy>채팅문의 </dy> </a></li>-->

                <li><a href="/year/guest/index.php"> <dy>Q&A</dy> </a></li>


                <!--어드민일 땐 장바구니 안나오게 -->
                <?php
                if($_SESSION['userid']!='admin')
                {
                    ?>
                    <li><a href="/year/customer/cart.php" > <dy> 장바구니 </dy></a>  </li>
                    <?php
                }
                else {
                    ?>
                <li><a href="/year/admin/index.php" style="color: #f0ad4e"><dy><strong> 관리자페이지 </strong></dy></a></li>
                <?php
                }
                ?>


                <li><a href="/year/htdocs/index.php"> <dy><span class="fas fa-user-circle fa-2x"></span></dy></a></li>





            </ul>
        </ul>
    </div>
</div>
<!--최상단 청록색 부분 끝 -->



<!--네비게이션바 시작 -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/year/index.php">Home</a></li>

            <li><a href="/year/index.php?p_cat=1"> 카테고리1 </a></li>
            <li><a href="/year/index.php?p_cat=2"> 카테고리2 </a></li>
            <li><a href="/year/index.php?p_cat=3"> 카테고리3 </a></li>
        </ul>
    </div>
</nav><!--네비게이션바 끝-->

<!-- 제이쿼리, 부트스트랩 시트들과 연결 -->
<script src="/year/styles/js/jquery-3.3.1.min.js"></script>
<script src="/year/styles/js/bootstrap.min.js"></script>
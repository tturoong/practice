<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin main page</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" href="./resource/css/bootstrap.min.css">
<link rel="stylesheet" href="./resource/css/custom.css">
<link rel="stylesheet" href="./resource/css/index.css">
<style type="text/css">


.dashbox { border: 1px solid skyblue; width: 230px; margin: 15px;}

.summary { border: 1px solid skyblue; width: 750px; text-align: right; margin: 10px 15px 40px 15px;}

th, td { padding: 10px; }


.dashbox td:nth-child(2n-1) { text-align: left; }
.dashbox td:nth-child(2n) { text-align: right; }

.summary tr:last-child {border-top: 1px dotted gray; border-collapse: collapse;}
.summary td:nth-child(6n+1) { text-align: center; }

.dash {
	display: flex;
	flex-wrap: wrap;
	overflow: auto;
}

.dash2 {
	flex-direction: column;
}


</style>


<script src="./resource	/js/jquery.min.js"></script>
<script src="./resource/js/popper.js"></script>
<script src="./resource/js/bootstrap.min.js"></script>
<script src="./resource/js/custom.js"></script>

<script type="text/javascript">
function modal() {
	alert("공지사항입니다.")
}

</script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.4.2/css/all.css">
</head>
<body id="body-pd">
    <div class="l-navbar" id="navbar">
        <nav class="nav">
            <div>	
                <div class="nav__brand">
                        <img src="./resource/images/icon/more.png" name="menu-outline" class="nav__toggle" id="nav-toggle" style="width: 28px;" onclick="langHide()">
                </div>
                <div class="nav__list">
                    <a href="#" class="nav__link active">
                        <img src="./resource/images/icon/person.png" style="width: 28px; background-color: #cccccc; border-radius: 50%; padding: 5px;" name="menu1">
                        	<span class="nav_name">로그인 / 회원가입</span>
                         </a>
                    <a href="#" class="nav__link">
                        <img src="./resource/images/icon/home.png" style="width: 28px;" name="menu2">
                        <span class="nav_name" style="color: #1682b0;">홈</span>
                    </a>
                    <a href="#" class="nav__link" onmouseover="menu3.src='./resource/images/icon/messenger_hover.png'" onmouseout="menu3.src='./resource/images/icon/messenger.png'">
                        <img src="./resource/images/icon/messenger.png" style="width: 28px;" name="menu3">
                        <span class="nav_name">커뮤니티</span>
                    </a>
                    <a href="#" class="nav__link" onmouseover="menu4.src='./resource/images/icon/comment01.png'" onmouseout="menu4.src='./resource/images/icon/comment.png'">
                        <img src="./resource/images/icon/comment.png" style="width: 28px;" name="menu4">
                        <span class="nav_name">사용가이드</span>
                    </a>
                </div>
                
            </div>
        </nav>
    </div>
    
    <div id="wrap" style="padding: 0px 100px;">
    <div class="navbar" style="margin-top: 52px;">
        <form class="row">
        </form>
        <a class="navbar-brand" href="#" style="position: absolute; left: 45%;">
           	관리자 페이지
        </a>
        <div class="col-auto">
            <a class="btn" id="btn-modal" data-toggle="modal" href="#notificationModal"style="border: 0px; padding: 2px;" onclick="modal();">
            	<img src="./resource/images/icon/notification.png" width="28">
            </a>
            <a href="loginRegister.jsp"><img src="./resource/images/icon/person.png" width="52" style="background-color: #1682b0; border-radius: 40px; padding: 10px;"></a>
        </div>
    </div>
     
    <hr>
    <div id="recommend">
    <h2 style="margin: 40px;">DASHBOARD</h2>
    </div>
    
    <div class="dash">
    	<table class="dashbox"> <!-- 대략적인 정보만 보여주기 or 바로가기 메뉴 -->
    		<tr>
    			<th><i class="fa-solid fa-store"></i>&nbsp;상품관리</th>
    		</tr>
    		<tr>
    			<td>판매중</td>
    			<td>?개</td>
    		</tr>
    		<tr>
    			<td>일시품절</td>
    			<td>?개</td>
    		</tr>
    		<tr>
    			<td>삭제된상품</td>
    			<td>?개</td>
    		</tr>
    	</table>
    	
    	<table class="dashbox">
    		<tr>
    			<th><i class="fa-solid fa-truck-fast"></i>&nbsp;주문관리</th>
    		</tr>
    		<tr>
    			<td>결제확인</td>
    			<td>?건</td>
    		</tr>
    		<tr>
    			<td>배송준비중</td>
    			<td>?건</td>
    		</tr>
    		<tr>
    			<td>배송완료</td>
    			<td>?건</td>
    		</tr>
    	</table>
    	
    	<table class="dashbox">
    		<tr>
    			<th><i class="fa-solid fa-pen-to-square"></i>&nbsp;리뷰 및 문의</th>
    		</tr>
    		<tr>
    			<td>최근리뷰</td>
    			<td>?건</td>
    		</tr>
    		<tr>
    			<td>최근문의</td>
    			<td>?건</td>
    		</tr>
    		<tr>
    			<td>답변완료</td>
    			<td>?건</td>
    		</tr>
    	</table>
    	
    	<table class="dashbox">
    		<tr>
    			<th><i class="fa-solid fa-newspaper"></i>&nbsp;사이트현황</th>
    		</tr>
    		<tr>
    			<td>금일 가입자수</td>
    			<td>?명</td>
    		</tr>
    		<tr>
    			<td>금일 탈퇴자수</td>
    			<td>?명</td>
    		</tr>
    		<tr>
    			<td>일 주문건수</td>
    			<td>?건</td>
    		</tr>
    	</table>
    	
    	
    	
    
    
    
    	<div>
    	<p style="margin: 20px 0 0 15px;">일자별 요약</p>
    	<table class="summary">
    		<tr style="color:gray; text-align: center;">
    			<td>일자</td>
    			<td>주문수</td>
    			<td>매출액</td>
    			<td>방문자수</td>
    			<td>가입자수</td>
    			<td>문의</td>
    		</tr>
    		<tr style="background-color:skyblue;">
    			<td>2023-10-29</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    		</tr>
    		<tr>
    			<td>2023-10-29</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    		</tr>
    		<tr>
    			<td>2023-10-29</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    		</tr>
    		<tr>
    			<td>2023-10-29</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    		</tr>
    		<tr>
    			<td>2023-10-29</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    		</tr>
    		<tr>
    			<td>2023-10-29</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    		</tr>
    		<tr>
    			<td>2023-10-29</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    		</tr>
    		<tr>
    			<td>최근 7일간 합계</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    			<td>0</td>
    		</tr>
    	</table>
    	</div>
    	
    	
    	
    	<div class="dash2">
    	<table class="dashbox" style="margin: 15px 0 30px 15px">
    		<tr>
    			<th><i class="fa-solid fa-sitemap"></i>&nbsp;홈페이지관리</th>
    		</tr>
    		<tr>
    			<td>최근리뷰</td>
    			<td>?건</td>
    		</tr>
    		<tr>
    			<td>공지사항관리</td>
    		</tr>
    		<tr>
    			<td>이벤트관리</td>
    		</tr>
    	</table>
    	
    	
    	<table class="dashbox" style="text-align: center">
    		<tr>
    			<th>TODAY'S VISITOR</th>
    		</tr>
    		<tr>
    			<td><h3 style="text-align: center">n명</h3></td>
    		</tr>
    	</table>
    	</div>
    </div>
 </div>
    
    


</body>

</html>
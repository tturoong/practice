<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" href="./resource/css/bootstrap.min.css">
<link rel="stylesheet" href="./resource/css/custom.css">
<link rel="stylesheet" href="./resource/css/index.css">

<style type="text/css">
table {
	width: 100%; border-spacing: 0; border-collapse: collapse;
}

table td {
	text-align: right;
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
    <h6 style="margin: 10px;">주문관리 > 전체 주문 조회</h6>
    <h2 style="margin: 40px;">전체 주문 조회</h2>
    </div>
    
    <div>
    	<table border="1" style="width: 700px;" >
    	<form name="searchForm" action="#" method="post">
    		<tr>
    			<th>제품명</th>
    			<td>
    				<input type="text" name="productName" style="border-bottom: ">
    			</td>
    		</tr>
    		
    		<tr>
    			<th> 제품 가격 </th>
    				<td>
    					최소 <input type="text" name="productPriceMin">원 ~
    					최대 <input type="text" name="productPriceMax">원
    				</td>
    		</tr>
    		
    		<tr>
    			<th> 제품 종류 </th>
    				<td>

	    				<select name="col">
						<option value="all">모두</option>
						<option value="name">작성자</option>
						<option value="subject">제목</option>
						<option value="content">내용</option>
						<option value="date">등록일</option>
					</select>
						<input type="text" name="kwd"> 
				</td>
    			
    		</tr>
    		<tr>
    			<th>기간</th>
    			<td>
    			</td>
    		</tr>
    		<tr>
    			<th>처리상태</th>
    			<td>
    			</td>
    		</tr>
    		</form>
    	</table>
    </div>
    
</div>

</body>

</html>
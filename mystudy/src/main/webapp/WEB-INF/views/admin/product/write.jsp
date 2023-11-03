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
.table {  width: 1000px; margin: 20px auto;}

.tablebutton { margin: 20px auto; }



</style>


<script src="./resource	/js/jquery.min.js"></script>
<script src="./resource/js/popper.js"></script>
<script src="./resource/js/bootstrap.min.js"></script>
<script src="./resource/js/custom.js"></script>

<script type="text/javascript">
function modal() {
	alert("공지사항입니다.")
}

function sendOk() {
    
	const f = document.writeForm;
	/*
	let str;
	
    str = f.subject.value.trim();
    if(!str) {
        alert("제목을 입력하세요. ");
        f.subject.focus();
        return;
    }

    str = f.content.value.trim();
    if(!str) {
        alert("내용을 입력하세요. ");
        f.content.focus();
        return;
    }
*/
    f.action = "${pageContext.request.contextPath}/admin/product/${mode}_ok.do";
    f.submit();
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
    <h2 style="margin: 40px;">상품등록</h2>
    </div>
    
	
	<form name="writeForm" method="post" enctype="multipart/form-data">
	<table class="table table-form">
	<!-- 
		<tr>
			<td>제품코드</td>
			<td>
				<input type="text" name="productCode" class="form-control">
			</td>
		</tr>
		 -->
		<tr>
			<td>제품명</td>
			<td>
				<input type="text" name="productName" class="form-control" value="${dto.productName}">
			</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>
				<input type="text" name="productPrice" class="form-control" value="${dto.productPrice}">
			</td>
		</tr>
		<tr>
			<td>설명</td>
			<td>
				<input type="text" name="productSubject" class="form-control" value="${dto.productSubject}">
			</td>
		</tr>
		<tr>
			<td>유통기한</td>
			<td>
				<input type="text" name="expirationDate" class="form-control" value="${dto.expirationDate}">
			</td>
		</tr>
		<tr>
			<td>저장방법</td>
			<td>
				<input type="text" name="productStorage" class="form-control" value="${dto.productStorage}">
			</td>
		</tr>
		<tr>
			<td>주종</td>
			<td>
				<input type="radio" name="productCategory" value="탁주"  checked>탁주&nbsp;
				<input type="radio" name="productCategory" value="약주,청주">약주,청주&nbsp;
				<input type="radio" name="productCategory" value="과실주">과실주&nbsp;
				<input type="radio" name="productCategory" value="증류주">증류주&nbsp;
				<input type="radio" name="productCategory" value="기타주류">기타주류&nbsp;
			</td>
		</tr>
		<tr>
			<td>해쉬태그</td>
			<td>
				<input type="text" name="hashTag" class="form-control" value="${dto.hashTag}">
			</td>
		</tr>
		<tr>
			<td>도수</td>
			<td>
				<input type="text" name="alcoholPercent" class="form-control" value="${dto.alcoholPercent}">
			</td>
		</tr>
		<tr>
			<td>맛</td>
			<td>
				<input type="radio" name="productTaste" value="단맛" checked>단맛&nbsp;
				<input type="radio" name="productTaste" value="신맛">신맛&nbsp;
				<input type="radio" name="productTaste" value="쓴맛">쓴맛&nbsp;
				<input type="radio" name="productTaste" value="탄산">탄산&nbsp;
			</td>
		</tr>
		<tr>
			<td>대상</td>
			<td>
				<input type="radio" name="productPerson" value="웃어른" checked>웃어른&nbsp;
				<input type="radio" name="productPerson" value="연인">연인&nbsp;
				<input type="radio" name="productPerson" value="친구">친구&nbsp;
				<input type="radio" name="productPerson" value="혼자">혼자&nbsp;
				<input type="radio" name="productPerson" value="비즈니스">비즈니스&nbsp;
			</td>
		</tr>
		<tr>
			<td>재고</td>
			<td>
				<input type="text" name="inventory" class="form-control" value="${dto.inventory}">
			</td>
		</tr>
		<tr>
			<td>사진</td>
			<td>
				<!-- <input type="text" name="image" class="form-control" value="${dto.image}">  -->
				 
			</td>
		</tr>
		<tr>
			<td>단종여부</td>
			<td>
				<input type="text" name="extinctOrNot" class="form-control" value="${dto.extinctOrNot}">
			</td>
		</tr>
		<tr>
			<td>원가</td>
			<td>
				<input type="text" name="price" class="form-control" value="${dto.price}">
			</td>
		</tr>
		<tr>
			<td>용량</td>
			<td>
				<input type="text" name="volume" class="form-control" value="${dto.volume}">
			</td>
		</tr>
		<tr>
			<td>양조장홈페이지</td>
			<td>
				<input type="text" name="breweryPage" class="form-control" value="${dto.breweryPage}">
			</td>
		</tr>
		
	</table>
	
	<table class="tablebutton">
		<tr align="center">
			<td>
				<button type="button" class="btn" onclick="sendOk();">등록하기</button>
				<button type="reset" class="btn">다시입력</button>
			</td>
		</tr>
	</table>
	</form>
</div>
    
    


    


</body>

</html>
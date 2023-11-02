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
.searchbox { margin: 20px auto; }

.searchbutton { margin: 20px auto; }

table th { border-right: 1; padding: 20px }

.searchbox td { text-align: left; padding-left: 20px}


</style>


<script src="./resource	/js/jquery.min.js"></script>
<script src="./resource/js/popper.js"></script>
<script src="./resource/js/bootstrap.min.js"></script>
<script src="./resource/js/custom.js"></script>

<script type="text/javascript">
function modal() {
	alert("공지사항입니다.")
}

function searchList() {
	const f = document.searchForm;
	
	f.submit();
}

function check() {
	const f = document.listForm
	const chks = f.querySelectorAll('input[name=check]');
	
	chks.forEach(inputEL => 
	inputEL.checked = f.chkAll.checked);
}

function deleteList() {
	const f = document.listForm
	const chks = f.querySelectorAll('input[name=check]:checked');
	
	if(chks.length === 0) {
		alert('삭제할 게시글을 먼저 선택하세요...');
		return;
	}
	
	if(confirm('선택한 게시글을 삭제하시겠습니까 ? ')) {
		f.action = 'deleteList.jsp';
		f.submit();
	}
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
    <h2 style="margin: 40px;">상품관리</h2>
    </div>
    
    <div class="search">
    <form name="searchForm" action="" method="post">
    	<table class="searchbox" border="1" style="width: 1000px">
    		<tr>
    			<th>제품명</th>
    			<td>
    				<input type="text" name="productName"value="${productNameKwd}" class="form-control">
    			</td>
    		</tr>
    		<tr>
    			<th>가격</th>
    			<td>
    				<input type="checkbox" name="price" value="p1">1만원 미만&nbsp;
    				<input type="checkbox" name="price" value="p2">1만원 이상 ~ 3만원 미만&nbsp;
    				<input type="checkbox" name="price" value="p3">3만원 이상 ~ 5만원 미만&nbsp;
    				<input type="checkbox" name="price" value="p4">5만원 이상&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<th>용량</th>
    			<td>
    				<input type="checkbox" name="volume" value="b1">200ml 미만&nbsp;
    				<input type="checkbox" name="volume" value="b2">200ml 이상 ~ 500ml 미만&nbsp;
    				<input type="checkbox" name="volume" value="b3">500ml 이상&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<th>유통기한</th>
    			<td>
    				<input type="date" name="expirationDateStart" value="e1">~
    				<input type="date" name="expirationDateEnd" value="e2">
    			</td>
    		</tr>
    		<tr>
    			<th>주종</th>
    			<td>
    				<input type="checkbox" name="category" value="탁주">탁주&nbsp;
    				<input type="checkbox" name="category" value="약,청주">약,청주&nbsp;
    				<input type="checkbox" name="category" value="과실주">과실주&nbsp;
    				<input type="checkbox" name="category" value="증류주">증류주&nbsp;
    				<input type="checkbox" name="category" value="기타주류">기타주류&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<th>도수</th>
    			<td>
    				<input type="checkbox" name="alcohol" value="alcohol1">10도 미만&nbsp;
    				<input type="checkbox" name="alcohol" value="alcohol2">10도 이상 ~ 30도 미만&nbsp;
    				<input type="checkbox" name="alcohol" value="alcohol3">30도 이상&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<th>맛</th>
    			<td>
    				<input type="checkbox" name="taste" value="단맛">단맛&nbsp;
    				<input type="checkbox" name="taste" value="신맛">신맛&nbsp;
    				<input type="checkbox" name="taste" value="쓴맛">쓴맛&nbsp;
    				<input type="checkbox" name="taste" value="탄산">탄산&nbsp;
    			</td>
    		</tr>
    		<!-- 
    		<tr>
    			<th>대상</th>
    			<td>
    				<input type="checkbox" name="sms">웃어른
    				<input type="checkbox" name="sms">연인
    				<input type="checkbox" name="sms">친구
    				<input type="checkbox" name="sms">혼자
    				<input type="checkbox" name="sms">비즈니스
    			</td>
    		</tr>
    		 -->
    	</table>
    	</form>
    	
    	<table class="searchbutton">
			<tr align="center">
				<td  width="100">
					<button type="button" class="btn" onclick="searchList();">검색</button>
				</td>
				<td width="100">
					<button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/admin/product/list.do';">초기화</button>
				</td>
			</tr>    		
    	</table>
    </div>
    
    <div>
		
			<form>
			<table class="table table-border table-list">
				<thead>
					<tr>
						<th class="chk">
							<input type="checkbox" name="chkAll" id="chkAll" onclick="check();">        
						</th>
						<th class="num">번호</th>
						<th class="code">제품코드</th>
						<th class="name">제품명</th>
						<th class="img">사진</th>
						<th class="price">가격</th>
						<th class="volume">용량</th>
						<th class="brewery">양조장</th>
						<th class="subject">설명</th>
						<th class="exdate">유통기한</th>
						<th class="storage">저장방법</th>
						<th class="category">주종</th>
						<th class="hashtag">해쉬태그</th>
						<th class="alcohol">도수</th>
						<th class="taste">맛</th>
						<th class="recommended">대상</th>
						<th class="inventory">재고</th>
						<th class="extinctornot">단종여부</th>
						<th class="update">수정</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="dto" items="${list}" varStatus="status">
						<tr>
							<td><input type="checkbox" name="check" value=""></td>
							<td>${dataCount-(page-1)*size - status.index }</td>
							<td>${dto.productCode}</td>
							<td>${dto.productName}</td>
							<td>${dto.image}</td>
							<td>${dto.productPrice}</td>
							<td>${dto.volume}</td>
							<td>${dto.breweryPage}</td>
							<td>${dto.productSubject}</td>
							<td>${dto.expirationDate}</td>
							<td>${dto.productStorage}</td>
							<td>${dto.productCategory}</td>
							<td>${dto.hashTag}</td>
							<td>${dto.alcoholPercent}</td>
							<td>${dto.productTaste}</td>
							<td>${dto.productPerson}</td>
							<td>${dto.inventory}</td>
							<td>${dto.extinctOrNot}</td>
							<td>
								<button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/admin/product/update.do';">수정</button>
							</td>
							
							 
							<%-- <td class="left">
								<c:forEach var="n" begin="1" end="${dto.depth }">&nbsp;&nbsp;</c:forEach>
								<c:if test="${dto.depth!=0}">└&nbsp;</c:if>
								<a href="${articleUrl}&boardNum=${dto.boardNum}">${dto.subject}</a>
							</td> --%>
							 
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</form>
			
			<table>
			<tr align="right">
				<td>
					<button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/admin/product/write.do';">상품등록</button>
				</td>
				
				<td>
					<button type="button" class="btn" onclick="deleteList()">상품삭제</button>
				</td>
			</tr>
			</table>
			
		<div class="page-navigation">
			${dataCount == 0 ? "등록된 게시글이 없습니다." : paging }
		</div>
    </div>
    </div>
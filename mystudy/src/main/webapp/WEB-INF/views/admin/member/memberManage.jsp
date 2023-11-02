<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/index.css">



<script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/custom.js"></script>


<style>
.mytable {
	border-bottom: 1px;
	width:70%;
	border-radius:10px; 
	border-style: hidden; 
	box-shadow: 0 0 0 1px #999;
	
}
.btn {
	color: #333333;
	border: 1px solid #999999;
	background-color: #ffffff;
	padding: 5px 10px;
	border-radius: 4px;
	font-weight: 500;
	cursor:pointer;
	font-size: 14px;
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
	vertical-align: baseline;
}
.btn:active, .btn:focus, .btn:hover {
	background-color: #f8f9fa;
	color:#333333;
}
.btn[disabled], fieldset[disabled] .btn {
	pointer-events: none;
	cursor: not-allowed;
	filter: alpha(opacity=65);
	-webkit-box-shadow: none;
	box-shadow: none;
	opacity: .65;
}

.btn-primary {
    background: #106eea;
	border:1px solid #00a6eb;
    color:#ffffff;
}
.btn-primary:hover, .btn-primary:active, .btn-primary:focus {
    background: #3284f1;
    border:1px solid #00a6eb;
    color:#ffffff;
}

</style>

<script type="text/javascript">
function searchList() {
	const f = document.searchForm;
	f.submit();
}

function deleteMember(mNum){
	if(confirm('회원 삭제하시겠습니까?')){
		location.href = "${pageContext.request.contextPath}/admin/member/update_ok.do?mNum="+mNum;
	}
}
</script>

</head>
<body id="body-pd">
    <div class="l-navbar" id="navbar">
        <nav class="nav">
            <div>	
                <div class="nav__brand">
                        <img src="${pageContext.request.contextPath}/resource/images/icon/more.png" name="menu-outline" class="nav__toggle" id="nav-toggle" style="width: 28px;" onclick="langHide()">
                </div>
                <div class="nav__list">
                    <a href="#" class="nav__link active">
                        <img src="${pageContext.request.contextPath}/resource/images/icon/person.png" style="width: 28px; background-color: #cccccc; border-radius: 50%; padding: 5px;" name="menu1">
                        	<span class="nav_name">로그인 / 회원가입</span>
                         </a>
                    <a href="#" class="nav__link">
                        <img src="${pageContext.request.contextPath}/resource/images/icon/home.png" style="width: 28px;" name="menu2">
                        <span class="nav_name" style="color: #1682b0;">홈</span>
                    </a>
                    <a href="#" class="nav__link" onmouseover="menu3.src='${pageContext.request.contextPath}/resource/images/icon/messenger_hover.png'" onmouseout="menu3.src='${pageContext.request.contextPath}/resource/images/icon/messenger.png'">
                        <img src="${pageContext.request.contextPath}/resource/images/icon/messenger.png" style="width: 28px;" name="menu3">
                        <span class="nav_name">커뮤니티</span>
                    </a>
                    <a href="#" class="nav__link" onmouseover="menu4.src='${pageContext.request.contextPath}/resource/images/icon/comment01.png'" onmouseout="menu4.src='${pageContext.request.contextPath}/resource/images/icon/comment.png'">
                        <img src="${pageContext.request.contextPath}/resource/images/icon/comment.png" style="width: 28px;" name="menu4">
                        <span class="nav_name">사용가이드</span>
                    </a>
                </div>
                
            </div>
        </nav>
    </div>
    
    <div id="wrap" style="padding: 0px 100px;">
    <div class="navbar" style="margin-top: 52px;">
        
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/member/list.do" style="position: absolute; left: 45%;">
           	회원 관리
        </a>
        
    </div>
    <hr>
    <div id="recommend">
    <h6 style="margin: 10px;">회원관리 > 전체 회원 리스트</h6>
    <h2 style="margin: 40px;"> 회원 리스트</h2>
    </div>
    
    
    <div class="body-main mx-auto">
    <div>
    	<form name="searchForm" action="${pageContext.request.contextPath}/admin/member/list.do" method="post">
    	<table class="mytable">
    		<tr>
    			<th>수신동의</th>
    			<td>
    				<input type="checkbox" name="agreeSms" value="Y">sms
    				<input type="checkbox" name="agreeEmail" value="Y">email
    			</td>
    		</tr>
    		<tr style="border:hidden">
    			<th>검색</th>
    			<td>
					<select name="schType" >
						<option value="">:::선택:::</option>
						<option value="mName">이름</option>
						<option value="mAge">연령대</option>
						<option value="mRegDate">가입일</option>
					</select>
					<input type="text" name="kwd" value="${kwd}" style="width:300px">
					<button type="button" class="btn" onclick="searchList();">검색</button>
    			</td>
    		</tr>
    		
    	</table>
    	</form>
		
    </div>
    		<form name="deleteForm" action="${pageContext.request.contextPath}/admin/member/update.do" method="post">
    		
			<table class="table">
				<tr>
					<td width="50%">
						${dataCount}개(${page}/${total_page} 페이지)
					</td>
					<td align="right">&nbsp;</td>
				</tr>
			</table>
			
			<table class="table table-border table-list">
				<thead>
					<tr>
						<th>
							<input type="checkbox" name="chkAll" id="chkAll">        
						</th>
						<th class="num">번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>주민번호</th>
						<th>일반전화번호</th>
						<th>핸드폰번호</th>
						<th>이메일</th>
						<th>가입일</th>
						<th>문자수신</th>
						<th>이메일수신</th>
						<th>관리자여부</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="dto" items="${list}" varStatus="status">
					<c:if test="${dto.deleteOrNot==0}">
						<tr>
							<td>
								<input type="checkbox" name="nums" value="${ dto.mNum }">
							</td>
							<td>${dataCount - (page-1) * size - status.index}</td>
							<td>${dto.mId}</td>
							<td>${dto.mName}</td>
							<td>${dto.mRnum}</td>
							<td>${dto.mTel}</td>
							<td>${dto.mPhone}</td>
							<td>${dto.mEmail}</td>
							<td>${dto.mRegDate}</td>
							<td>${dto.field}</td>
							<td>${dto.field2}</td>
							<td>${dto.adminOrNot}</td>
							<td><button type="button" onclick="deleteMember('${dto.mNum}');">삭제</button></td>
						</tr>
					</c:if>
					</c:forEach>
				</tbody>
			</table>
			</form>
			<button type="button" onclick="">선택삭제</button> 
			<div class="page-navigation">
				${dataCount == 0 ? "등록된 회원이 없습니다." : paging}
			</div>
				
	    </div>
    	
    
</div>

</body>

</html>
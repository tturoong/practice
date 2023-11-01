<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<style type="text/css">
body {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	margin: 0 auto;
	height: 100vh;
	background-color: #f1f1f1;
}

table {
	width: 100%; border-spacing: 0; border-collapse: collapse;
}

table td {
	text-align: right;
}

.box1 { width: 1600px; 
	display: flex;
	overflow: auto;
	justify-content: center;
    align-items: center;}
    
.box2 { width: 1600px;}

.dashbox { border: 2px solid skyblue; width: 300px;  margin: 10px;}

.summary { border: 2px solid skyblue; width: 50%; text-align: right; margin: 10px 15px 40px 15px;}

th, td { padding: 10px; }


.dashbox td:nth-child(2n-1) { text-align: left; }
.dashbox td:nth-child(2n) { text-align: right; }

.summary tr:last-child {border-top: 1px dotted gray; border-collapse: collapse;}
.summary td:nth-child(6n+1) { text-align: center; }

.dash {
	display: flex;
	flex-wrap: wrap;
	overflow: auto;
	justify-content: center;
    align-items: center;
}

.container {
	width: 1000px; margin: 20px auto 10px;
	border: 3px solid black;
}

</style>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.4.2/css/all.css">
</head>
<body>
<div>
	<h3> 통계 </h3>
</div>

<div class="container">
	<div class="dash">
		<div class="box1">
			<table class="dashbox">
				<tr>
					<th onclick="location.href='${pageContext.request.contextPath}/statistic/graph.do';"><i class="fa-solid fa-store"></i>&nbsp; 회원 가입자 통계</th>
				</tr>
			</table>
			
			<table class="dashbox">
				<tr>
					<th onclick="location.href='${pageContext.request.contextPath}/statistic/sales.do';"><i class="fa-solid fa-truck-fast"></i>&nbsp; 매출 관리 </th>
				</tr>
			</table>
			
			<table class="dashbox">
				<tr>
					<th><i class="fa-solid fa-newspaper">&nbsp; 접속자 수 관리</i></th>
				</tr>
			</table>
		</div>
	</div>

	<div style="width: 100%; height: 100%;">
		<div>
			<form name="dateForm" action="${pageContext.request.contextPath}/statistic/sales.do" method="post">
				<table>
					<tr>
	
						<td>
							<span style="align-items: flex-end;">기간</span>
							<select name = "date" id = "date">
								<option value="1" ${ date == "1" ? "selected":"0"}>일별</option>
								<option value="2" ${ date == "2" ? "selected":"0"}>월별</option>
<%-- 								<option value="3" ${ date == "3" ? "selected":"0"}>연별</option> --%>
							</select>
							<button type="submit" onclick="location.href='${pageContext.request.contextPath}/statistic/sales.do';"> 전송 </button>
							
						</td>
					</tr>
				</table>
			</form>	
		</div>
	
		<div>
			<canvas id="myChart">
			</canvas>
		</div>
		
		<div>
			
		</div>
		
	</div>
</div>



</body>

<script>
const ctx = document.getElementById('myChart').getContext('2d');
const myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: [
        	'${label}'
        	/* 'Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange' */
        	],
        datasets: [{
            label: '매출 (단위 : 원)' ,
            data: [
            	/* 12, 19, 3, 5, 2, 3 */
            	${data}
            	],
            backgroundColor: [
                /* 'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)' */
            ],
            borderColor: [
                /* 'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)' */
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
</script>





</html>
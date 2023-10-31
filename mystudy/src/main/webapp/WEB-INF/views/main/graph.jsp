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


</style>
</head>
<body>
<div>
	<h3> 통계 </h3>
</div>

<div style="width: 1000px; height: 1000px;">
	<div>
		<form name="dateForm" action="${pageContext.request.contextPath}/statistic/graph.do" method="post">
			<table>
				<tr>

					<td>
						<span>기간</span>
						<select name = "date" id = "date">
							<option value="1" ${ date == "1" ? "selected":"0"}>일별</option>
							<option value="2" ${ date == "2" ? "selected":"0"}>월별</option>
							<option value="3" ${ date == "3" ? "selected":"0"}>연별</option>
						</select>
					</td>
					<td>
						<button type="submit" onclick="location.href='${pageContext.request.contextPath}/statistic/graph.do';"> 전송 </button>
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
            label: '회원가입자 수',
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
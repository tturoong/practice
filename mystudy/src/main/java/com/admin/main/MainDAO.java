package com.admin.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;
import com.util.DBUtil;

public class MainDAO {
	private Connection conn = DBConn.getConnection();
	
	// 대시보드 그래프
	public List<MainDTO> graph() throws SQLException {
		List<MainDTO> list = new ArrayList<MainDTO>();
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		LocalDate now = LocalDate.now();
		LocalDate past = LocalDate.of(now.getYear(), now.getMonthValue()-1, 1);

		
		ResultSet rs = null;
		MainDTO dto = null;

		try {
		
				// 일별 조회
				String currentYear = String.valueOf(now.getYear());
				String currentMonth;
				String startMonth;
				String currentDayOfMonth;
				String startDayOfMonth;
				int tmpMonth;
				String pastDate;
				String endDate = now.withDayOfMonth(now.lengthOfMonth()).toString();
				endDate = endDate.substring(endDate.length()-2, endDate.length());
				
				// 오늘이 말일이면 다음달 1월로
				if ( now.getDayOfMonth() == Integer.parseInt(endDate) ) {
//					System.out.println(cal.DAY_OF_MONTH);

					currentMonth = String.valueOf(now.getMonthValue()+1);
					startMonth = String.valueOf(now.getMonthValue());
					currentDayOfMonth = String.valueOf(1);
				} else {
					// 아니면 평소처럼
					startMonth = String.valueOf(now.getMonthValue());	//	11
					currentMonth = String.valueOf(now.getMonthValue());	//	11
					currentDayOfMonth = String.valueOf(now.getDayOfMonth()+1);	//	2
				}
				
				// 오늘이 1~7일이면
				if (now.getDayOfMonth() <= 7) {
					int difference = 7 - now.getDayOfMonth();
					//  startDayOfMonth가 저번달 말일이어야함
					// 	1일 일때	->	 start 가  now.withDayOfMonth(now.lengthOfMonth()-1) -6 -> 
					// 2일 일때 - > start 가 now.withDayOfMonth(now.lengthOfMonth()-1) - 5
					
					// 월 처리
					tmpMonth = Integer.parseInt(startMonth);
					tmpMonth -= 1;
					startMonth = String.valueOf(tmpMonth);
					// 일 처리
					
					System.out.println(past + "past");
					pastDate = past.withDayOfMonth(past.lengthOfMonth()).toString();
					System.out.println(pastDate + "pastDate");
					// 2023-10-31

					pastDate = pastDate.substring(pastDate.length()-2, pastDate.length());
					int a = Integer.parseInt(pastDate);
					a -= difference;
					startDayOfMonth = String.valueOf(a) ;
				} else {
					startDayOfMonth = String.valueOf(now.getDayOfMonth()-7);
				}
				
				
				
				
				sb.append("SELECT TO_CHAR(b.dt, 'YYYY-MM-DD') AS mRegDate, NVL(SUM(a.cnt), 0) AS cnt ");
	            sb.append(" FROM (SELECT TO_CHAR(mRegDate, 'YYYY-MM-DD') AS mRegDate, COUNT(*) AS cnt ");
	            sb.append(" FROM member ");
	            sb.append(" WHERE mRegDate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ");
	            sb.append(" GROUP BY mRegDate) a , ");
	            sb.append(" ( SELECT TO_DATE(?, 'YYYY-MM-DD') + LEVEL - 1 AS dt ");
	            sb.append(" FROM dual ");
	            sb.append(" CONNECT BY LEVEL <= (TO_DATE(?, 'YYYY-MM-DD') - TO_DATE(?, 'YYYY-MM-DD') + 1)) b ");
	            sb.append(" WHERE b.dt = a.mRegDate(+) ");
	            sb.append(" GROUP BY b.dt ");
	            sb.append(" ORDER BY b.dt");
	            
	            String start = currentYear + "-" + startMonth + "-" + startDayOfMonth;
				String last = currentYear + "-" + currentMonth + "-" + currentDayOfMonth;

				pstmt = conn.prepareStatement(sb.toString());
				
				
				pstmt.setString(1, start);
				pstmt.setString(2, last);
				pstmt.setString(3, start);
				pstmt.setString(4, last);
				pstmt.setString(5, start);
			
				rs = pstmt.executeQuery();
			
				while( rs.next() ) {
					dto = new MainDTO();
					
					dto.setmRegDate(rs.getString("mRegDate"));
					dto.setCnt(rs.getInt("cnt"));
					System.out.println(rs.getString("mRegDate"));
					System.out.println(rs.getInt("cnt"));
					list.add(dto);
				
				}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			DBUtil.close(pstmt);
			DBUtil.close(rs);
		}
		
		
		return list;
	}
	
}

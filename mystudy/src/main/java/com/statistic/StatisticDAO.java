package com.statistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.util.DBConn;
import com.util.DBUtil;

public class StatisticDAO {
	private Connection conn = DBConn.getConnection();
	
	public List<StatisticDTO> regDateGraph(int count) throws SQLException {
		List<StatisticDTO> list = new ArrayList<StatisticDTO>();
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		LocalDate now = LocalDate.now();
		ResultSet rs = null;
		StatisticDTO dto = null;

		
		
		
		
		try {
//			count가 1이면 일별 2면 월별 3이면 연별
			if ( count == 1 ) {
	 			// 일별 조회1
//				sb.append(" SELECT TO_CHAR(b.dt, 'YYYY-MM-DD') AS mRegDate ");
//				sb.append(" , NVL(SUM(a.cnt), 0) cnt ");
//				sb.append(" FROM ( SELECT TO_CHAR(mRegDate, 'YYYY-MM-DD') AS mRegDate ");
//				sb.append(" , COUNT(*) cnt ");
//				sb.append(" FROM member ");
//				sb.append(" WHERE mRegDate BETWEEN TO_DATE(?, 'YYYY-MM-DD') ");
//				sb.append(" AND TO_DATE(?, 'YYYY-MM-DD') ");
//				sb.append(" GROUP BY mRegDate ");
//				sb.append(" ) a ");
//				sb.append(" , ( SELECT TO_DATE(?,'YYYY-MM-DD') + LEVEL - 1 AS dt ");
//				sb.append(" FROM dual ");
//				sb.append(" CONNECT BY LEVEL <= (TO_DATE(?,'YYYY-MM-DD') ");
//				sb.append(" - TO_DATE(?,'YYYY-MM-DD') + 1) ");
//				sb.append(" ) b ");
//				sb.append(" WHERE b.dt = a.mRegDate(+) ");
//				sb.append(" GROUP BY b.dt ");
//				sb.append(" ORDER BY b.dt ");
				
				// 일별 조회 2
				String currentYear = String.valueOf(now.getYear());
				String currentMonth;
				String startMonth;
				String currentDayOfMonth;
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
					startMonth = String.valueOf(now.getMonthValue());
					currentMonth = String.valueOf(now.getMonthValue());
					currentDayOfMonth = String.valueOf(now.getDayOfMonth()+1);
				}
				
				
				
				String startDayOfMonth = String.valueOf(now.getDayOfMonth()-7);
				
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

		} else if ( count == 2) {
			// 월별
	            
	            String currentYear = String.valueOf(now.getYear());
				String currentMonth = String.valueOf(now.getMonthValue()+1);
				String currentDayOfMonth = "01";
				// 20231101
				// 20230301
				String startMonth = String.valueOf(now.getMonthValue()-6);
			
				
				
				
				sb.append(" SELECT TO_CHAR(b.dt, 'YYYY-MM') AS mRegDate, NVL(SUM(a.cnt), 0) cnt ");
				sb.append(" FROM (SELECT TO_CHAR(mRegDate, 'YYYY-MM-DD') AS mRegDate, COUNT(*) cnt ");
				sb.append(" FROM member ");
				sb.append(" WHERE mRegDate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ");
				sb.append(" GROUP BY mRegDate) a , ");
				sb.append(" ( SELECT TO_DATE(?,'YYYY-MM-DD') + LEVEL - 1 AS dt ");
				sb.append(" FROM dual ");
				sb.append(" CONNECT BY LEVEL <= (TO_DATE(?, 'YYYY-MM-DD') - TO_DATE(?, 'YYYY-MM-DD') + 1)) b ");
				sb.append(" WHERE b.dt = a.mRegDate(+) ");
				sb.append(" GROUP BY TO_CHAR(b.dt, 'YYYY-MM') ");
				sb.append(" ORDER BY TO_CHAR(b.dt, 'YYYY-MM') ");
				
				if (currentMonth.length() == 1) {
					currentMonth = "0" + currentMonth; 
				} 
				if( currentDayOfMonth.length() == 1) {
					currentDayOfMonth = "0" + currentDayOfMonth;
				} 
				if (startMonth.length() == 1) {
					startMonth = "0" + startMonth;
				}
				
				
				String last = currentYear + "-" + currentMonth + "-" + currentDayOfMonth;
				String start = currentYear + "-" + startMonth + "-" + currentDayOfMonth;
			
				pstmt = conn.prepareStatement(sb.toString());
				
				pstmt.setString(1, start);
				pstmt.setString(2, last);
				pstmt.setString(3, start);
				pstmt.setString(4, last);
				pstmt.setString(5, start);
			
//		} else if ( count == 3) {
			 //연별
		} else {
			String currentYear = String.valueOf(now.getYear());
			String currentMonth = String.valueOf(now.getMonthValue());
			String currentDayOfMonth = String.valueOf(now.getDayOfMonth()+1);
			String startDayOfMonth = String.valueOf(now.getDayOfMonth()-7);
			
			
			
			String start = currentYear + "-" + currentMonth + "-" + startDayOfMonth;
			String last = currentYear + "-" + currentMonth + "-" + currentDayOfMonth;
		}			
			
			


			
			
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				dto = new StatisticDTO();
				
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

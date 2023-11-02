package com.admin.statistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
		LocalDate past = LocalDate.of(now.getYear(), now.getMonthValue()-1, 1);
		ResultSet rs = null;
		StatisticDTO dto = null;

		
		
		
		
		try {
//			count가 1이면 일별 2면 월별 3이면 연별
			if ( count == 1 ) {
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

					currentMonth = String.valueOf(now.getMonthValue()+1);
					startMonth = String.valueOf(now.getMonthValue());
					currentDayOfMonth = String.valueOf(1);
				} else {
					// 아니면 평소처럼
					startMonth = String.valueOf(now.getMonthValue());
					currentMonth = String.valueOf(now.getMonthValue());
					currentDayOfMonth = String.valueOf(now.getDayOfMonth()+1);
				}
				
				// 오늘이 1~7일이면
				if (now.getDayOfMonth() <= 7) {
					int difference = 7 - now.getDayOfMonth();
					
					// 월 처리
					tmpMonth = Integer.parseInt(startMonth);
					tmpMonth -= 1;
					startMonth = String.valueOf(tmpMonth);
					// 일 처리
					pastDate = past.withDayOfMonth(past.lengthOfMonth()).toString();

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

		} else if ( count == 2) {
			// 월별
	            
	            String currentYear = String.valueOf(now.getYear());
				String currentMonth = String.valueOf(now.getMonthValue()+1);
				String currentDayOfMonth = "01";

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
	
	public List<StatisticDTO> salesGraph(int count) throws SQLException {
		List<StatisticDTO> list = new ArrayList<StatisticDTO>();
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		LocalDate now = LocalDate.now();
		LocalDate past = LocalDate.of(now.getYear(), now.getMonthValue()-1, 1);
		ResultSet rs = null;
		StatisticDTO dto = null;

		
		
		
		
		try {
//			count가 1이면 일별 2면 월별 3이면 연별
			if ( count == 1 ) {
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

					currentMonth = String.valueOf(now.getMonthValue()+1);
					startMonth = String.valueOf(now.getMonthValue());
					currentDayOfMonth = String.valueOf(1);
				} else {
					// 아니면 평소처럼
					startMonth = String.valueOf(now.getMonthValue());
					currentMonth = String.valueOf(now.getMonthValue());
					currentDayOfMonth = String.valueOf(now.getDayOfMonth()+1);
				}
				
				// 오늘이 1~7일이면
				if (now.getDayOfMonth() <= 7) {
					int difference = 7 - now.getDayOfMonth();
					
					// 월 처리
					tmpMonth = Integer.parseInt(startMonth);
					tmpMonth -= 1;
					startMonth = String.valueOf(tmpMonth);
					// 일 처리
					pastDate = past.withDayOfMonth(past.lengthOfMonth()).toString();

					pastDate = pastDate.substring(pastDate.length()-2, pastDate.length());
					int a = Integer.parseInt(pastDate);
					a -= difference;
					startDayOfMonth = String.valueOf(a) ;
				} else {
					startDayOfMonth = String.valueOf(now.getDayOfMonth()-7);
				}

				sb.append("SELECT TO_CHAR(b.dt, 'YYYY-MM-DD') AS orderDate, NVL(SUM(a.totalPrice), 0) AS totalPrice ");
	            sb.append(" FROM (SELECT TO_CHAR(orderDate, 'YYYY-MM-DD') AS orderDate, totalPrice ");
	            sb.append(" FROM orderPrice ");
	            sb.append(" WHERE orderDate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ");
	            sb.append(" GROUP BY orderDate, totalPrice, orderNum ) a , ");
	            sb.append(" ( SELECT TO_DATE(?, 'YYYY-MM-DD') + LEVEL - 1 AS dt ");
	            sb.append(" FROM dual ");
	            sb.append(" CONNECT BY LEVEL <= (TO_DATE(?, 'YYYY-MM-DD') - TO_DATE(?, 'YYYY-MM-DD') + 1)) b ");
	            sb.append(" WHERE b.dt = a.orderDate(+) ");
	            sb.append(" GROUP BY b.dt ");
	            sb.append(" ORDER BY b.dt");
	            
	            String start = currentYear + "-" + startMonth + "-" + startDayOfMonth;
				String last = currentYear + "-" + currentMonth + "-" + currentDayOfMonth;
				System.out.println(start);
				System.out.println(last);
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

				String startMonth = String.valueOf(now.getMonthValue()-6);
			
				/*
				sb.append(" SELECT TO_CHAR(b.dt, 'YYYY-MM') AS orderMonth, ");
				sb.append(" TO_CHAR(b.dt, 'YYYY-MM-DD') AS orderDate, ");
				sb.append(" NVL(SUM(a.totalPrice), 0) AS totalPrice ");
				sb.append(" FROM ( ");
				sb.append("     SELECT TO_CHAR(orderDate, 'YYYY-MM-DD') AS orderDate, totalPrice ");
				sb.append("     FROM orderPrice ");
				sb.append("     WHERE orderDate BETWEEN TO_DATE(?, 'YYYY-MM-DD') ");
				sb.append("         AND TO_DATE(?, 'YYYY-MM-DD') ");
				sb.append(" ) a ");
				sb.append(" RIGHT JOIN ( ");
				sb.append("     SELECT TO_DATE(?, 'YYYY-MM-DD') + LEVEL - 1 AS dt ");
				sb.append("     FROM dual ");
				sb.append("     CONNECT BY LEVEL <= (TO_DATE(?, 'YYYY-MM-DD') - TO_DATE(?, 'YYYY-MM-DD') + 1) ");
				sb.append(" ) b ON b.dt = a.orderDate ");
				sb.append(" GROUP BY TO_CHAR(b.dt, 'YYYY-MM'), TO_CHAR(b.dt, 'YYYY-MM-DD') ");
				sb.append(" ORDER BY TO_CHAR(b.dt, 'YYYY-MM') ");
				*/
				
				sb.append(" SELECT TO_CHAR(orderDate, 'YYYY-MM') AS orderMonth, SUM(totalPrice) AS totalPrice ");
				sb.append(" FROM orderPrice ");
				sb.append(" WHERE orderDate BETWEEN TO_DATE(?, 'YYYY-MM-DD') ");
				sb.append(" AND TO_DATE(?, 'YYYY-MM-DD') ");
				sb.append(" GROUP BY TO_CHAR(orderDate, 'YYYY-MM') ");
				sb.append(" ORDER BY TO_CHAR(orderDate, 'YYYY-MM')");
				

				
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
			
		} 		
			

			rs = pstmt.executeQuery();
			
			if( count ==2 ) {
				while ( rs.next() ) {
					dto = new StatisticDTO();
					dto.setmRegDate(rs.getString("orderMonth"));
					dto.setCnt(rs.getInt("totalPrice"));

					list.add(dto);
				}
			} else {
				while ( rs.next() ) {
					dto = new StatisticDTO();
					dto.setmRegDate(rs.getString("orderDate"));
					dto.setCnt(rs.getInt("totalPrice"));

					list.add(dto);
				}
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

package com.admin.statistic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.statistic.StatisticDAO;
import com.util.MyServlet;

@WebServlet("/statistic/*")
public class StatisticServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri=req.getRequestURI();
		
		if(uri.indexOf("statistic.do") != -1) {

			// forward(req,resp, "/WEB-INF/main.jsp");
		} else if (uri.indexOf("sales.do") != -1) {
//			판매 정산
//			- 필터링 ( 기간별, 상품 카테고리별 )
			salesGraph(req, resp);
		} else if(uri.indexOf("register.do") != -1) {
			
			
			
		} else {
			//회원가입자수 그래프
			regDateGraph(req, resp);
		}
	} 
	
	protected void regDateGraph(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StatisticDAO dao = new StatisticDAO();
		String cp = req.getContextPath();
		String label = "";
		String data = "";
//		if(req.getMethod().equalsIgnoreCase("GET")) {
//			resp.sendRedirect(cp + "/");
//			return;
//		}
		
		
		try {
			List<StatisticDTO> list = null;
			int count;
			if(req.getParameter("date") != null) {
				count = Integer.parseInt(req.getParameter("date"));
			} else {
				count = 1;
			}
			
			
			 list = dao.regDateGraph(count);
			
			
			for(StatisticDTO dto: list) {
				if( list.indexOf(dto) == list.size()-1) {
					
					break;
				}
				
				label += dto.getmRegDate();
				data += dto.getCnt();
				
				label += "','";
				data += ",";
						
			}
			
			if( label.substring(label.length()-1, label.length()).equals("'") ) {
				label = label.substring(0, label.length() - 3);
			}
				
			if( data.substring(data.length()-1, data.length()).equals(",") ) {
				data = data.substring(0, data.length() - 1);
			}
				 

			
			req.setAttribute("list", list);
			req.setAttribute("label", label);
			req.setAttribute("data", data);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		forward(req, resp,"/WEB-INF/views/admin/statistic/registerGraph.jsp");

	}
	
	protected void salesGraph(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StatisticDAO dao = new StatisticDAO();
		String cp = req.getContextPath();
		String label = "";
		String data = "";
//		if(req.getMethod().equalsIgnoreCase("GET")) {
//			resp.sendRedirect(cp + "/");
//			return;
//		}
		
		
		try {
			List<StatisticDTO> list = null;
			int count;
			if(req.getParameter("date") != null) {
				count = Integer.parseInt(req.getParameter("date"));
			} else {
				count = 1;
			}
			
			
			 list = dao.salesGraph(count);
			
			 
			 
			 
			
			for(StatisticDTO dto: list) {
				if( list.indexOf(dto) == list.size()-1 && count == 1) {
					
					break;
				}
				
				
				
				
				
				label += dto.getmRegDate();
				data += dto.getCnt();
				
				label += "','";
				data += ",";
				
			}
			
			if( label.substring(label.length()-1, label.length()).equals("'") ) {
				label = label.substring(0, label.length() - 3);
			}
				
			if( data.substring(data.length()-1, data.length()).equals(",") ) {
				data = data.substring(0, data.length() - 1);
			}
				 

			
			req.setAttribute("list", list);
			
			
			
			req.setAttribute("label", label);
			req.setAttribute("data", data);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		forward(req, resp,"/WEB-INF/views/admin/statistic/salesGraph.jsp");

	}
}

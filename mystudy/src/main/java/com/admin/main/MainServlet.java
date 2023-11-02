package com.admin.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.MyServlet;

@WebServlet("/admin/main.do")
public class MainServlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri=req.getRequestURI();
		
		if(uri.indexOf("main.do") != -1) {
			graph(req, resp);
			
		} else if (uri.indexOf("statistic.do") != -1) {
			System.out.println("wrong way");
		} else if(uri.indexOf("member.do") != -1) {
			forward(req, resp, "/WEB-INF/views/admin/member/memberManage.jsp");
		}
	}
	
	protected void graph(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 그래프
		MainDAO dao = new MainDAO();
		String label = "";                                       
		String data = "";
		try {
			List<MainDTO> list = dao.graph();
			
			for(MainDTO dto : list) {
				if( list.indexOf(dto) == list.size()-1 ) {
					
					break;
				}
				
				label += dto.getmRegDate();
				data += dto.getCnt();
				
				label += "','";
				data += ",";
				
				if( list.indexOf(dto) == list.size()-2) {
					int todayReg = dto.getCnt();
					req.setAttribute("todayReg", todayReg);
				}
				
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
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		forward(req, resp, "/WEB-INF/views/admin/main/main.jsp");
	}
}



package com.admin.member;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/admin/member/*")
public class MemberServlet extends MyServlet {
   private static final long serialVersionUID = 1L;

   @Override
   protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("utf-8");

      String uri = req.getRequestURI();
      
      // 세션 정보
      /*
      // LoginFilter에서 처리
      HttpSession session = req.getSession();
      SessionInfo info = (SessionInfo) session.getAttribute("member");

      if (info == null) {
         viewPage(req, resp, "member/login.jsp");
         return;
      }
      */

      // uri에 따른 작업 구분
      if (uri.indexOf("list.do") != -1) {
         list(req, resp);
      /*} else if (uri.indexOf("write.do") != -1) {
         writeForm(req, resp);
      } else if (uri.indexOf("write_ok.do") != -1) {
         writeSubmit(req, resp);
      } else if (uri.indexOf("article.do") != -1) {
         article(req, resp);
      
      } else if (uri.indexOf("delete.do") != -1) {
         delete(req, resp);
      
      } else if (uri.indexOf("update.do") != -1) { 
          updateForm(req, resp);*/
      } else if (uri.indexOf("updateList.do") != -1) { //일괄삭제
    	  updateListForm(req,resp);
      } else if (uri.indexOf("update_ok.do") != -1) { // 개별삭제
          updateSubmit(req, resp);
      }
   }

   private void updateListForm(HttpServletRequest req, HttpServletResponse resp) {
	
   }

   protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // 게시물 리스트
      MemberDAO dao = new MemberDAO();
      MyUtil util = new MyUtil();

      String cp = req.getContextPath();
      
      try {
         String page = req.getParameter("page");
         int current_page = 1;
         if (page != null) {
            current_page = Integer.parseInt(page);
         }
         
         // 검색
         String schType = req.getParameter("schType");
         String kwd = req.getParameter("kwd");
         String agreeSms = req.getParameter("agreeSms");
         String agreeEmail = req.getParameter("agreeEmail");
         
         System.out.println(agreeSms);
         if (schType == null) {
            schType = "all";
            kwd = "";
         }
         

         // GET 방식인 경우 디코딩
         if (req.getMethod().equalsIgnoreCase("GET")) {
            kwd = URLDecoder.decode(kwd, "utf-8");
         }

         // 전체 데이터 개수
         int dataCount;
         if (kwd.length() == 0 && agreeSms==null || agreeEmail==null) { 
        	 dataCount = dao.dataCount();
            
         } else if(kwd.length() == 0){ 
        	 dataCount = dao.dataCount2(agreeSms, agreeEmail);
         } else {
            dataCount = dao.dataCount(schType, kwd);
         }
         
         // 전체 페이지 수
         int size = 10;
         int total_page = util.pageCount(dataCount, size);
         if (current_page > total_page) {
            current_page = total_page;
         }

         // 게시물 가져오기
         int offset = (current_page - 1) * size;
         if(offset < 0) offset = 0;
         
         List<MemberDTO> list = null;
         
         if(kwd.length() == 0 && agreeSms.length() != 0 ) {
        	list = dao.listMember(offset, size, agreeSms); 
         } else if (kwd.length() == 0) {
            list = dao.listMember(offset, size);
         } else {
            list = dao.listMember(offset, size, schType, kwd);
         }

         String query = "";
         if (kwd.length() != 0) {
            query = "schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "utf-8");
         }

         // 페이징 처리
         String listUrl = cp + "/admin/member/list.do";
         String articleUrl = cp + "/admin/member/article.do?page=" + current_page;
         if (query.length() != 0) {
            listUrl += "?" + query;
            articleUrl += "&" + query;
         }

         String paging = util.paging(current_page, total_page, listUrl);

         // 포워딩할 JSP에 전달할 속성
         req.setAttribute("list", list);
         req.setAttribute("page", current_page);
         req.setAttribute("total_page", total_page);
         req.setAttribute("dataCount", dataCount);
         req.setAttribute("size", size);
         req.setAttribute("articleUrl", articleUrl);
         req.setAttribute("paging", paging);
         req.setAttribute("schType", schType);
         req.setAttribute("kwd", kwd);
         
      } catch (Exception e) {
         e.printStackTrace();
      }

      // JSP로 포워딩
      forward(req, resp, "admin/member/memberManage.jsp"); 
   }
   
   
   protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 완료		
		String cp = req.getContextPath();
		/*
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/admin/member/list.do");
			return;
		}
		*/
		MemberDAO dao = new MemberDAO();
		
		Long mNum = Long.parseLong(req.getParameter("mNum"));
		
		try {
			
			System.out.println(mNum);
			dao.updateMember(mNum);

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/admin/member/list.do");
	}



   
}
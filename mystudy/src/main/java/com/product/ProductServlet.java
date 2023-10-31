package com.product;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.MyServlet;

@WebServlet("/productadmin/*")
public class ProductServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String uri = req.getRequestURI();
		
		if(uri.indexOf("list.do") != -1) {
			list(req,resp);
		} else if (uri.indexOf("write.do") != -1) {
			writeForm(req,resp);
		} else if (uri.indexOf("write_ok.do") != -1) {
			writeSubmit(req,resp);
		} else if (uri.indexOf("update.do") != -1) {
			updateForm(req,resp);
		} else if (uri.indexOf("update_ok.do") != -1) {
			updateSubmit(req, resp);
		} else if (uri.indexOf("delete.do") != -1) {
			delete(req, resp);
		}
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 상품관리 리스트
		ProductDAO dao = new ProductDAO();
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
			if (kwd.length() == 0) {
				dataCount = dao.dataCount();
			} else {
				dataCount = dao.dataCount(schType, kwd);
			}
			
			// 전체 페이지 수
			int size = 10;
			int total_page = util.pageCount(dataCount, size);
			if (current_page > total_page) {
				current_page = total_page;
			}
			/*
			// 게시물 가져오기
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			List<ProductDTO> list = null;
			if (kwd.length() == 0) {
				list = dao.listProduct(offset, size);
			} else {
				list = dao.listProduct(offset, size, schType, kwd);
			}

			String query = "";
			if (kwd.length() != 0) {
				query = "schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "utf-8");
			}

			// 페이징 처리
			String listUrl = cp + "/productadmin/list.do";
			String articleUrl = cp + "/bbs/article.do?page=" + current_page;
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
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		// JSP로 포워딩
		viewPage(req, resp, "product/list.jsp");
	}
	
	protected void writeForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글쓰기 폼
		req.setAttribute("mode", "write");
		viewPage(req, resp, "product/write.jsp");
	}
	
	protected void writeSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 저장
		ProductDAO dao = new ProductDAO();
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			viewPage(req, resp, "redirect:/product/list.do");
			return;
		}
		
		try {
			ProductDTO dto = new ProductDTO();

			// 파라미터
			dto.setProductCode(req.getParameter("productCode"));
			dto.setProductName(req.getParameter("productName"));
			dto.setProductPrice(req.getParameter("productPrice"));
			dto.setProductSubject(req.getParameter("productSubject"));
			dto.setExpirationDate(req.getParameter("expirationDate"));
			dto.setBreweryPage(req.getParameter("breweryPage"));
			dto.setProductCategory(req.getParameter("productCategory"));
			dto.setHasttag(req.getParameter("hashtag"));
			dto.setAlcoholPercent(req.getParameter("alcoholPercent"));
			dto.setProductTaste(req.getParameter("productTaste"));
			dto.setProductPerson(req.getParameter("productPerson"));
			dto.setInventory(req.getParameter("inventory"));
			dto.setImage(req.getParameter("image"));

			dao.insertProduct(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		viewPage(req, resp, "redirect:/product/list.do");
	}
	
	protected void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 폼
		ProductDAO dao = new ProductDAO();

		String cp = req.getContextPath();

		String page = req.getParameter("page");

		try {
			long num = Long.parseLong(req.getParameter("num"));
			ProductDTO dto = dao.findById(num);

			if (dto == null) {
				resp.sendRedirect(cp + "/productadmin/list.do?page=" + page);
				return;
			}


			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("mode", "update");

			forward(req, resp, "/WEB-INF/views/product/write.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/product/list.do?page=" + page);
	}
	
	protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 완료
		ProductDAO dao = new ProductDAO();

		
		String cp = req.getContextPath();
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp + "/product/list.do");
			return;
		}

		String page = req.getParameter("page");
		try {
			ProductDTO dto = new ProductDTO();
			
			dto.setProductCode(req.getParameter("productCode"));
			dto.setProductName(req.getParameter("productName"));
			dto.setProductPrice(req.getParameter("productPrice"));
			dto.setProductSubject(req.getParameter("productSubject"));
			dto.setExpirationDate(req.getParameter("expirationDate"));
			dto.setBreweryPage(req.getParameter("breweryPage"));
			dto.setProductCategory(req.getParameter("productCategory"));
			dto.setHasttag(req.getParameter("hashtag"));
			dto.setAlcoholPercent(req.getParameter("alcoholPercent"));
			dto.setProductTaste(req.getParameter("productTaste"));
			dto.setProductPerson(req.getParameter("productPerson"));
			dto.setInventory(req.getParameter("inventory"));
			dto.setImage(req.getParameter("image"));

			dao.updateBoard(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/product/list.do?page=" + page);
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 삭제
		ProductDAO dao = new ProductDAO();

		String cp = req.getContextPath();
		
		String page = req.getParameter("page");
		String query = "page=" + page;

		try {
			long productCode = Long.parseLong(req.getParameter("productCode"));
			String schType = req.getParameter("schType");
			String kwd = req.getParameter("kwd");
			if (schType == null) {
				schType = "all";
				kwd = "";
			}
			kwd = URLDecoder.decode(kwd, "utf-8");

			if (kwd.length() != 0) {
				query += "&schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "UTF-8");
			}

			dao.deleteBoard(productCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(cp + "/product/list.do?" + query);
	}
}
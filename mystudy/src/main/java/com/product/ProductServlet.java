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
import com.util.MyUtil;

@WebServlet("/product/*")
public class ProductServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String uri = req.getRequestURI();
		
		
		// 세션 정보
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("admin");

		if (info == null) {
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}
		
		
		// uri에 따른 작업 구분
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
			// 넘어온 페이지
			String page = req.getParameter("page");
			int current_page = 1;
			if (page != null) {
				current_page = Integer.parseInt(page);
			}
			
			// 검색
			String productNameKwd = req.getParameter("productNameKwd");
			String productPriceKwd = req.getParameter("productNameKwd");
			String volumeKwd = req.getParameter("volumeKwd");
			String expirationDateStart = req.getParameter("expirationDateStart");
			String expirationDateEnd = req.getParameter("expirationDateEnd");
			String productCategoryKwd = req.getParameter("productCategoryKwd");
			String alcoholPercentKwd = req.getParameter("alcoholPercentKwd");
			String productTasteKwd = req.getParameter("productTasteKwd");

			// 전체 데이터 개수
			int dataCount;
			if (productNameKwd.length() == 0
					&& productPriceKwd.length() == 0
					&& volumeKwd.length() == 0
					&& expirationDateStart.length() == 0
					&& expirationDateEnd.length() == 0
					&& productCategoryKwd.length() == 0
					&& alcoholPercentKwd.length() == 0
					&& productTasteKwd.length() == 0) {
				dataCount = dao.dataCount();
			} else {
				dataCount = dao.dataCount(productNameKwd, productPriceKwd, volumeKwd, 
						expirationDateStart, expirationDateEnd, productCategoryKwd,
						alcoholPercentKwd, productTasteKwd);
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
			
			List<ProductDTO> list = null;
			if (productNameKwd.length() != 0
					&& productPriceKwd.length() != 0
					&& volumeKwd.length() != 0
					&& expirationDateStart.length() != 0
					&& expirationDateEnd.length() != 0
					&& productCategoryKwd.length() != 0
					&& alcoholPercentKwd.length() != 0
					&& productTasteKwd.length() != 0) {
				list = dao.listProduct(offset, size);
			} else {
				list = dao.listProduct(offset, size, productNameKwd, productPriceKwd, 
						 volumeKwd, expirationDateStart, expirationDateEnd, 
						 productCategoryKwd, alcoholPercentKwd, 
						 productTasteKwd);
			}
			
			// 이게 뭔지
			String query = "";
			if (kwd.length() != 0) {
				query = "schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "utf-8");
			}
			
			// 페이징 처리
			String listUrl = cp + "/product/list.do";
			//String articleUrl = cp + "/bbs/article.do?page=" + current_page;
			if (query.length() != 0) {
				listUrl += "?" + query;
				//articleUrl += "&" + query;
			}
			

			String paging = util.paging(current_page, total_page, listUrl);

			// 포워딩할 JSP에 전달할 속성
			req.setAttribute("list", list);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("size", size);
			// req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("paging", paging);
			// req.setAttribute("schType", schType);
			// req.setAttribute("kwd", kwd);
			req.setAttribute("productNameKwd", productNameKwd);
			req.setAttribute("productPriceKwd", productPriceKwd);
			req.setAttribute("volumeKwd", volumeKwd);
			req.setAttribute("expirationDateStart", expirationDateStart);
			req.setAttribute("expirationDateEnd", expirationDateEnd);
			req.setAttribute("productCategoryKwd", productCategoryKwd);
			req.setAttribute("alcoholPercentKwd", alcoholPercentKwd);
			req.setAttribute("productTasteKwd", productTasteKwd);
			
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
			dto.setProductName(req.getParameter("productName"));
			dto.setProductPrice(Integer.parseInt(req.getParameter("productPrice")));
			dto.setProductSubject(req.getParameter("productSubject"));
			dto.setExpirationDate(req.getParameter("expirationDate"));
			dto.setBreweryPage(req.getParameter("breweryPage"));
			dto.setProductCategory(req.getParameter("productCategory"));
			dto.setHashTag(req.getParameter("hashtag"));
			dto.setAlcoholPercent(Double.parseDouble(req.getParameter("alcoholPercent")));
			dto.setProductTaste(req.getParameter("productTaste"));
			dto.setProductPerson(req.getParameter("productPerson"));
			dto.setInventory(Integer.parseInt(req.getParameter("inventory")));
			dto.setImage(req.getParameter("image"));

			dao.insertProduct(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		viewPage(req, resp, "redirect:/product/list.do");
	}
	
	protected void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 폼 // article을 보지않고 수정하는 방법
		ProductDAO dao = new ProductDAO();

		String cp = req.getContextPath();

		String page = req.getParameter("page");

		try {
			long num = Long.parseLong(req.getParameter("num"));
			ProductDTO dto = dao.findById(num);

			if (dto == null) {
				resp.sendRedirect(cp + "/product/list.do?page=" + page);
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
			
			dto.setProductCode(req.getParameter("productName"), req.getParameter("breweryPage"));
			dto.setProductName(req.getParameter("productName"));
			dto.setProductPrice(Integer.parseInt(req.getParameter("productPrice")));
			dto.setProductSubject(req.getParameter("productSubject"));
			dto.setExpirationDate(req.getParameter("expirationDate"));
			dto.setBreweryPage(req.getParameter("breweryPage"));
			dto.setProductCategory(req.getParameter("productCategory"));
			dto.setHashTag(req.getParameter("hashtag"));
			dto.setAlcoholPercent(Double.parseDouble(req.getParameter("alcoholPercent")));
			dto.setProductTaste(req.getParameter("productTaste"));
			dto.setProductPerson(req.getParameter("productPerson"));
			dto.setInventory(Integer.parseInt(req.getParameter("inventory")));
			dto.setImage(req.getParameter("image"));

			dao.updateProduct(dto);
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
			String productCode = req.getParameter("productCode");
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

			dao.deleteProduct(productCode);
		} catch (Exception e) {
			e.printStackTrace();
		}  

		resp.sendRedirect(cp + "/product/list.do?" + query);
	}
}

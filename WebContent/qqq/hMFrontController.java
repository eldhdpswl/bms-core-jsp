package bms.hmember.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bms.gboard.service.gBoardService;
import bms.gboard.service.gBoardServiceImpl;


@WebServlet("*.host")
public class hMFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public hMFrontController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		actionDo(req, res);
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		actionDo(req, res);
		
	}
	
	protected void actionDo(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//한글 안깨지게 처리..안하면 DB에 한글이 깨져서 insert
		req.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String url = uri.substring(contextPath.length());
		
		//host 메인페이지
		if(url.equals("/hostMain.host")) {
			System.out.println("/hostMain.host");
			
			viewPage = "/host/hostMain.jsp";
			
		}
		
		//host 로그아웃
		else if(url.equals("/logout.host")) {
			System.out.println("/logout.host");
			
			req.getSession().setAttribute("memId", null);
			req.setAttribute("cnt", 2);
			
			viewPage ="/member/main.jsp";
		}
		
		//host 게시판
		else if(url.equals("/hostBoardList.host")) {
			System.out.println("/hostBoardList.host");
			
			gBoardService service = new gBoardServiceImpl();
			service.boardList(req, res);
			
			
			viewPage = "/host/hostBoardList.jsp";
			
		}
		
		//host 상세페이지
		else if(url.equals("/hostContentForm.host")) {
			System.out.println("/hostContentForm.host");
		
			gBoardService service = new gBoardServiceImpl();
			service.boardList(req, res);
			
			viewPage = "/host/hostContentForm.jsp";
		}
		
		//host 재고관리
		else if(url.equals("/host_stockMain.host")) {
			System.out.println("/host_stockMain.host");
			
			viewPage = "/host/host_stockMain.jsp";
		}
		
		//host 책 목록
		else if(url.equals("/host_stockList.host")) {
			System.out.println("/host_stockList.host");
			
			viewPage = "/host/host_stockList.jsp";
		}
		
		//host 책 추가
		else if(url.equals("/host_stockaddForm.host")) {
			System.out.println("/host_stockaddForm.host");
			
			
			viewPage = "/host/host_stockaddForm.jsp";
		}
		
		//host 책 추가 처리페이지
		else if(url.equals("/host_stockaddPro.host")) {
			System.out.println("/host_stockaddPro.host");
			
			
			viewPage = "/host/host_stockaddPro.jsp";
		}
		
		
		//host 책 수정
		
		//host 책 삭제
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		dispatcher.forward(req, res);
	}

}

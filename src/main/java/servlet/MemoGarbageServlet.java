package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Memo;
import model.MemoGarbageViewLogic;
import model.User;

@WebServlet("/MemoGarbageServlet")
public class MemoGarbageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインしているか確認するためセッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		if(loginUser == null) { //ログインしていない
			//リダイレクト
			response.sendRedirect("index.jsp"); 
		} else { //ログイン済み
			//メモの取得
			MemoGarbageViewLogic memoGarbageviewLogic = new MemoGarbageViewLogic();
			List<Memo> memoList = memoGarbageviewLogic.execute();
			
			//取得したメモをリクエストスコープに格納
			request.setAttribute("memoList", memoList);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/deletelist.jsp");
			dispatcher.forward(request, response);
		}
	}
}
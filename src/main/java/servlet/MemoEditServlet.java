package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Memo;
import model.MemoEditLogic;
import model.User;


@WebServlet("/MemoEditServlet")
public class MemoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ログインしているか確認するためセッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		if(loginUser == null) { //ログインしていない
			//リダイレクト
			response.sendRedirect("index.jsp");
		} else { //ログイン済み
			//リクエストパラメータ取得
			String memoId = request.getParameter("memoId");
			
			//取得したIDをリクエストスコープに格納
			request.setAttribute("memoId", memoId);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoedit.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータ取得
		int memoId = Integer.parseInt(request.getParameter("memoId"));
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		
		//エラーメッセージリストの作成
		List<String> errorMessages = new ArrayList<>();
		
		//入力値のチェック
		if(title == null || title.isEmpty()) {
			errorMessages.add("※タイトルを入力してください");
		}
		if(memo == null || memo.isEmpty()) {
			errorMessages.add("※メモを入力してください");
		}
		
		if(!errorMessages.isEmpty()) { //エラーメッセージが格納された場合
			//エラーメッセージをリクエストスコープへ保存
			request.setAttribute("errorMessage", errorMessages);
			//memoIdを再度引き継ぐ
			request.setAttribute("memoId", memoId);
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoedit.jsp");
			dispatcher.forward(request, response);
		} else { //問題がない場合
			//メモインスタンスの作成
			Memo memoList = new Memo(memoId, title, memo);
			
			//メモ編集処理の呼び出し
			MemoEditLogic memoEditLogic = new MemoEditLogic();
			boolean editResult = memoEditLogic.execute(memoList);
			
			if(editResult) {
				 response.sendRedirect("MemoServlet");
			}
		}
	}
}

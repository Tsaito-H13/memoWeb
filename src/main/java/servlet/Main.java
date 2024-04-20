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
import model.MemoLogic;
import model.User;

//メインコントローラー
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ログインしているか確認するためセッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		if(loginUser == null) { //ログインしていない
			//リダイレクト
			response.sendRedirect("index.jsp"); 
		} else { //ログイン済み
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータの取得
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
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		} else { //問題がない場合
			//メモインスタンスの作成
			Memo text = new Memo(title, memo);
			
			//メモ作成処理インスタンスの作成
			MemoLogic memoLogic = new MemoLogic();
				
			//メモ作成を実行
			memoLogic.execute(text);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/submit.jsp");
			dispatcher.forward(request, response);			
		}
	}
}

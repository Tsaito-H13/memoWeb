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

import model.SecessionLogic;
import model.User;


@WebServlet("/SecessionServlet")
public class SecessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//フォワード先
		String forwardPath = null;
		
		//サーブレットクラスの動作を決定する「action」の値をリクエストパラメータから取得
		String action = request.getParameter("action");

		if(action == null) { //削除開始をリクエストされたときの処理
			//フォワード先の設定
			forwardPath = "WEB-INF/jsp/secession.jsp";
		} else if(action.equals("done")) { //削除実行をリクエストされたときの処理
			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User secessionUser = (User)session.getAttribute("secessionUser");
			
			//削除処理の呼び出し
			SecessionLogic secessionLogic = new SecessionLogic();
			boolean secessionResult = secessionLogic.execute(secessionUser);
			
			if(secessionResult) { //削除が出来た場合
				//フォワード先の設定
				forwardPath = "WEB-INF/jsp/secessionDone.jsp";
				//不要になったセッションスコープ内のインスタンスを削除
				session.invalidate();
			} else { //削除ができなかった場合
				//フォワード先の設定
				forwardPath = "WEB-INF/jsp/secessionNG.jsp";
				//不要になったセッションスコープ内のインスタンスを削除
				session.removeAttribute("secessionUser");
			}
		}
		
		//設定先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを取得
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		//エラーメッセージリストの作成
		List<String> errorMessages = new ArrayList<>();
		
		//入力値のチェック
//		if(userId == null || userId.isEmpty()) {
//			errorMessages.add("※ユーザーIDを入力してください");
//		}
		if(pass == null || pass.isEmpty()) {
			errorMessages.add("※パスワードを入力してください");
		}
		
		if(!errorMessages.isEmpty()) { //エラーメッセージが格納された場合
			//エラーメッセージをリクエストスコープへ保存
			request.setAttribute("errorMessage", errorMessages);
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/secession.jsp");
			dispatcher.forward(request, response);
		}
		
		//削除するユーザーの情報を設定
		User secessionUser = new User(userId, pass);
		
		//セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("secessionUser", secessionUser);
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/secessionConfirm.jsp");
		dispatcher.forward(request, response);
	}
}
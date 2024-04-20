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

import dao.AccountsDAO;
import model.Account;
import model.RegisterLogic;

//アカウント作成コントローラー
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード先
		String forwardPath = null;
		
		//サーブレットクラスの動作を決定する「action」の値をリクエストパラメータから取得
		String action = request.getParameter("action");

		if(action == null) { //登録開始をリクエストされたときの処理
			//フォワード先の設定
			forwardPath = "WEB-INF/jsp/register.jsp";
		} else if(action.equals("done")) { //登録実行をリクエストされたときの処理
			//セッションスコープに保存された登録ユーザーを取得
			HttpSession session = request.getSession();
			Account registerAccount = (Account)session.getAttribute("registerAccount");
			
			//登録処理の呼び出し
			RegisterLogic resisterLogic = new RegisterLogic();
			boolean registerResult = resisterLogic.execute(registerAccount);
			
			//結果をリクエストスコープへ保存
			request.setAttribute("registerResult", registerResult);
			
			//フォワード先の設定
			forwardPath = "WEB-INF/jsp/registerDone.jsp";
			
			//不要になったセッションスコープ内のインスタンスを削除
			session.removeAttribute("registerAccount");
		}
		
		//設定先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		AccountsDAO dao = new AccountsDAO();
		
		//エラーメッセージリストの作成
		List<String> errorMessages = new ArrayList<>();
		
		//入力値のチェック
		if(userId == null || userId.isEmpty()) {
			errorMessages.add("※ユーザーIDを入力してください");
		}
		if(pass == null || pass.isEmpty()) {
			errorMessages.add("※パスワードを入力してください");
		}
		if(name == null || name.isEmpty()) {
			errorMessages.add("※名前を入力してください");
		}
		if(dao.exists(userId)) {
			errorMessages.add("※そのユーザーIDは既に存在します");
		}
		
		if(!errorMessages.isEmpty()) { //エラーメッセージが格納された場合
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMessage", errorMessages);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		}
		
		//登録するアカウントの情報を設定
		Account registerAccount = new Account(userId, pass, name);
		
		//セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerAccount", registerAccount);
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}
}

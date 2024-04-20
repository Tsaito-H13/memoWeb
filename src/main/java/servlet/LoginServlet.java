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

import model.LoginLogic;
import model.User;

//ログインコントローラー
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータの取得
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		//エラーメッセージリストの作成
		List<String> errorMessages = new ArrayList<>();
		
		//入力値のチェック
		if(userId == null || userId.isEmpty()) {
			errorMessages.add("※ユーザーIDを入力してください");
		}
		
		if(pass == null || pass.isEmpty()) {
			errorMessages.add("※パスワードを入力してください");
		}
		
	    if(!errorMessages.isEmpty()) {  // エラーメッセージが格納された場合
	        //エラーメッセージをリクエストスコープへ保存
	        request.setAttribute("errorMessage", errorMessages);
	        //フォワード
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
	        dispatcher.forward(request, response);
	    }

	    //ユーザーインスタンス生成
	    User user = new User(userId, pass);

	    //ログイン処理
	    LoginLogic loginLogic = new LoginLogic();

	    //ユーザーインスタンスを渡してログイン処理の結果を格納
	    boolean result = loginLogic.execute(user);

	    //結果をリクエストスコープへ保存
	    request.setAttribute("result", result);
	    
	    //ユーザー情報をセッションスコープに保存
	    HttpSession session = request.getSession();
	    session.setAttribute("loginUser", user);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/LoginResult.jsp");
	    dispatcher.forward(request, response);
	}
}

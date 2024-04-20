package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemoRestoreLogic;


@WebServlet("/MemoRestoreServlet")
public class MemoRestoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータ取得
		int memoId = Integer.parseInt(request.getParameter("memoId"));
		
		//復元処理クラスの呼び出し
		MemoRestoreLogic memoRestoreLogic = new MemoRestoreLogic();
		boolean restoreResult = memoRestoreLogic.execute(memoId);
		
		if(restoreResult) {
			response.sendRedirect("MemoGarbageServlet");
		}
	}
}

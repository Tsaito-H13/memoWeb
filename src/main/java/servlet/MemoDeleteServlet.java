package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LogicalDeleteMemoLogic;
import model.PhysicalDeleteMemoLogic;

@WebServlet("/MemoDeleteServlet")
public class MemoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memoId = Integer.parseInt(request.getParameter("memoId"));
		String deleteMethod = request.getParameter("deleteMethod");
		
		if (deleteMethod.equals("logical")) {
			// memoIdを使用して論理削除の処理
			LogicalDeleteMemoLogic memoDeleteLogic = new LogicalDeleteMemoLogic();
			boolean deleteResult = memoDeleteLogic.execute(memoId);
			if(deleteResult) { //削除出来た場合
				//リダイレクト
				response.sendRedirect("MemoServlet");
			}
		} else if (deleteMethod.equals("physical")) {
			// memoIdを使用して物理削除の処理
			PhysicalDeleteMemoLogic memoDeleteLogic = new PhysicalDeleteMemoLogic();
			boolean deleteResult = memoDeleteLogic.execute(memoId);
			
			if(deleteResult) { //削除できた場合
				//リダイレクト
				response.sendRedirect("MemoGarbageServlet");
			}
		}
	}
}

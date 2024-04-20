package model;

import dao.MemoDAO;

public class PhysicalDeleteMemoLogic {
	/**
	 * メモ物理削除処理
	 * @param memoId
	 * @return メモ物理削除実行の結果　削除できればtrue、出来なければfalse
	 */
	public boolean execute(int memoId) {
		//メモ物理削除処理を呼び出し、結果(true or false) を格納してreturn
		MemoDAO dao = new MemoDAO();
		boolean deleteResult = dao.delete(memoId);
		return deleteResult;
	}
}

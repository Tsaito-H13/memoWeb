package model;

import dao.MemoDAO;

//メモ論理削除処理クラス
public class LogicalDeleteMemoLogic {
	/**
	 * メモ論理削除処理
	 * @param memoId
	 * @return メモ論理削除実行の結果　削除できればtrue、出来なければfalse
	 */
	public boolean execute(int memoId) {
		//メモ論理削除処理を読み出し、結果(true or false) を格納してreturn
		MemoDAO dao = new MemoDAO();
		boolean deleteResult = dao.logicalDelete(memoId);
		return deleteResult;
	}
}

package model;

import dao.MemoDAO;

//メモ復元処理クラス
public class MemoRestoreLogic {
	
	/**
	 * メモ復元処理
	 * @param MemoId
	 * @return 復元処理の結果(true or false) を格納してreturn
	 */
	public boolean execute(int MemoId) {
		MemoDAO dao = new MemoDAO();
		boolean restoreResult = dao.restore(MemoId);
		return restoreResult;
	}

}

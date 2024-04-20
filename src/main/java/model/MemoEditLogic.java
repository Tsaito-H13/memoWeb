package model;

import dao.MemoDAO;

//メモ編集処理クラス
public class MemoEditLogic {
	/**
	 * メモ編集処理
	 * @param memo
	 * @return　編集できればtrue、出来なければfalse
	 */
	public boolean execute(Memo memo) {
		//メモ編集処理を呼び出して、結果(true or false) を格納してreturn
		MemoDAO dao = new MemoDAO();
		boolean editResult = dao.edit(memo);
		return editResult;
	}
}
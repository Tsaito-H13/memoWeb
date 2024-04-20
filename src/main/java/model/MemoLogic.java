package model;

import dao.MemoDAO;

//メモ作成処理クラス
public class MemoLogic {
	
	/**
	 * メモ作成
	 * @param memo
	 */
	public void execute(Memo memo) {
		MemoDAO dao = new MemoDAO();
		
		//MemoDAOのcreateメソッドを実行
		dao.create(memo);
	}
}

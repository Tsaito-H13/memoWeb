package model;

import java.util.List;

import dao.MemoDAO;

//論理削除されたメモの取得処理クラス
public class MemoGarbageViewLogic {

	/**
	 * 論理削除メモ取得処理
	 * @return　取得したメモが格納された動的配列
	 */
	public List<Memo> execute() {
		//論理削除されたメモの取得処理の呼び出し、メモが格納された配列をreturn
		MemoDAO dao = new MemoDAO();
		List<Memo> memoList = dao.deleteFindAll();
		return memoList;
	}

}

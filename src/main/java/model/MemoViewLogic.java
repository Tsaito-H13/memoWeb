package model;

import java.util.List;

import dao.MemoDAO;

//メモ取得処理クラス
public class MemoViewLogic {
	
	/**
	 * データベースのメモを取得して表示
	 * @return メモのタイトル、内容、作成時刻を格納したmemoList
	 */
	public List<Memo> execute() {
		MemoDAO dao = new MemoDAO();
		//MemoDAOのfindAllメソッドで取得した情報をmemoListに格納
		List<Memo> memoList = dao.findAll();
		return memoList;
	}
}

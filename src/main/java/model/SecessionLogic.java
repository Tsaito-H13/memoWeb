package model;

import dao.AccountsDAO;

//アカウント削除処理クラス
public class SecessionLogic {
	
	/**
	 * アカウント削除メソッド
	 * @param 削除ページから入力されたユーザー情報
	 * @return AccountsDAOのdeleteメソッドを実行した結果
	 */
	public boolean execute(User user) {
		AccountsDAO dao = new AccountsDAO();
		
		//パスワードハッシュ化処理
		String hashedPassword = PasswordHasher.hashPassword(user.getPass());
		user.setPass(hashedPassword);
		
		//AccountsDAOのdeleteメソッドの結果を格納
		boolean deleteResult = dao.delete(user);
		return deleteResult;
	}
}

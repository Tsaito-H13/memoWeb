package model;

import dao.AccountsDAO;

//アカウント作成処理クラス
public class RegisterLogic {
	
	/**
	 * アカウント作成メソッド
	 * @param ユーザーから入力された情報を格納したaccount
	 * @return AccountsDAOのcreateメソッドを実行した結果
	 */
	public boolean execute(Account account) {
		AccountsDAO dao = new AccountsDAO();
		
		//パスワードハッシュ化処理
		String hashedPassword = PasswordHasher.hashPassword(account.getPass());
		account.setPass(hashedPassword);

		//AccountDAOのcreateメソッドの結果を格納
		boolean registerResult = dao.create(account);
		return registerResult;
	}
}

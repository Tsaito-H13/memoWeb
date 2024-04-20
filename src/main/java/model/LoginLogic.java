package model;

import dao.AccountsDAO;

//ログイン処理クラス
public class LoginLogic {
	
	/**
	 * ログイン処理メソッド
	 * @param ログイン画面から入力されたユーザー情報
	 * @return データベースからアカウントを取得できればtrue、出来なければfalse
	 */
	public boolean execute(User user) {
		AccountsDAO dao = new AccountsDAO();

		//パスワードのハッシュ化
		String hashedPassword = PasswordHasher.hashPassword(user.getPass());
		user.setPass(hashedPassword);
		
		//AccountsDAOのfindByLoginの結果を使用してアカウントインスタンスの生成
		Account account = dao.findByLogin(user);
		
		return account != null;
	}
}

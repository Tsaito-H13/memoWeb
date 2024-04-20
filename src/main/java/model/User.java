package model;

//ユーザー情報格納クラス
public class User {
	
	//ユーザーID
	private String userId;
	
	//パスワード
	private String pass;

	public User(String userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}

	public String getUserId() {
		return userId;
	}

	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
}

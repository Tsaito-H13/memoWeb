package model;

//アカウント格納クラス
public class Account {
	
	 //ユーザーID
	private String userId;
	
	//パスワード
	private String pass;
	
	//名前
	private String name;
	
	public Account(String userId, String pass, String name) {
		this.userId = userId;
		this.pass = pass;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public String getPass() {
		return pass;
	}

	public String getName() {
		return name;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}

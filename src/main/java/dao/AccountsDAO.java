package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBProperties;
import model.Account;
import model.User;

//アカウントDAO
public class AccountsDAO {
    private final String URL = DBProperties.getUrl();
    private final String USER = DBProperties.getUser();
    private final String PASS = DBProperties.getPassword();
	
    static {
    	try {
    		Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
    }
    
	/**
	 * ログイン処理
	 * @param ログイン画面から入力されたユーザー情報
	 * @return データベースから取得したアカウント情報を格納したAccountインスタンス
	 */
	public Account findByLogin(User user) {
		Account account = null;
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT user_id, pass, name FROM ACCOUNTS WHERE user_id=? AND pass=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getPass());
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				String userId = rs.getString("user_id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				account = new Account(userId, pass, name);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
	
	/**
	 * アカウント作成処理
	 * @param アカウント作成画面から入力されたアカウント情報
	 * @return インサートの実行結果　作成できたらtrue、出来なければfalse
	 */
	public boolean create(Account account) {
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO ACCOUNTS(user_id, pass, name) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getUserId());
			pStmt.setString(2, account.getPass());
			pStmt.setString(3, account.getName());
			
			int result = pStmt.executeUpdate();
			
			if(result != 1) {
				conn.rollback();
				return false;
			}
			
			conn.commit();
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * アカウント削除処理
	 * @param アカウント削除画面から入力されたユーザー情報
	 * @return デリートの実行結果 削除できればtrue、出来なければfalse
	 */
	public boolean delete(User user) {
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM ACCOUNTS WHERE user_id=? AND pass=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getPass());
			
			int result = pStmt.executeUpdate();
			
			if(result != 1) {
				conn.rollback();
				return false;
			}
			
			conn.commit();
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 *  ユーザーID検査
	 * @param userId
	 * @return 同じユーザーが存在すればtrue、存在しなければfalse
	 */
	public boolean exists(String userId) {
		
	    try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
	    	
	        String sql = "SELECT COUNT(*) FROM ACCOUNTS WHERE user_id = ?";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, userId);
	        
	        ResultSet rs = pStmt.executeQuery();
	        
	        if(rs.next()) {
	            int count = rs.getInt(1);
	            if(count > 0) {
	                return true;
	            }
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBProperties;
import model.Memo;

//メモDAO
public class MemoDAO {
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
	 * メモ全取得
	 * @return メモ情報を格納したmemoList
	 */
	public  List<Memo> findAll() {
		
		List<Memo> memoList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			
			String sql = "SELECT memo_id, title, memo, create_date, modified_date FROM MEMO_DATA WHERE is_deleted = 0 ORDER BY memo_id DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int memoId = rs.getInt("memo_id");
				String title = rs.getString("title");
				String content = rs.getString("memo");
				String createDate = rs.getString("create_date");
			    String modifiedDate = rs.getString("modified_date");
				Memo text = new Memo(memoId, title, content, createDate, modifiedDate);
				memoList.add(text);
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return memoList;
	}
	
	/**
	 * 論理削除されたメモ全取得
	 * @return　メモ情報を格納したmemoList
	 */
	public  List<Memo> deleteFindAll() {
		
		List<Memo> memoList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			
			String sql = "SELECT memo_id, title, memo, create_date, modified_date FROM MEMO_DATA WHERE is_deleted = 1 ORDER BY memo_id DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int memoId = rs.getInt("memo_id");
				String title = rs.getString("title");
				String content = rs.getString("memo");
				String createDate = rs.getString("create_date");
			    String modifiedDate = rs.getString("modified_date");
				Memo text = new Memo(memoId, title, content, createDate, modifiedDate);
				memoList.add(text);
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return memoList;
	}
	
	/**
	 * メモ作成
	 * @param memo
	 * @return メモが作成出来ればtrue、作成できなければfalse
	 */
	public boolean create(Memo memo) {
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO MEMO_DATA(category, title, memo, create_date, modified_date, is_deleted) VALUES(0,?,?,cast(now() as datetime), cast(now() as datetime), 0)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, memo.getTitle());
			pStmt.setString(2, memo.getMemo());
			
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
	 * メモ編集
	 * @param memo
	 * @return 編集できればtrue、出来なければfalse
	 */
	public boolean edit(Memo memo) {

		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE MEMO_DATA SET title = ?, memo = ?, modified_date = cast(now() as datetime)  WHERE memo_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, memo.getTitle());
			pStmt.setString(2, memo.getMemo());
			pStmt.setInt(3, memo.getMemoId());
			
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
	 * 論理削除
	 * @param memoId
	 * @return　引数のmemoIdで検索されたメモを論理削除出来ればtrue、出来なければfalse
	 */
	public boolean logicalDelete(int memoId) {
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE MEMO_DATA SET is_deleted = 1 WHERE memo_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, memoId);
			
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
	 * 復元
	 * @param memoId
	 * @return　論理削除されたものを復元できればtrue、出来なければfalse
	 */
	public boolean restore(int memoId) {
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE MEMO_DATA SET is_deleted = 0 WHERE memo_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, memoId);
			
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
	 * 物理削除
	 * @param memoId
	 * @return 削除できればtrue、出来なければfalse
	 */
	public boolean delete(int memoId) {
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM MEMO_DATA WHERE memo_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, memoId);
			
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
}

// ����� ���� DB ����
// USER ���̺� ����� ������ �߰�, ����, ����, �˻� ���� 

package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	// USER ���̺� ���ο� User ����
	public int create(User user) throws SQLException {
		String sql = "INSERT INTO user (email, password, nickname) VALUES (?, ?, ?)";		
		Object[] param = new Object[] {user.getEmail(), user.getPassword(), user.getNickname()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}

	// USER ���̺��� ����� ���� ����
	public int update(User user) throws SQLException {
		String sql = "UPDATE user "
					+ "SET email=?, password=?, nickname=? "
					+ "WHERE id=?";
		Object[] param = new Object[] {user.getEmail(), user.getPassword(), user.getNickname(), user.getUserID()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	//�ش� ID�� ���� ����ڸ� USER ���̺��� ����
	public int remove(String userID) throws SQLException {
		String sql = "DELETE FROM user WHERE id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userID});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	// �ش� ID�� ����� ������ DB���� ã�� User ������ Ŭ������ ���� �� ��ȯ
	public User findUser(String userID) throws SQLException {
        String sql = "SELECT email, password, nickname "
        			+ "FROM user "
        			+ "WHERE id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userID});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				User user = new User(		// User ��ü�� �����Ͽ� �л� ������ ����
					Integer.parseInt(userID),
					rs.getString("email"),
					rs.getString("password"),
					rs.getString("nickname"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	// �ش� ID�� ����ڰ� �����ϴ��� �˻�
	public boolean existingUser(String userID) throws SQLException {
		String sql = "SELECT count(*) FROM user WHERE id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userID});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}
	
	// �ش� ID�� ����� ������ DB���� ã�� User ������ Ŭ������ ���� �� ��ȯ
	public User findUserByEmail(String email) throws SQLException {
		String sql = "select id, email, password, nickname "
			+ "from user "
			+ "where email=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				User user = new User(		// User ��ü�� �����Ͽ� �л� ������ ����
					rs.getInt("id"),
					email,
					rs.getString("password"),
					rs.getString("nickname"));
					System.out.println(user);
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	// �ش� ID�� ����� ������ DB���� ã�� User ������ Ŭ������ ���� �� ��ȯ
	public User findUser(int userID) throws SQLException {
        String sql = "SELECT email, password, nickname "
        			+ "FROM user "
        			+ "WHERE id=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userID});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				User user = new User(		// User ��ü�� �����Ͽ� �л� ������ ����
					userID,
					rs.getString("email"),
					rs.getString("password"),
					rs.getString("nickname"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}		

}

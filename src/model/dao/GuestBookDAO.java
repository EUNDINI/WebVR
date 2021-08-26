package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GuestBook;


public class GuestBookDAO {
	private JDBCUtil jdbcUtil = null;
	
	public GuestBookDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	public int create(GuestBook gtb) throws SQLException {//���ο� ���� �ۼ�
		String sql = "INSERT INTO guest_book VALUES (?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {gtb.getGbID(), gtb.getContent(), 
						gtb.getDate(), gtb.getUserID(), gtb.getExhbId()
						//(user.getCommId()!=0) ? user.getCommId() : null 
						};				
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
	
	
	public int update(GuestBook gtb) throws SQLException {//���� ����
		String sql = "UPDATE guest_book "
					+ "SET content=? "
					+ "WHERE user_id=?, id=? ";
		Object[] param = new Object[] {gtb.getContent(), gtb.getUserID(), 
					gtb.getGbID()};				
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
	
	public int remove(String gbID) throws SQLException {//���� ����
		String sql = "DELETE FROM guest_book WHERE id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {gbID});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	
	
	public List<GuestBook> GBList() throws SQLException {//���� �� ���� ����Ʈ ��ȯ
        String sql = "SELECT id, user_id, exhibition_id, content, date " 
        		   + "FROM guest_book"
        		   + "WHERE exhibition_id=?"
        		   + "ORDER BY date";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<GuestBook> GBList = new ArrayList<GuestBook>();	// User���� ����Ʈ ����
			while (rs.next()) {
				GuestBook GB = new GuestBook(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("id"),
					rs.getInt("user_id"),
					rs.getInt("exhibition_id"),
					rs.getString("content"),
					rs.getString("date"));
				GBList.add(GB);				// List�� User ��ü ����
			}		
			return GBList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public List<GuestBook> myGBList() throws SQLException {//Ư�� ����� ���� ����Ʈ ��ȯ
        String sql = "SELECT id, user_id, exhibition_id, content, date " 
        		   + "FROM guest_book"
        		   + "WHERE user_id=?"
        		   + "ORDER BY date";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<GuestBook> myGBList = new ArrayList<GuestBook>();	// User���� ����Ʈ ����
			while (rs.next()) {
				GuestBook GB = new GuestBook(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("id"),
					rs.getInt("user_id"),
					rs.getInt("exhibition_id"),
					rs.getString("content"),
					rs.getString("date"));
				myGBList.add(GB);				// List�� User ��ü ����
			}		
			return myGBList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
}

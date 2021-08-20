package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Exhibition;

public class ExhibitionDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ExhibitionDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	public int create() throws SQLException {
//		String sql = "INSERT INTO USER VALUES (?, ?, ?, ?)";		
//		Object[] param = new Object[] {5, "wlwdnd1118@gmail.com", "1234", "456789456789"};				
		String sql = "select * from test1;";
		Object[] param = new Object[] {};
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
		try {				
//			int result = jdbcUtil.executeUpdate();	// insert �� ����
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				System.out.println("print rs : " + rs.getInt("test_c"));
			}
			return 1;
//			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
//
//		Date start_date = new Date(2021, 8, 15);
//		Date end_date = new Date(2021, 8, 16);
//		sql = "INSERT INTO EXHIBITION VALUES (?, ?, ?, ?, ?, ?)";		
//		param = new Object[] {5, 5, "title", "����", start_date, end_date, "desc"};				
//		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
//		try {				
//			int result = jdbcUtil.executeUpdate();	// insert �� ����
//			return result;
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		} finally {		
//			jdbcUtil.commit();
//			jdbcUtil.close();	// resource ��ȯ
//		}		
		return 0;			
	}

}

package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artwork;
import model.Visit;
import model.User;

public class VisitDAO {
	private JDBCUtil jdbcUtil = null;
	
	public VisitDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}
	
	// Visit ����
	public int create(User user, Artwork artwork) throws SQLException {
		String sql = "INSERT INTO visit VALUES (?, ?)";		
		Object[] param = new Object[] { user.getUserID(), 
						artwork.getArtworkId() };	
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		String key[] = { "id" };
		try {
			jdbcUtil.executeUpdate(key); // insert �� ����
			ResultSet rs = jdbcUtil.getGeneratedKeys();

			if (rs.next()) {
				int generatedKey = rs.getInt(1);
				return generatedKey;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}

		return 0;			
	}
		
	// Visit ����
	public int remove(int id) throws SQLException {
		String sql = "DELETE FROM visit WHERE id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	
	// �־��� userId�� �ش��ϴ� visit List�� �����ͺ��̽����� ã�� ������ Ŭ������ �����Ͽ� ��ȯ
	public List<Visit> findVisitListByUserId(int userId) throws SQLException {
		String sql = "SELECT v.id, v.user_id, exhibition_id, e.title, e.description, e.image_address "
    			+ "FROM visit v "
				+ "INNER JOIN exhibition e "
    			+ "ON v.exhibition_id = e.id "
    			+ "WHERE v.user_id=? ";  
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Visit> visitList = new ArrayList<Visit>();	
			while (rs.next()) {				
				Visit visit = new Visit(		// like ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
						rs.getInt("v.id"),
						rs.getInt("v.user_id"),
						rs.getInt("exhibition_id"),
						rs.getString("e.title"),
						rs.getString("e.description"),
						rs.getString("e.image_address")
						);
				visitList.add(visit);
			}		
			return visitList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	// �־��� exhibitionId�� �ش��ϴ� visit List�� �����ͺ��̽����� ã�� ������ Ŭ������ �����Ͽ� ��ȯ
	public List<Visit> findVisitListByArtworkId(int artworkId) throws SQLException {
		String sql = "SELECT id, user_id, exhibition_id "
    			+ "FROM visit "
    			+ "WHERE exhibition_id=? ";  
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artworkId});	// JDBCUtil�� query���� �Ű� ���� ����
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Visit> visitList = new ArrayList<Visit>();	
			while (rs.next()) {				
				Visit visit = new Visit(		// like ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
						rs.getInt("id"),
						rs.getInt("user_id"),
						rs.getInt("exhibition_id"));
				visitList.add(visit);
			}		
			return visitList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

}

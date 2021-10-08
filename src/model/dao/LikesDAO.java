package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artwork;
import model.Likes;
import model.User;

public class LikesDAO {
	private JDBCUtil jdbcUtil = null;
	
	public LikesDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}
	
	// Likes ����
	public int create(User user, Artwork artwork) throws SQLException {
		String sql = "INSERT INTO likes(user_id, artwork_id) VALUES (?, ?)";	
		Object[] param = new Object[] { user.getUserID(), 
						artwork.getArtworkId() };
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}

		return 0;			
	}
		
	// Likes ����
	public int remove(int id) throws SQLException {
		String sql = "DELETE FROM likes WHERE id=?";		
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
	
	// �־��� userId, artworkId�� �ش��ϴ� artwork�� �����ͺ��̽����� ã�� ������ Ŭ������ �����Ͽ� ��ȯ
	public Likes findLikesByUserId(int userId, int artworkId) throws SQLException {
		String sql = "SELECT id, user_id, artwork_id "
    			+ "FROM likes "
    			+ "WHERE user_id=? and artwork_id=? ";         
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, artworkId});	// JDBCUtil�� query���� �Ű� ���� ����
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			Likes like = null;
			if (rs.next()) {						//  ���� �߰�
				like = new Likes(		// like ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
						rs.getInt("id"),
						rs.getInt("user_id"),
						rs.getInt("artwork_id"));
			}
			return like;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null; 
		
	}
	
	// �־��� userId�� �ش��ϴ� likes List�� �����ͺ��̽����� ã�� ������ Ŭ������ �����Ͽ� ��ȯ
		public List<Likes> findLikesListByUserId(int userId) throws SQLException {
			String sql = "SELECT l.id, l.user_id, l.artwork_id, a.artwork_address, a.exhibition_id "
	    			+ "FROM likes l "
					+ "INNER JOIN artwork a "
	    			+ "ON l.artwork_id = a.id "
	    			+ "WHERE user_id=? ";  
			
			jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����
			try {
				ResultSet rs = jdbcUtil.executeQuery();			// query ����			
				List<Likes> likesList = new ArrayList<Likes>();	
				while (rs.next()) {				
					Likes like = new Likes(		// like ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
							rs.getInt("l.id"),
							rs.getInt("l.user_id"),
							rs.getInt("l.artwork_id"),
							rs.getString("a.artwork_address"),
							rs.getInt("a.exhibition_id")
							);
					likesList.add(like);
				}		
				return likesList;					
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
			return null;
		}
	
	// �־��� artworkId�� �ش��ϴ� likes List�� �����ͺ��̽����� ã�� ������ Ŭ������ �����Ͽ� ��ȯ
	public List<Likes> findLikesListByArtworkId(int artworkId) throws SQLException {
		String sql = "SELECT id, user_id, artwork_id "
    			+ "FROM likes "
    			+ "WHERE artwork_id=? ";  
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artworkId});	// JDBCUtil�� query���� �Ű� ���� ����
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Likes> likesList = new ArrayList<Likes>();	
			while (rs.next()) {				
				Likes like = new Likes(		// like ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
						rs.getInt("id"),
						rs.getInt("user_id"),
						rs.getInt("artwork_id"));
				likesList.add(like);
			}		
			return likesList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
}

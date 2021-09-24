package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.User;

public class CommentDAO {
private JDBCUtil jdbcUtil = null;
	
	public CommentDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	// comment ����
	public int create(Comment comment) throws SQLException {
		String sql = "insert into comment (content, date, user_id, artwork_id) values (?, ?, ?, ?)"; //null�� �θ� �˾Ƽ� �����ϴ���? �ƴϸ� id �÷��� �����ϰ� �־�� �ϴ� ����?	
		Object[] param = new Object[] {
				comment.getContent(), 
				new java.sql.Date(comment.getDate().getTime()), 
				comment.getUserID(), 
				comment.getArtwId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	
		try {				
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;			
	}
	
	// comment ����
	public int delete(int commentId) throws SQLException {
		String sql = "delete from comment where id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {commentId});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	
	// artwork id�� ���� �ش� ��ǰ�� ���� �ֱ� 30���� comment�� list ��ȸ
	public List<Comment> findCommentListByArtworkId(int artworkId) {
		String sql = "select id, content, date, user_id " 
      		   + "from comment "
      		   + "where artwork_id=? "
      		   + "order by id desc " // �ð� ������� id�� ��������ϱ� �׳� id�� �������� ����
      		   + "limit 30"; // �ֱ� 30��
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artworkId});	
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<Comment> commentList = new ArrayList<Comment>();	
			while (rs.next()) {
				Comment comment = new Comment(
					rs.getInt("id"),
					rs.getString("content"),
					new java.util.Date(rs.getDate("date").getTime()),
					rs.getInt("user_id"),
					artworkId);
				commentList.add(comment);	
			}		
			return commentList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	// ����� id�� �ش��ϴ� comment List ������
		public List<Comment> findCommentListByUserId(int userId) throws SQLException {//Ư�� ����� ���� ����Ʈ ��ȯ
	        String sql = "SELECT c.id, user_id, artwork_id, a.title, content, c.date " 
	        		   + "FROM comment c "
	        		   + "INNER JOIN artwork a "
	        		   + "ON a.id = c.artwork_id "
	        		   + "WHERE user_id=? "
	        		   + "ORDER BY c.id DESC ";
	        
			jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});		// JDBCUtil�� query�� ����
						
			try {
				ResultSet rs = jdbcUtil.executeQuery();		
				List<Comment> commentList = new ArrayList<Comment>();	// User���� ����Ʈ ����
				while (rs.next()) {
					Comment comment = new Comment(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("c.id"),
						rs.getString("content"),
						new java.util.Date(rs.getDate("c.date").getTime()),
						userId,
						rs.getInt("artwork_id"),
						rs.getString("a.title"));
					commentList.add(comment);				// List�� User ��ü ����
				}		
				return commentList;					
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
			return null;
		}

}
